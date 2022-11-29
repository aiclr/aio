//assing a function (correct) 传递函数
// <button onClick={handleClick}>
// <button onClick={() => alert('...')}>

//calling a function (incorrect) 调用函数.
//renders (渲染)阶段就会调用 函数
// <button onClick={handleClick()}>
// <button onClick={alert('...')}>

// 传递事件处理逻辑
export default function NoState() {
    return (
        <Toolbar
            onPlayMovie={() => alert('Playing!')}
            onUploadImage={() => alert('Uploading!')}
        />
    );
}

function Toolbar({onPlayMovie, onUploadImage}) {
    return (
        <div>
            <Button onClick={onPlayMovie}>
                Play Movie
            </Button>
            <Button onClick={onUploadImage}>
                Upload Image
            </Button>
        </div>
    );
}

function Button({onClick, children}) {
    return (
        <button onClick={onClick}>
            {children}
        </button>
    );
}