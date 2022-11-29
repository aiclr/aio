import NoState from "./passEvent";
import ReadPropsInEventHandler from "./readPropsInEventHandler";
import PassEventHandlerAsProps from "./passEventHandlerAsProps";
import {Signup, Signup2} from "./preventDefBehavior";
import EventPropagation, {StopEventPropagation, CaptureEvent} from "./eventPropagation";
import {LightSwitch} from "./Challenge1";
import {SwitchColor} from "./ColorSwitch";
import {MyClock} from "./Clock";

export default function Gallery() {
    return (
        <>
            <h3>渲染</h3>
            <MyClock/>
            <h3>事件处理函数 传递 与 调用</h3>
            <LightSwitch/>
            <SwitchColor/>
            <h3>form 表单的默认行为</h3>
            <h4>默认行为提交后会清空表单</h4>
            <Signup/>
            <h4>阻止默认提交后清空表单行为</h4>
            <Signup2/>

            <h3>事件传播与阻止事件传播</h3>
            <h4>强制捕获子组件的事件</h4>
            <CaptureEvent/>
            <h4>阻止事件传播</h4>
            <StopEventPropagation/>
            <h4>事件传播</h4>
            <EventPropagation/>

            <h3>将事件处理函数作为 props 传递</h3>
            <PassEventHandlerAsProps/>

            <h3>事件处理函数读取 props 属性</h3>
            <ReadPropsInEventHandler/>

            <h3>事件</h3>
            <NoState/>
        </>
    );
}