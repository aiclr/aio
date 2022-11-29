import { useState } from 'react';
import './ProfileEditor.css'

export function ProfileEditor() {

    const [firstName, setFirstName] = useState('Jane');
    const [lastName, setLastName] = useState('Jacobs');
    const [edit, setEdit] = useState(false);
    
    return (
        <form id="form"
            onSubmit={e => {
                e.preventDefault();
                setEdit(!edit);
            }}
        >
            <label>
                First name:
                <b
                    id='firstNameText'
                    style={{ display: edit ? "none" : "" }}
                >
                    {firstName}
                </b>
                <input
                    id='firstNameInput'
                    value={firstName}
                    style={{ display: edit ? "" : "none" }}
                    onChange={(e) => setFirstName(e.target.value)}
                />
            </label>
            <label>
                Last name:
                <b
                    id='lastNameText'
                    style={{ display: edit ? "none" : "inline" }}
                >
                    {lastName}
                </b>
                <input
                    id='lastNameInput'
                    value={lastName}
                    style={{ display: edit ? "inline" : "none" }}
                    onChange={(e) => setLastName(e.target.value)}
                />
            </label>
            <button
                type="submit"
                id="editButton"
            >
                {edit ? 'Save' : 'Edit'} Profile
            </button>
            <p><i id="helloText">Hello, {firstName} {lastName}!</i></p>
        </form>
    );
}