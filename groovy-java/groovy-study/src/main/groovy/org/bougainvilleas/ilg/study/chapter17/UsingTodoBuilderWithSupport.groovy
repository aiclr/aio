package org.bougainvilleas.ilg.study.chapter17

/**
 * BuilderSupport 提供用于识别节点结构的便捷方法
 * 不用编写处理结构的逻辑，只需要监听调用，groovy 会遍历结构并采取相应的动作
 * 在解析结构时，会触发处理器上的事件 createNode nodeCompleted propertyMissing
 *
 * 适合层次式结构
 */
class TodoBuilderWithSupport extends BuilderSupport{

    int level=0
    def result=new StringWriter()

    @Override
    protected void setParent(Object parent, Object child) {

    }

    /**
     * 创建节点 build
     *        Buy_New_Mac
     */
    @Override
    protected Object createNode(Object name) {
        if(name=='build'){
            result<<"To-Do:\n"
            'buildnode'
        }else{
            handle(name,[:])
        }
    }

    @Override
    protected Object createNode(Object name, Object value) {
        throw new Exception("Invalid format")
    }


    /**
     * 创建节点  Prepare_Vacation(start: '02/15',end: '02/22')
     *              Reserve_Flight(pn:'01/01',status:'done')
     *              Reserve_Hotel(on:'01/02')
     *              Reserve_Car(on:'01/02')
     */
    @Override
    protected Object createNode(Object name, Map attributes) {
        handle(name,attributes)
    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        throw new Exception("Invalid format")
    }

    @Override
    protected void nodeCompleted(Object parent, Object node) {
        level--
        if(node=='buildnode'){
            println result
        }
    }

    /**
     * BuildSupport 不会像处理缺失的方法那样处理确缺失的属性，可以使用propertyMissing方法来处理那些情况
     *
     * 处理 未知属性 如
     *  Buy_New_Mac 内的 Install_QuickSilver Install_TextMate
     *  Install_Groovy 内的 Run_all_tests
     *
     */
    def propertyMissing(String name){
        handle(name,[:])
        level--
    }


    def handle(String name,attributes){
        level++
        level.times{result << " "}
        result << placeXifStatusDone(attributes)//status:'done' 用 X
        result << name.replaceAll("_"," ")//Prepare_Vacation-->Prepare Vacation
        result << printParameters(attributes)
        result << "\n"
        name
    }

    def placeXifStatusDone(attributes){
        attributes['status']=='done'?'X ':'- '
    }

    def printParameters(attributes){
        def values=""
        if(attributes.size()>0){
            values+=" ["
            def count=0
            attributes.each{key,value->
                if(key=='status') return
                count++
                values+=(count>1?" ":"")
                values+="${key}: ${value}"
            }
            values+="]"
        }
        values
    }


}

bldr=new TodoBuilderWithSupport()

bldr.build {
    Prepare_Vacation(start: '02/15',end: '02/22') {
        Reserve_Flight(pn:'01/01',status:'done')
        Reserve_Hotel(on:'01/02')
        Reserve_Car(on:'01/02')
    }

    Buy_New_Mac {
        Install_QuickSilver
        Install_TextMate
        Install_Groovy{
            Run_all_tests
        }
    }
}
