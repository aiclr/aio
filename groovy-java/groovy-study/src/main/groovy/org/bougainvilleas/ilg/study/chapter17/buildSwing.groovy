package org.bougainvilleas.ilg.study.chapter17

/**
 * 大型项目 参考 Griffon
 *
 * 底层仍为 Swing API
 * 优雅简洁的语法糖
 */

bldr=new groovy.swing.SwingBuilder()
//初始化 JFrame 指定title 标题、size大小、layout 布局、设置默认关闭操作
frame=bldr.frame(
    title:'Swing',
    size:[50,100],
        layout:new java.awt.FlowLayout(),
        defaultCloseOperation: javax.swing.WindowConstants.EXIT_ON_CLOSE
){
    lbl=label(text:'test')
    //闭包注册事件处理器
    btn=button(text: 'Click me',actionPerformed:{
        btn.text='Clicked'
        lbl.text='Groovy!'
    })
}
//frame.show()
frame.setVisible(true)
