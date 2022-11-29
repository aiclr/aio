import { useState } from 'react';

export function SyncedInputs() {

    const [text, setText] = useState('');

    function handleInput(e) {
        setText(e.target.value);
    }

    return (
        <>
            <Input label="First input" text={text} change={handleInput} />
            <Input label="Second input" text={text} change={handleInput} />
        </>
    );
}

function Input({ label, text, change }) {

    return (
        <label>
            {label}
            {' '}
            <input
                value={text}
                onChange={change}
            />
        </label>
    );
}
