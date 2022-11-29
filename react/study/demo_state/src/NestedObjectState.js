import { useState } from 'react';
//npm install use-immer --save
// 协助处理嵌套 对象 状态更新 。会生成 新对象 用于状态更新
import { useImmer } from 'use-immer';

export function NestedObjectStateForm() {
    const [person, setPerson] = useState({
        name: 'Niki de Saint Phalle',
        artwork: {
            title: 'Blue Nana',
            city: 'Hamburg',
            image: './logo192.png',
        }
    });

    function handleNameChange(e) {
        setPerson({
            ...person,
            name: e.target.value
        });
    }

    function handleArtWorkChange(e) {
        setPerson({
            ...person,
            artwork: {
                ...person.artwork,
                [e.target.name]: e.target.value
            }
        });
    }

    return (
        <>
            <label>
                Name:
                <input
                    value={person.name}
                    onChange={handleNameChange}
                />
            </label>
            <label>
                Title:
                <input
                    name='title'
                    value={person.artwork.title}
                    onChange={handleArtWorkChange}
                />
            </label>
            <label>
                City:
                <input
                    name='city'
                    value={person.artwork.city}
                    onChange={handleArtWorkChange}
                />
            </label>
            <label>
                Image:
                <input
                    name='image'
                    value={person.artwork.image}
                    onChange={handleArtWorkChange}
                />
            </label>
            <p>
                <i>{person.artwork.title}</i>
                {' by '}
                {person.name}
                <br />
                (located in {person.artwork.city})
            </p>
            <img
                src={person.artwork.image}
                alt={person.artwork.title}
            />
        </>
    );
}



export function NestedObjectStateFormUseImmer() {

    const [person, updatePerson] = useImmer({
        name: 'Niki de Saint Phalle',
        artwork: {
            title: 'Blue Nana',
            city: 'Hamburg',
            image: './logo192.png',
        }
    });

    function handleNameChange(e) {
        updatePerson(draft => {
            draft.name = e.target.value;
        });
    }

    function handleArtWorkChange(e) {
        updatePerson(draft => {
            draft.artwork = {
                ...draft.artwork,
                [e.target.name]: e.target.value
            };
        });
    }

    return (
        <>
            <label>
                Name:
                <input
                    value={person.name}
                    onChange={handleNameChange}
                />
            </label>
            <label>
                Title:
                <input
                    name='title'
                    value={person.artwork.title}
                    onChange={handleArtWorkChange}
                />
            </label>
            <label>
                City:
                <input
                    name='city'
                    value={person.artwork.city}
                    onChange={handleArtWorkChange}
                />
            </label>
            <label>
                Image:
                <input
                    name='image'
                    value={person.artwork.image}
                    onChange={handleArtWorkChange}
                />
            </label>
            <p>
                <i>{person.artwork.title}</i>
                {' by '}
                {person.name}
                <br />
                (located in {person.artwork.city})
            </p>
            <img
                src={person.artwork.image}
                alt={person.artwork.title}
            />
        </>
    );
}
