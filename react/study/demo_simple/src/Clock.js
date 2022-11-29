// hava bug component
export function Clock({time}) {
    let hours = time.getHours();
    if (hours >= 0 && hours <= 6) {
        document.getElementById('time').className = 'night';
    } else {
        document.getElementById('time').className = 'day';
    }
    return (
        <h1 id="time">
            {time.toLocaleTimeString()}
        </h1>
    );
}

export function FixClock({time}) {
    let hours = time.getHours();
    let className='day';

    if (hours >= 0 && hours <= 6) {
        className = 'night';
    }
    return (
            <h1 id="time" className={className}>
                {time.toLocaleTimeString()}
            </h1>
            );
}
