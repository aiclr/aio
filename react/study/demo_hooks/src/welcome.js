import { useState, useEffect, useRef } from 'react';
import './welcome.css'
import { FadeInAnimation } from './animation.js';

function Welcome() {
  const ref = useRef(null);

  useEffect(() => {
    const duration = 1000;
    const node = ref.current;

    let startTime = performance.now();
    let frameId = null;

    function onFrame(now) {
      const timePassed = now - startTime;
      const progress = Math.min(timePassed / duration, 1);
      onProgress(progress);
      if (progress < 1) {
        // We still have more frames to paint
        frameId = requestAnimationFrame(onFrame);
      }
    }

    function onProgress(progress) {
      node.style.opacity = progress;
    }

    function start() {
      onProgress(0);
      startTime = performance.now();
      frameId = requestAnimationFrame(onFrame);
    }

    function stop() {
      cancelAnimationFrame(frameId);
      startTime = null;
      frameId = null;
    }

    start();
    return () => stop();
  }, []);

  return (
    <h1 className="welcome" ref={ref}>
      Welcome
    </h1>
  );
}

//将 useEffect 渲染抽出为 useFadeIn

function useFadeIn(ref) {
  useEffect(() => {
    const duration = 1000;
    const node = ref.current;

    let startTime = performance.now();
    let frameId = null;

    function onFrame(now) {
      const timePassed = now - startTime;
      const progress = Math.min(timePassed / duration, 1);
      onProgress(progress);
      if (progress < 1) {
        // We still have more frames to paint
        frameId = requestAnimationFrame(onFrame);
      }
    }

    function onProgress(progress) {
      node.style.opacity = progress;
    }

    function start() {
      onProgress(0);
      startTime = performance.now();
      frameId = requestAnimationFrame(onFrame);
    }

    function stop() {
      cancelAnimationFrame(frameId);
      startTime = null;
      frameId = null;
    }

    start();
    return () => stop();
  }, [ref]);
}



function Welcome1() {
  const ref = useRef(null);
  useFadeIn(ref);
  return (
    <h1 className="welcome" ref={ref}>
      Welcome1
    </h1>
  );
}


// 将动画渲染 从 useFadeIn 抽出为 JavaScript class
// import { FadeInAnimation } from './animation.js';

export function useFadeIn2(ref) {
  useEffect(() => {
    const animation = new FadeInAnimation(ref.current);
    animation.start(1000);
    return () => {
      animation.stop();
    };
  }, [ref]);
}



function Welcome2() {
  const ref = useRef(null);
  useFadeIn2(ref);
  return (
    <h1 className="welcome" ref={ref}>
      Welcome2
    </h1>
  );
}

// css 动画
function Welcomecss() {
  return (
    <h1 className="welcomecss">
      Welcome CSS
    </h1>
  );
}

export function Animation() {
  const [show, setShow] = useState(false);
  return (
    <>
      <button onClick={() => setShow(!show)}>
        {show ? 'Remove' : 'Show'}
      </button>
      <hr />
      {show && <Welcome />}
      {show && <Welcome1 />}
      {show && <Welcome2 />}
      <p> css 超简单写法 </p>
      {show && <Welcomecss />}
    </>
  );
}


