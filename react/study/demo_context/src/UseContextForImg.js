import { useState, useContext } from 'react';
import { places } from './data';
import { getImageUrl } from './utils';
import { ImageSize } from './LevelContext';

// 所有 子dom 都可以通过 const imageSize = useContext(ImageSize);
// 获取到 context
export function UseContextForImg() {
    const [isLarge, setIsLarge] = useState(false);
    const imageSize = isLarge ? 150 : 100;
    return (
        <ImageSize.Provider value={imageSize}>
            <label>
                <input
                    type="checkbox"
                    checked={isLarge}
                    onChange={e => {
                        setIsLarge(e.target.checked);
                    }}
                />
                Use large images
            </label>
            <hr />
            <List />
        </ImageSize.Provider>
    )
}

function List() {
    const listItems = places.map(place =>
        <li key={place.id}>
            <Place
                place={place}
            />
        </li>
    );
    return <ul>{listItems}</ul>;
}

function Place({ place }) {
    return (
        <>
            <PlaceImage
                place={place}
            />
            <p>
                <b>{place.name}</b>
                {': ' + place.description}
            </p>
        </>
    );
}

function PlaceImage({ place }) {
    const imageSize = useContext(ImageSize);
    return (
        <img
            src={getImageUrl(place)}
            alt={place.name}
            width={imageSize}
            height={imageSize}
        />
    );
}
