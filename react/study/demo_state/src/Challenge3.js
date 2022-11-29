import {useState} from 'react';

//自己的 解决方案
export function Challenge3() {
    const [isSent, setIsSent] = useState(false);
    const [message, setMessage] = useState('');

    return (
        <>
            <h1 hidden={!isSent}>Thank you!</h1>
            <form onSubmit={e => {
                e.preventDefault();
                alert(`Sending: "${message}"`);
                setIsSent(true);
            }} hidden={isSent} >
                    <textarea
                        placeholder="Message"
                        value={message}
                        onChange={e => setMessage(e.target.value)}
                    />
                <br/>
                <button type="submit">Send</button>
            </form>
        </>
    );
}
//官方 解决方案
export function FeedbackForm() {
    const [isSent, setIsSent] = useState(false);
    const [message, setMessage] = useState('');

    if (isSent) {
        return <h1>Thank you!</h1>;
    } else {
        return (
                <form onSubmit={e => {
                    e.preventDefault();
                    alert(`Sending: "${message}"`);
                    setIsSent(true);
                }}>
                    <textarea
                        placeholder="Message"
                        value={message}
                        onChange={e => setMessage(e.target.value)}
                    />
                    <br />
                    <button type="submit">Send</button>
                </form>
                );
    }
}

//错误的写法
export function FeedbackFormError() {
    const [isSent, setIsSent] = useState(false);
    if (isSent) {
        return <h1>Thank you!</h1>;
    } else {
        // eslint-disable-next-line
        const [message, setMessage] = useState('');
        return (
                <form onSubmit={e => {
                    e.preventDefault();
                    alert(`Sending: "${message}"`);
                    setIsSent(true);
                }}>
                    <textarea
                        placeholder="包含错误的组件"
                        value={message}
                        onChange={e => setMessage(e.target.value)}
                    />
                    <br />
                    <button type="submit">Send</button>
                </form>
                );
    }
}