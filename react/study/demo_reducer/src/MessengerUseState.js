import { useState } from 'react';

const contacts = [
    { id: 0, name: 'Taylor', email: 'taylor@mail.com' },
    { id: 1, name: 'Alice', email: 'alice@mail.com' },
    { id: 2, name: 'Bob', email: 'bob@mail.com' },
];


function ContactList({ contacts,state, onClick }) {


    return (
        <section className="contact-list">
            <ul>
                {contacts.map((contact) => (
                    <li key={contact.id}>
                        <button
                            onClick={()=>onClick({
                                ...state,
                                selectedId: contact.id
                            })}>
                            {state.selectedId === contact.id ? <b>{contact.name}</b> : contact.name}
                        </button>
                    </li>
                ))}
            </ul>
        </section>
    );
}


function Chat({ contact,state, message, onChange }) {

    return (
        <section className="chat">
            <textarea
                value={message}
                placeholder={'Chat to ' + contact.name}
                onChange={(e) => {
                    onChange({
                        ...state,
                        messages: {
                            ...state.messages,
                            [contact.id]: e.target.value
                        }
                    })
                }}
            />
            <br />
            <button onClick={() => {
                alert(`Sending "${message}" to ${contact.email}`);
                onChange({
                    ...state,
                    messages: {
                        ...state.messages,
                        [state.selectedId]: '',
                    }
                })
            }}>Send to {contact.email}</button>
        </section>
    );
}

const initialState = {
    selectedId: 0,
    messages: {
        0: 'Hello, Taylor',
        1: 'Hello, Alice',
        2: 'Hello, Bob',
    },
};

export function MessengerUseState() {
    const [state, setState] = useState(initialState);
    const message = state.messages[state.selectedId];
    const contact = contacts.find((c) => c.id === state.selectedId);
    

    return (
        <div>
            <ContactList
                contacts={contacts}
                state={state}
                onClick={setState}
            />
            <Chat
                key={contact.id}
                state={state}
                message={message}
                contact={contact}
                onChange={setState}
            />
        </div>
    );
}

