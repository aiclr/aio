import {useState} from "react";

export function SwitchColor() {
    const [count,setCount]=useState(0);

    function handleDivClick(){
        setCount(count+1);
    }

    function handleClick() {
        let bodyStyle = document.body.style;
        if (bodyStyle.backgroundColor === 'gray') {
            bodyStyle.backgroundColor = 'white';
        } else {
            bodyStyle.backgroundColor = 'gray';
        }
    }

    return (
            <div id='color' onClick={handleDivClick} color={'red'}>
            <ColorSwitch onChangeColor={handleClick}>
                Toggle the lights
            </ColorSwitch>
            <h4>Clicks on the page: {count}</h4>
        </div>

    );
}


function ColorSwitch({onChangeColor}) {
    return (
        <button onClick={e => {
            e.stopPropagation();
            onChangeColor();
        }}>
            Change color
        </button>
    );
}
