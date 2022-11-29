'use strict';

function MyButton() {
    return (
        <button>
            I'm a button
        </button>
    );
}

export default function MyApp() {
    return (
            <div>
                <h1>Welcome to my app</h1>
                <MyButton/>
            </div>
    );
}

//file:///home/leo/code/bougainvilleas/react/h5/app.html
const rootNode = document.getElementById('app-root');
const root = ReactDOM.createRoot(rootNode);
root.render(React.createElement(MyApp));