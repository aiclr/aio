import { useState } from 'react';
import { useImmer } from 'use-immer';
// 修改 原数组的方法 mutates the array
// 添加 push, unshift
// 移除 pop, shift, splice
// 替换 splice, arr[i] = ... assignment
// 排序 reverse, sort

//复制数组属于浅拷贝 不能直接修改数组内的对象 会影响 state

// 返回 新数组的方法 returns a new array
// 添加 concat, [...arr] spread syntax (example)。会返回新数组 
// 移除 filter, slice (example) 。过滤和复制会返回新的数组
// 替换 map (example) 。map 会返回新的数组
// 排序 copy the array first (example) 先复制一个原数组 在新复制的数组上操作



// 增删数组
let nextId = 0;

export function List() {
    const [name, setName] = useState('');
    const [artists, setArtists] = useState([]);

    function handleAddClick() {
        setName('');
        // 添加操作 
        setArtists([
            ...artists,
            { id: nextId++, name: name }
        ]);
    };

    function handleRemoveClick({ artist }) {
        setName('');
        // filter 会返回一个新的 数组
        let newArr = artists.filter(tmp => artist.id !== tmp.id)
        setArtists(newArr)
    };

    return (
        <div>
            <h1>Inspiring sculptors:</h1>
            <input
                value={name}
                onChange={e => setName(e.target.value)}
            />
            <button onClick={handleAddClick}>Add</button>

            <ul>
                {artists.map(artist => (
                    <li key={artist.id}>{artist.name}
                        <button
                            onClick={() => handleRemoveClick(artist)}
                        >
                            remove</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}


// Transforming an array  变换数组

let initialShapes = [
    { id: 0, type: 'circle', x: 50, y: 100 },
    { id: 1, type: 'square', x: 150, y: 100 },
    { id: 2, type: 'circle', x: 250, y: 100 },
];

export function ShapeEditor() {
    const [shapes, setShapes] = useState(
        initialShapes
    );

    function handleClick() {
        // map 会返回一个新的 数组
        const nextShapes = shapes.map(shape => {
            if (shape.type === 'square') {
                // No change
                return shape;
            } else {
                // Return a new circle 50px below
                return {
                    ...shape,
                    y: shape.y + 50,
                };
            }
        });
        // Re-render with the new array
        setShapes(nextShapes);
    }

    return (
        <div>
            <button onClick={handleClick}>
                Move circles down!
            </button>
            {shapes.map(shape => (
                <div
                    key={shape.id}
                    style={{
                        background: 'purple',
                        position: 'absolute',
                        left: shape.x,
                        top: shape.y,
                        borderRadius:
                            shape.type === 'circle'
                                ? '50%' : '',
                        width: 20,
                        height: 20,
                    }} />
            ))}
        </div>
    );
}

// Replacing items in an array
let initialCounters = [
    0, 0, 0
];

export function CounterList() {
    const [counters, setCounters] = useState(
        initialCounters
    );

    function handleIncrementClick(index) {
        const nextCounters = counters.map((c, i) => {
            if (i === index) {
                // Increment the clicked counter
                return c + 1;
            } else {
                // The rest haven't changed
                return c;
            }
        });
        setCounters(nextCounters);
    }

    return (
        <div>
            <ul>
                {counters.map((counter, i) => (
                    <li key={i}>
                        {counter}
                        <button onClick={() => {
                            handleIncrementClick(i);
                        }}>+1</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

//Inserting into an array 指定索引位置插入新数据
// artists.slice(0, 3) 返回 index =0，1,2 组成的新集合 不包含索引=3的数据
// artists.slice(3) 返回 index = 2 及以其后的全部数据组成的数组
nextId = 3;
const initialArtists = [
    { id: 0, name: 'Marta Colvin Andrade' },
    { id: 1, name: 'Lamidi Olonade Fakeye' },
    { id: 2, name: 'Louise Nevelson' },
];

export function InsertList() {
    const [name, setName] = useState('');
    const [artists, setArtists] = useState(
        initialArtists
    );

    function handleClick() {
        const insertAt = 1; // Could be any index
        const nextArtists = [
            // Items before the insertion point:
            // [0,insertAt)
            ...artists.slice(0, insertAt),
            // New item:
            { id: nextId++, name: name },
            // Items after the insertion point:
            ...artists.slice(insertAt)
        ];
        setArtists(nextArtists);
        setName('');
    }

    return (
        <div>
            <h1>Inspiring sculptors:</h1>
            <input
                value={name}
                onChange={e => setName(e.target.value)}
            />
            <button onClick={handleClick}>
                Insert
            </button>
            <ul>
                {artists.map(artist => (
                    <li key={artist.id}>{artist.name}</li>
                ))}
            </ul>
        </div>
    );
}


// 反转数组
nextId = 3;
const initialList = [
    { id: 0, title: 'Big Bellies' },
    { id: 1, title: 'Lunar Landscape' },
    { id: 2, title: 'Terracotta Army' },
];

export function ReverseList() {
    const [list, setList] = useState(initialList);

    function handleClick() {
        const nextList = [...list];
        nextList.reverse();
        setList(nextList);
    }

    return (
        <>
            <button onClick={handleClick}>
                Reverse
            </button>
            <ul>
                {list.map(artwork => (
                    <li key={artwork.id}>{artwork.title}</li>
                ))}
            </ul>
        </>
    );
}


// 数组复制属于浅拷贝
// 数组内存储复杂对象时的 修改操作

nextId = 3;
const initialObjList = [
    { id: 0, title: 'Big Bellies', seen: false },
    { id: 1, title: 'Lunar Landscape', seen: false },
    { id: 2, title: 'Terracotta Army', seen: true },
];

export function BucketList() {
    const [myList, setMyList] = useState(initialObjList);
    const [yourList, setYourList] = useState(initialObjList);

    function handleToggleMyList(artworkId, nextSeen) {
        // 浅拷贝 错误写法
        // const myNextList = [...myList];
        // find 是否返回新对象
        // const artwork = myNextList.find(
        //     a => a.id === artworkId
        // );
        // artwork.seen = nextSeen;
        // setMyList(myNextList);

        setMyList(
            myList.map(my => {
                if (my.id === artworkId) {
                    return {
                        ...my,
                        seen: nextSeen
                    };
                } else {
                    return my;
                }

            })
        );
    }

    function handleToggleYourList(artworkId, nextSeen) {
        // 错误写法
        // const yourNextList = [...yourList];
        // const artwork = yourNextList.find(
        //     a => a.id === artworkId
        // );
        // artwork.seen = nextSeen;
        // setYourList(yourNextList);

        setYourList(
            yourList.map(your => {
                if (your.id === artworkId) {
                    return {
                        ...your,
                        seen: nextSeen
                    };
                } else {
                    return your;
                }
            })
        );
    }

    return (
        <>
            <h1>Art Bucket List</h1>
            <h2>My list of art to see:</h2>
            <ItemList
                artworks={myList}
                onToggle={handleToggleMyList} />
            <h2>Your list of art to see:</h2>
            <ItemList
                artworks={yourList}
                onToggle={handleToggleYourList} />
        </>
    );
}

export function BucketListUseImmer() {
    const [myList, updateMyList] = useImmer(initialObjList);
    const [yourList, updateYourList] = useImmer(initialObjList);

    function handleToggleMyList(artworkId, nextSeen) {
        // use-immer 写法
        updateMyList(draft => {
            const artwork = draft.find(a => a.id === artworkId);
            artwork.seen = nextSeen;
        });
    }

    function handleToggleYourList(artworkId, nextSeen) {
        // use-immer 写法
        updateYourList(draft => {
            const artwork = draft.find(a => a.id === artworkId);
            artwork.seen = nextSeen;
        });
    }

    return (
        <>
            <h1>Art Bucket List</h1>
            <h2>My list of art to see:</h2>
            <ItemList
                artworks={myList}
                onToggle={handleToggleMyList} />
            <h2>Your list of art to see:</h2>
            <ItemList
                artworks={yourList}
                onToggle={handleToggleYourList} />
        </>
    );
}

function ItemList({ artworks, onToggle }) {
    return (
        <ul>
            {artworks.map(artwork => (
                <li key={artwork.id}>
                    <label>
                        <input
                            type="checkbox"
                            checked={artwork.seen}
                            onChange={e => {
                                onToggle(
                                    artwork.id,
                                    e.target.checked
                                );
                            }}
                        />
                        {artwork.title}
                    </label>
                </li>
            ))}
        </ul>
    );
}
