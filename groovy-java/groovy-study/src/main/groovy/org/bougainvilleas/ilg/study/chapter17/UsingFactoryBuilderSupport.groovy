package org.bougainvilleas.ilg.study.chapter17

/**
 * SwingBuilder 中 button checkbox label 等明确定义的节点名，用 FactoryBuilderSupport
 * 抽象工厂
 * 基于节点名，将节点的创建委托给不同工厂，只需要将节点名映射到工厂
 */
def bldr=new RobotBuilder()

def robot=bldr.robot('iRobot'){
    forward(dist:20)
    left(rotation:90)
    forward(speed:10,duration:5)
}
robot.go()


class RobotBuilder extends FactoryBuilderSupport{
    {
        /**
         * robot 由 RobotFactory 创建
         */
        registerFactory('robot',new RobotFactory())
        /**
         * forward 由 ForwardMoveFactory 创建
         */
        registerFactory('forward',new ForwardMoveFactory())
        /**
         * left 由 LeftTurnFactory 创建
         */
        registerFactory('left',new LeftTurnFactory())
    };
}

class Robot{
    String name
    def movements=[]
    void go(){
        println "Robot $name operating..."
        movements.each{movement->println movement}
    }
}
class ForwardMove{
    def dist
    String toString(){"move distance... $dist"}
}
class LeftTurn{
    def rotation
    String toString(){ "turn left... $rotation degrees"  }
}

class RobotFactory extends AbstractFactory{

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        new Robot(name:value)
    }

    @Override
    void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
        /**
         * 子节点
         */
        parent.movements << child
    }
}

class ForwardMoveFactory extends AbstractFactory{
    boolean isLeaf(){true}

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        new ForwardMove()
    }

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        if(attributes.speed && attributes.duration){
            node.dist=attributes.speed * attributes.duration
            attributes.remove('speed')
            attributes.remove('duration')
        }
        true
    }
}

class LeftTurnFactory extends AbstractFactory{
    boolean isLeaf(){true}

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        new LeftTurn()
    }
}
