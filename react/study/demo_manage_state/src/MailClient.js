import { useState } from 'react';
import './MailClient.css'

const initialLetters = [{
    id: 0,
    subject: 'Ready for adventure?',
    isStarred: true,
}, {
    id: 1,
    subject: 'Time to check in!',
    isStarred: false,
}, {
    id: 2,
    subject: 'Festival Begins in Just SEVEN Days!',
    isStarred: false,
}];

function Letter({
    letter,
    isHighlighted,
    onHover,
    onToggleStar,
}) {
    return (
        <li
            className={isHighlighted ? 'highlighted' : ''}
            onFocus={() => { onHover(letter.id); }}
            onPointerMove={() => { onHover(letter.id); }}
        >
            <button onClick={() => { onToggleStar(letter.id); }}>
                {letter.isStarred ? 'Unstar' : 'Star'}
            </button>
            {letter.subject}
        </li>
    )
}



export function MailClient() {

    const [letters, setLetters] = useState(initialLetters);
    const [highlightedLetterId, setHighlightedLetterId] = useState(null);

    function handleHover(id) {
        setHighlightedLetterId(id);
    }

    function handleStar(starredId) {
        setLetters(
            letters.map(letter => {
                if (letter.id === starredId) {
                    return {
                        ...letter,
                        isStarred: !letter.isStarred
                    };
                } else {
                    return letter;
                }
            }));
    }

    return (
        <>
            <h2>Inbox</h2>
            <ul>
                {letters.map(letter => (
                    <Letter
                        key={letter.id}
                        letter={letter}
                        isHighlighted={
                            letter.id === highlightedLetterId
                        }
                        onHover={handleHover}
                        onToggleStar={handleStar}
                    />
                ))}
            </ul>
        </>
    );
}



function Letter2({
    letter,
    onToggle,
    isSelected,
}) {
    return (
        <li className={isSelected ? 'selected' : ''}>
            <label>
                <input
                    type="checkbox"
                    checked={isSelected}
                    onChange={() => {
                        onToggle(letter.id);
                    }}
                />
                {letter.subject}
            </label>
        </li>
    )
}
export function MailClient2() {
    const [selectedIds, setSelectedIds] = useState([]);

    const selectedCount = selectedIds.length;

    function handleToggle(toggledId) {
        if (selectedIds.includes(toggledId)) {
            setSelectedIds(selectedIds.filter(id => id !== toggledId));
        } else {
            setSelectedIds([
                ...selectedIds,
                toggledId
            ])
        }
    }

    return (
        <>
            <h2>Inbox</h2>
            <ul>
                {initialLetters.map(letter => (
                    <Letter2
                        key={letter.id}
                        letter={letter}
                        isSelected={selectedIds.includes(letter.id)}
                        onToggle={handleToggle}
                    />
                ))}
                <hr />
                <p>
                    <b>
                        You selected {selectedCount} letters
                    </b>
                </p>
            </ul>
        </>
    );
}