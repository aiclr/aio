//事件传播 Event Propagation
// 点击 按钮
// 会先执行按钮的事件处理程序
// 后执行 div的onClick 事件处理程序
export default function EventPropagation() {
    return (
        <div className="Toolbar" onClick={() => {
            alert('You clicked on the toolbar!');
        }}>
            <button onClick={() => alert('Playing!')}>
                Play Movie
            </button>
            <button onClick={() => alert('Uploading!')}>
                Upload Image
            </button>
        </div>
    );
}

// 阻止事件传播
// e.stopPropagation(); 防止事件进一步 传递
// click();//调用函数
// 父 <div> 的 onClick 处理程序不会执行

function Button({click, children}) {
    return (
        <button onClick={e => {
            e.stopPropagation();//阻止事件传播
            click();//调用函数
        }}>
            {children}
        </button>
    );
}

export function StopEventPropagation() {
    return (
        <div className="Toolbar" onClick={() => {
            alert('You clicked on the toolbar!');
        }}>
            <Button click={() => alert('Playing!')}>
                Play Movie
            </Button>
            <Button click={() => alert('Uploading!')}>
                Upload Image
            </Button>
        </div>
    );
}

// 强制捕获子组件的事件
// 先执行 div onClickCapture 处理函数
// 再执行 button onClick
export function CaptureEvent() {
    return (
        <div onClickCapture={() => {
            alert('You clicked on the toolbar!');
        }}>
            <button onClick={e => {
                e.stopPropagation();
                alert('You clicked on the button!');
            }}>强制捕获子组件的事件
            </button>
        </div>
    )
}
