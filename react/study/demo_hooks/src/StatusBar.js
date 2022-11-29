import { useState, useEffect, useSyncExternalStore } from 'react';


// useEffect 监听是否断网 有bug 如果启动前已断网则出现异常
export function StatusBar() {
    const [isOnline, setIsOnline] = useState(true);
    useEffect(() => {
        function handleOnline() {
            setIsOnline(true);
        }
        function handleOffline() {
            setIsOnline(false);
        }
        window.addEventListener('online', handleOnline);
        window.addEventListener('offline', handleOffline);
        return () => {
            window.removeEventListener('online', handleOnline);
            window.removeEventListener('offline', handleOffline);
        };
    }, []);

    return <h1>{isOnline ? '✅ Online' : '❌ Disconnected'}</h1>;
}


// useEffect 监听是否断网 有bug 如果启动前已断网则出现异常
export function SaveButton() {
    const [isOnline, setIsOnline] = useState(true);
    useEffect(() => {
        function handleOnline() {
            setIsOnline(true);
        }
        function handleOffline() {
            setIsOnline(false);
        }
        window.addEventListener('online', handleOnline);
        window.addEventListener('offline', handleOffline);
        return () => {
            window.removeEventListener('online', handleOnline);
            window.removeEventListener('offline', handleOffline);
        };
    }, []);

    function handleSaveClick() {
        console.log('✅ Progress saved');
    }


    return (
        <button disabled={!isOnline} onClick={handleSaveClick}>
            {isOnline ? 'Save progress' : 'Reconnecting...'}
        </button>
    );
}

// 抽出公用代码 做成自定义 hooks 命名规则必须use开头
// 只有 hooks 可以调用其他 hooks （自定义 or React 提供的 useState）
// 如果改名 getOnlineStatus 则不是hooks 不能在内部使用 useState
// 有bug 如果启动前已断网则出现异常
function useOnlineStatus() {
    const [isOnline, setIsOnline] = useState(true);
    useEffect(() => {
        function handleOnline() {
            setIsOnline(true);
        }
        function handleOffline() {
            setIsOnline(false);
        }
        window.addEventListener('online', handleOnline);
        window.addEventListener('offline', handleOffline);
        return () => {
            window.removeEventListener('online', handleOnline);
            window.removeEventListener('offline', handleOffline);
        };
    }, []);
    return isOnline;
}


export function StatusBar2() {
    const isOnline = useOnlineStatus();
    return <h1>{isOnline ? '✅ Online' : '❌ Disconnected'}</h1>;
}

export function SaveButton2() {
    const isOnline = useOnlineStatus();

    function handleSaveClick() {
        console.log('✅ Progress saved');
    }

    return (
        <button disabled={!isOnline} onClick={handleSaveClick}>
            {isOnline ? 'Save progress' : 'Reconnecting...'}
        </button>
    );
}

/////////

function subscribe(callback) {
    window.addEventListener('online', callback);
    window.addEventListener('offline', callback);
    return () => {
        window.removeEventListener('online', callback);
        window.removeEventListener('offline', callback);
    };
}
// 使用 useSyncExternalStore 解决启动前断网出现的异常
function useOnlineStatusPlus() {
    return useSyncExternalStore(
        subscribe,
        () => navigator.onLine, // How to get the value on the client
        () => true // How to get the value on the server
    );
}

export function StatusBar3() {
    const isOnline = useOnlineStatusPlus();
    return <h1>{isOnline ? '✅ Online' : '❌ Disconnected'}</h1>;
}

export function SaveButton3() {
    const isOnline = useOnlineStatusPlus();

    function handleSaveClick() {
        console.log('✅ Progress saved');
    }

    return (
        <button disabled={!isOnline} onClick={handleSaveClick}>
            {isOnline ? 'Save progress' : 'Reconnecting...'}
        </button>
    );
}