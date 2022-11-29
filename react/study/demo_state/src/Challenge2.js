import {useState} from "react";

export function Challenge2() {
    const [firstName,setFirstName]=useState('')
    const [lastName,setLastName]=useState('')


    function handleFirstNameChange(e) {
        setFirstName(e.target.value);
    }

    function handleLastNameChange(e) {
        setLastName(e.target.value);
    }

    function handleReset() {
        setFirstName('');
        setLastName('');
    }

    return (
            <form onSubmit={e => e.preventDefault()}>
                <input
                    placeholder="First name"
                    value={firstName}
                    onChange={handleFirstNameChange}
                />
                <input
                    placeholder="Last name"
                    value={lastName}
                    onChange={handleLastNameChange}
                />
                <h1>Hi, {firstName} {lastName}</h1>
                <button onClick={handleReset}>Reset</button>
            </form>
            );
}

// 错误例子
export function Challenge2Error() {
    let firstName = '';
    let lastName = '';

    function handleFirstNameChange(e) {
        firstName = e.target.value;
    }

    function handleLastNameChange(e) {
        lastName = e.target.value;
    }

    function handleReset() {
        firstName = '';
        lastName = '';
    }

    return (
            <form onSubmit={e => e.preventDefault()}>
                <input
                    placeholder="First name"
                    value={firstName}
                    onChange={handleFirstNameChange}
                />
                <input
                    placeholder="Last name"
                    value={lastName}
                    onChange={handleLastNameChange}
                />
                <h1>Hi, {firstName} {lastName}</h1>
                <button onClick={handleReset}>Reset</button>
            </form>
            );
}
