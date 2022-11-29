// 事件处理函数 传递 与 调用
export function LightSwitch() {
    function handleClick() {
        let bodyStyle = document.body.style;
        if (bodyStyle.backgroundColor === 'black') {
            bodyStyle.backgroundColor = 'white';
        } else {
            bodyStyle.backgroundColor = 'black';
        }
    }

    return (
            <>
            <button onClick={handleClick}>
                Toggle the lights
            </button>
            <button onClick={()=>{handleClick()}}>
                Toggle the lights
            </button>
            </>

    );
}
