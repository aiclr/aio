// 默认行为
// <form> submit event,
// which happens when a button inside of it is clicked, will reload the whole page by default:
export function Signup() {
    return (
        <form onSubmit={() => alert('Submitting!')}>
            <input/>
            <button>Send</button>
        </form>
    );
}

export function Signup2() {
    return (
            <form onSubmit={e => {
                e.preventDefault();
                alert('Submitting!');
            }}>
                <input/>
                <button>Send</button>
            </form>
            );
}
