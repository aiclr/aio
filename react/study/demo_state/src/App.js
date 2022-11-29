import { useState } from 'react';
import { sculptureList } from './data.js';
import MyGallery from "./Gallery";
import { Challenge1, Challenge1Error } from "./Challenge1";
import { Challenge2, Challenge2Error } from "./Challenge2";
import { Challenge3, FeedbackForm, FeedbackFormError } from "./Challenge3";
import { Challenge4, Challenge4Error } from "./Challenge4";
import { Counter, Form, TrafficLight } from "./CallSetState";
import { CounterPro } from "./BatchStatePut";
import { RequestTracker } from "./requestCounter";
import { MyProcessQueue } from "./processQueue";
import { MovingDot } from "./MovingDot";
import { MyForm } from "./MyForm";
import { NestedObjectStateForm, NestedObjectStateFormUseImmer } from "./NestedObjectState";
import { Canvas } from "./Box";
import { CanvasUseImmer } from "./BoxUseImmer";
import { List, ShapeEditor, CounterList, InsertList, ReverseList, BucketList, BucketListUseImmer } from "./ArrayState";
import { ShoppingCart ,ShoppingCartUseImmer} from './StateChallenge'
import { TaskApp,TaskAppUseImmer } from './TaskList';

export default function Gallery() {
    const [index, setIndex] = useState(0);
    const [showMore, setShowMore] = useState(false);

    function handleClick() {
        setIndex(index + 1);
    }

    function handleMoreClick() {
        setShowMore(!showMore);
    }

    let sculpture = sculptureList[index];
    return (
        <>

            
            <p>数组作为状态下 修改数组 移动紫色圆色块</p>
            <ShapeEditor />
            <hr />
            <p>作业-待办</p>
            <TaskApp/>
            <TaskAppUseImmer/>
            <p>作业-数组增删</p>
            <ShoppingCart />
            <ShoppingCartUseImmer />
            <hr />
            <BucketListUseImmer />
            <p>复杂object 数组作为状态下 使用use-immer更新元素</p>
            <BucketListUseImmer />
            <p>复杂object 数组作为状态下 使用use-immer更新元素</p>
            <BucketListUseImmer />
            <p>复杂object 数组作为状态下 浅拷贝 会造成问题</p>
            <BucketList />
            <hr />
            <p>数组作为状态下 对数组进行反转</p>
            <ReverseList />
            <hr />
            <p>数组作为状态下 往数组中间插入值</p>
            <InsertList />
            <hr />
            <p>数组作为状态下 替换数组中的值</p>
            <CounterList />
            <hr />

            <p>数组作为状态下 添加删除数组</p>
            <List />
            <hr />
            <CanvasUseImmer />
            <Canvas />
            <hr />
            <p>使用 use-immer 处理复合对象状态更新</p>
            <NestedObjectStateFormUseImmer />
            <hr />
            <NestedObjectStateForm />
            <hr />
            <MyForm />
            <hr />
            <MovingDot />
            <hr />
            <MyProcessQueue />
            <hr />
            <RequestTracker />
            <hr />
            <button onClick={handleClick}>
                Next
            </button>
            <h2>
                <i>{sculpture.name} </i>
                by {sculpture.artist}
            </h2>
            <h3>
                ({index + 1} of {sculptureList.length})
            </h3>
            <button onClick={handleMoreClick}>
                {showMore ? 'Hide' : 'Show'} details
            </button>
            {showMore && <p>{sculpture.description}</p>}
            <img
                src={sculpture.url}
                alt={sculpture.alt}
            />
            <hr />
            <MyGallery />
            <hr />
            <MyGallery />
            <hr />
            <p>超出边界的 next 操作</p>
            <Challenge1Error />
            <p>控制边界的 next 操作</p>
            <Challenge1 />
            <p>无法输入的有问题的表单</p>
            <Challenge2Error />
            <p>使用 state 后 表单可输入了</p>
            <Challenge2 />
            <p>会抛出异常的 state 渲染</p>
            <FeedbackFormError />
            <p>自己的修复后的 state 渲染</p>
            <Challenge3 />
            <p>官方的修复后的 state 渲染</p>
            <FeedbackForm />
            <p>不必要的state造成的bug</p>
            <Challenge4Error />
            <p>删除不需要的state</p>
            <hr />
            <Challenge4 />
            <p>同一个事件处理函数多次调用setState</p>
            <Counter />
            <hr />
            <Form />
            <hr />
            <TrafficLight />
            <hr />
            <CounterPro />
        </>
    );
}