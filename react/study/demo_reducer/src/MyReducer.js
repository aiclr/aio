import { useState } from 'react'

function ContactList({ contacts, selectedId, dispatch }) {
    return (
        <section className="contact-list">
            <ul>
                {contacts.map((contact) => (
                    <li key={contact.id}>
                        <button
                            onClick={() => {
                                dispatch({
                                    type: 'changed_selection',
                                    contactId: contact.id
                                })
                            }}>
                            {selectedId === contact.id ? <b>{contact.name}</b> : contact.name}
                        </button>
                    </li>
                ))}
            </ul>
        </section>
    );
}


function Chat({ contact, message, dispatch }) {

    return (
        <section className="chat">
            <textarea
                value={message}
                placeholder={'Chat to ' + contact.name}
                onChange={(e) => {
                    dispatch({
                        type: 'edited_message',
                        message: e.target.value
                    })
                }}
            />
            <br />
            <button onClick={() => {
                alert(`Sending "${message}" to ${contact.email}`);
                dispatch({
                    type: 'sent_message'
                })
            }}>Send to {contact.email}</button>
        </section>
    );
}

// 手动实现 reducer

function useReducer(reducer, initialState) {
    const [state, setState] = useState(initialState);

    const dispatch = (action) => {
        setState(reducer(state, action))
    }

    return [state, dispatch];
}

const initialState = {
    selectedId: 0,
    messages: {
        0: 'Hello, Taylor',
        1: 'Hello, Alice',
        2: 'Hello, Bob',
    },
};

function messengerReducer(state, action) {
    switch (action.type) {
        case 'changed_selection': {
            return {
                ...state,
                selectedId: action.contactId,
            };
        }
        case 'edited_message': {
            return {
                ...state,
                messages: {
                    ...state.messages,
                    [state.selectedId]: action.message,
                },
            };
        }
        case 'sent_message': {
            return {
                ...state,
                messages: {
                    ...state.messages,
                    [state.selectedId]: '',
                },
            };
        }
        default: {
            throw Error('Unknown action: ' + action.type);
        }
    }
}
export function MessengerWithMyReducer() {
    const [state, dispatch] = useReducer(messengerReducer, initialState);
    const message = state.messages[state.selectedId];
    const contact = contacts.find((c) => c.id === state.selectedId);
    return (
        <div>
            <ContactList
                contacts={contacts}
                selectedId={state.selectedId}
                dispatch={dispatch}
            />
            <Chat
                key={contact.id}
                message={message}
                contact={contact}
                dispatch={dispatch}
            />
        </div>
    );
}

const contacts = [
    { id: 0, name: 'Taylor', email: 'taylor@mail.com' },
    { id: 1, name: 'Alice', email: 'alice@mail.com' },
    { id: 2, name: 'Bob', email: 'bob@mail.com' },
];
