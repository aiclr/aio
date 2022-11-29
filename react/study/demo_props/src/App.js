import {getImageUrl} from "./utils";
import MyAvatar from "./MyAvatar"
import Clock from "./Clock"
import GalleryPro from "./Challenges"
import {useState} from "react";
import Challenges2 from "./Challenges2";
import Challenges3 from "./Challenges3";



// React component functions accept a single argument, a props object
// React  组件函数 接受一个单独的参数 一个 props 对象
function Avatar1(props) {
    return (
        <img
            className="avatar"
            src={getImageUrl(props.person)}
            alt={props.person.name}
            width={props.size}
            height={props.size}
        />
    );
}

// Usually you don’t need the whole props object itself, so you destructure it into individual props.
// 通常 不需要整个 props 对象。所以可以将其拆分成单个 props  注意 花括号 {}
function Avatar({person, size}) {
    return (
        <img
            className="avatar"
            src={getImageUrl(person)}
            alt={person.name}
            width={size}
            height={size}
        />
    );
}

// 参数多 复杂
function Profile1({person, size, isSepia, thinkBorder}) {
    return (
        <div className="card">
            <Avatar
                person={person}
                size={size}
                isSepia={isSepia}
                thinkBorder={thinkBorder}
            />
        </div>
    );
}

// 简化版
function Profile2(props) {
    return (
        <div className="card">
            <Avatar {...props}/>
        </div>
    );
}

function Card({children}) {
    return (
        <div className="card">
            {children}
        </div>
    )
}

export default function Profile() {

    const [color, setColor] = useState("lightcoral")
    const [time, setTime] = useState(new Date().toLocaleTimeString())

    function updateTime() {
        setTime(new Date().toLocaleTimeString());
    }

    setInterval(updateTime,1000)

    return (
        <div>
            <h1>Pick a color:
                <select onChange={event => setColor(event.target.value)} defaultValue={color}>
                    <option value="lightcoral">lightcoral</option>
                    <option value="midnightblue">midnightblue</option>
                    <option value="rebeccapurple">rebeccapurple</option>
                </select>
            </h1>
            <Clock color={color} time={time}/>
            <Card>
                <MyAvatar
                    size={100}
                    person={{
                        name: 'Katsuko Saruhashi',
                        imageId: '192'
                    }}
                />
            </Card>

            <Avatar
                size={100}
                person={{
                    name: 'Katsuko Saruhashi',
                    imageId: '192'
                }}
            />
            <Avatar
                size={80}
                person={{
                    name: 'Aklilu Lemma',
                    imageId: '512'
                }}
            />
            <Avatar1
                size={50}
                person={{
                    name: 'Lin Lanying',
                    imageId: '192'
                }}
            />
            <GalleryPro/>
            <Challenges2/>
            <Challenges3/>
        </div>
    );

}