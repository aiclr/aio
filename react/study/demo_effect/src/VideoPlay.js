import { useState, useRef, useEffect } from 'react';

// useEffect 如何与外部组件同步
// Effect will run after every render 
//组件渲染之后（组件出现在屏幕上之后）执行 useEffect
// 依赖项数组 可以包含多个依赖项，
// 如果指定的所有依赖项的值与上一次渲染时的值完全相同，react 将跳过重新运行 useEffect
// react 使用 Object.is 比较依赖关系值
// 
// useEffect(() => {
// This runs after every render 每次渲染后执行
// });

// useEffect(() => {
// This runs only on mount (when the component appears) 组件显示在屏幕上后执行
// }, []);

// useEffect(() => {
// This runs on mount *and also* if either a or b have changed since the last render
// }, [a, b]);

function VideoPlayer({ src, isPlaying }) {
  const ref = useRef(null);

  // 将 [isPlaying]  指定为依赖项数组告诉 React 
  // 如果 isPlaying 与上次渲染时相同，则应跳过重新运行 useEffect
  // 本例中 input 输入内容不会导致 useEffect 重新运行
  // 但是按'Pause'或'Play'按钮，会改变 isPlaying 会重新运行 useEffect
  useEffect(() => {
    if (isPlaying) {//It's used here... 与依赖项数组呼应
      console.log('Calling video.play()');
      ref.current.play();
    } else {
      console.log('Calling video.pause()');
      ref.current.pause();
    }
  }, [isPlaying]);//依赖项数组。与 if 判断条件 isPlaying 呼应 ..so it must be declared here!

  return <video ref={ref} src={src} loop playsInline />;
}

export function Play() {
  const [isPlaying, setIsPlaying] = useState(false);
  const [text, setText] = useState('');
  return (
    <>
      <input value={text} onChange={e => setText(e.target.value)} />
      <button onClick={() => setIsPlaying(!isPlaying)}>
        {isPlaying ? 'Pause' : 'Play'}
      </button>
      <br />
      <VideoPlayer
        isPlaying={isPlaying}
        src="https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"
      />
    </>
  );
}
