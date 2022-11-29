import React from 'react'
const allMessage=[
    {id:1,title:'message001',content:'hello world'},
    {id:2,title:'message002',content:'hello java'},
    {id:3,title:'message003',content:'hello react'}
    ]

export default function MessageDetail(props) {
    const {id}= props.match.params
    //find函数，返回第一个结果为true的数组元素
    const message=allMessage.find((m)=>m.id===id*1)
    return(
        <ul>
            <li>ID:{message.id}</li>
            <li>Title:{message.title}</li>
            <li>Content:{message.content}</li>
        </ul>
    )
}