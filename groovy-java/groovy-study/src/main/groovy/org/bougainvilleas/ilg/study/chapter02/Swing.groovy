package org.bougainvilleas.ilg.study.chapter02

import javax.swing.*
import java.awt.*
import java.awt.event.*

/**
 * 2.6 实现接口
 *
 * groovy 可以把一个映射或一个代码块转化为接口
 *
 */



frame = new JFrame(size: [300,300],layout: new FlowLayout(),defaultCloseOperation: javax.swing.WindowConstants.EXIT_ON_CLOSE)
button = new JButton("click")
positionLabel = new JLabel("")
msgLabel = new JLabel("")
frame.contentPane.add button
frame.contentPane.add positionLabel
frame.contentPane.add msgLabel

/**
 java code

 button.addActionListener(
    new ActionListener(){
         public void actionPerformed(ActionEvent ae)
         {
         JOptionPane.showMessageDialog(frame,"You clicked!")
         }
     });

groovy 不需要actionPerformed()方法声明,也不需要显式地用new来创建匿名内部类
 */

button.addActionListener(
        /**
         * as 操作符 相当于实现了 ActionListener 接口
         * 会拦截对接口中任何方法的调用,此处ActionListener 只有一个方法 actionPerformed()
         * 然后将调用路由到下面的代码块
         *
         * 对于多个方法的接口, 如果所有方法的实现都可以相同,按照单方法的实现即可
         */

        {JOptionPane.showMessageDialog(frame,"You clicked!")} as ActionListener
)

//创建变量  displayMouseLocation 指向 代码块. it 表示方法的参数
displayMouseLocation = {positionLabel.setText("$it.x,$it.y")}
/**
 * MouseListener 和  MouseMotionListener总共有七个方法,
 * 此处对所有的方法实现都是上面这一行 displayMouseLocation = {positionLabel.setText("$it.x,$it.y")}
 */
frame.addMouseListener(displayMouseLocation as MouseListener)
frame.addMouseMotionListener(displayMouseLocation as MouseMotionListener)

/**
 * 创建映射 处理有多个方法的接口
 * 此处 focusGained  focusLost 等于 接口的方法名
 * 代码块 是方法实现
 */
handleFocus=[
        focusGained:{
            println "focusGained called"
            msgLabel.setText("Good to see you!")
        },
        focusLost:{
            println "focusLost called"
            msgLabel.setText("Come back soon!")
        }
]

button.addFocusListener(handleFocus as FocusListener)

/**
 * 运行时才能知道接口名字 asType()
 * 通过将实现的接口 Class对象作为一个参数发送给 asType()
 * 把代码块或映射转化为接口方法实现
 */
events=['WindowListener','ComponentListener']
//上面 的数组 可能是动态的,而且可能来自某些输入
handler={msgLabel.setText("$it")}
//想实现的接口,在列表 events,该列表是动态的,假设它会在代码执行期间通过输入来填充.
//事件公共的处理器位于变量 handler 指向的代码块中
//对事件进行遍历,对于每个事件,都使用了 asType() 方法为该接口创建一个实现
//在代码块上调用asType() 方法,一旦有监听器接口实现,就可以通过调用相应的add方法来注册该实现
// 如 "add${event}" ---> addWindowListener
//调用add方法本身就是动态的

for(event in events)
{
    handlerImpl = handler.asType(Class.forName("java.awt.event.${event}"))
    frame."add${event}"(handlerImpl)
}
frame.show()
