import {useState} from 'react';
import {sculptureList} from './data.js';

export function Challenge1() {
    const [index, setIndex] = useState(0);
    const [showMore, setShowMore] = useState(false);

    let hasPrev = index > 0;
    let hasNext = index < sculptureList.length - 1;

    function handlePrevClick() {
        if (hasPrev) {
            setIndex(index - 1);
        }
    }

    function handleNextClick() {
        if (hasNext) {
            setIndex(index + 1);
        }
    }

    function handleMoreClick() {
        setShowMore(!showMore);
    }

    let sculpture = sculptureList[index];
    return (
        <>
            <button
                onClick={handlePrevClick}
                disabled={!hasPrev}
            >
                Previous
            </button>
            <button
                onClick={handleNextClick}
                disabled={!hasNext}
            >
                Next
            </button>
            <h2>
                <i>{sculpture.name} </i>
                by {sculpture.artist}
            </h2>
            <h3>
                ({index + 1} of {sculptureList.length})
            </h3>
            <button onClick={handleMoreClick}>
                {showMore ? 'Hide' : 'Show'} details
            </button>
            {showMore && <p>{sculpture.description}</p>}
            <img
                src={sculpture.url}
                alt={sculpture.alt}
            />
        </>
    );
}

export function Challenge1Error() {
    const [index, setIndex] = useState(0);
    const [showMore, setShowMore] = useState(false);

    function handleNextClick() {
        setIndex(index + 1000);
    }

    function handleMoreClick() {
        setShowMore(!showMore);
    }

    let sculpture = sculptureList[index];
    return (
            <>
            <button onClick={handleNextClick}>
                Next
            </button>
            <h2>
                <i>{sculpture.name} </i>
                by {sculpture.artist}
            </h2>
            <h3>
                ({index + 1} of {sculptureList.length})
            </h3>
            <button onClick={handleMoreClick}>
                {showMore ? 'Hide' : 'Show'} details
            </button>
            {showMore && <p>{sculpture.description}</p>}
            <img
                src={sculpture.url}
                alt={sculpture.alt}
            />
            </>
            );
}
