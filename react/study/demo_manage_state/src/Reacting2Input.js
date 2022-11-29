import { useState } from 'react';

export function Form() {
  const [answer, setAnswer] = useState('');
  const [error, setError] = useState(null);
  const [status, setStatus] = useState('typing');

  //async + await 异步写法
  //参考 https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Statements/async_function
  async function handleSubmit(e) {
    e.preventDefault();
    setStatus('submitting');
    try {
      await submitForm(answer);
      setStatus('success');
    } catch (err) {
      //catch 这个错误返回 reject(new Error('Good guess but a wrong answer. Try again!'));
      setStatus('typing');
      setError(err);
    }
  }
  // 与 handleSubmit 写法效果一样
  // Promise 调用链写法
  // 参考 https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Statements/async_function
  function handleSubmit2(e) {
    e.preventDefault();
    setStatus('submitting');
    submitForm(answer)
    .then(()=>setStatus('success'))
    .catch((err)=>{
      setStatus('typing');
      setError(err);
    });
  }

  if (status === 'success') {
    return <h1>That's right!</h1>
  }

  function handleTextareaChange(e) {
    setAnswer(e.target.value);
  }

  return (
    <>
      <h2>City quiz</h2>
      <p>
        In which city is there a billboard that turns air into drinkable water?
      </p>
      <form onSubmit={handleSubmit2}>
        <textarea
          value={answer}
          onChange={handleTextareaChange}
          disabled={status === 'submitting'}
        />
        <br />
        <button disabled={
          answer.length === 0 ||
          status === 'submitting'
        }>
          Submit
        </button>
        {error !== null &&
          <p className="Error">
            {error.message}
          </p>
        }
      </form>
    </>
  );
}

function submitForm(answer) {
  // Pretend it's hitting the network.
  // Promise 对象用于表示一个异步操作的最终完成（或失败）及其结果值
  // https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Promise
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      let shouldError = answer.toLowerCase() !== 'lima'
      if (shouldError) {
        reject(new Error('Good guess but a wrong answer. Try again!'));
      } else {
        resolve();// 正常返回
      }
    }, 1500);
  });
}


