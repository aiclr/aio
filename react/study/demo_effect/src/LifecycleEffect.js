import { useState, useEffect } from 'react'

// 点是否移动应该与 复选框是否被选中保持同步
export function PointerMove() {
    const [position, setPosition] = useState({ x: 0, y: 0 });
    const [canMove, setCanMove] = useState(true);

    useEffect(() => {
        function handleMove(e) {
            if (canMove) {
                setPosition({ x: e.clientX, y: e.clientY });
            }
        }
        window.addEventListener('pointermove', handleMove);
        return () => window.removeEventListener('pointermove', handleMove);
    }, [canMove]);

    return (
        <>
            <label>
                <input type="checkbox"
                    checked={canMove}
                    onChange={e => setCanMove(e.target.checked)}
                />
                The dot is allowed to move
            </label>
            <hr />
            <div style={{
                position: 'absolute',
                backgroundColor: 'pink',
                borderRadius: '50%',
                opacity: 0.6,
                transform: `translate(${position.x}px, ${position.y}px)`,
                pointerEvents: 'none',
                left: -20,
                top: -20,
                width: 40,
                height: 40,
            }} />
        </>
    );
}
// 点是否移动应该与 复选框是否被选中保持同步
export function PointerMoveBug() {
    const [position, setPosition] = useState({ x: 0, y: 0 });
    const [canMove, setCanMove] = useState(true);

    useEffect(() => {
        function handleMove(e) {
            setPosition({ x: e.clientX, y: e.clientY });
        }
        window.addEventListener('pointermove', handleMove);
        return () => window.removeEventListener('pointermove', handleMove);
    }, []);

    return (
        <>
            <label>
                <input type="checkbox"
                    checked={canMove}
                    onChange={e => setCanMove(e.target.checked)}
                />
                The dot is allowed to move
            </label>
            <hr />
            <div style={{
                position: 'absolute',
                backgroundColor: 'pink',
                borderRadius: '50%',
                opacity: 0.6,
                transform: `translate(${position.x}px, ${position.y}px)`,
                pointerEvents: 'none',
                left: -20,
                top: -20,
                width: 40,
                height: 40,
            }} />
        </>
    );
}



async function fetchPlanets() {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve([{
                id: 'earth',
                name: 'Earth'
            }, {
                id: 'venus',
                name: 'Venus'
            }, {
                id: 'mars',
                name: 'Mars'
            }]);
        }, 1000);
    });
}

async function fetchPlaces(planetId) {
    if (typeof planetId !== 'string') {
        throw Error(
            'fetchPlaces(planetId) expects a string argument. ' +
            'Instead received: ' + planetId + '.'
        );
    }
    return new Promise(resolve => {
        setTimeout(() => {
            if (planetId === 'earth') {
                resolve([{
                    id: 'laos',
                    name: 'Laos'
                }, {
                    id: 'spain',
                    name: 'Spain'
                }, {
                    id: 'vietnam',
                    name: 'Vietnam'
                }]);
            } else if (planetId === 'venus') {
                resolve([{
                    id: 'aurelia',
                    name: 'Aurelia'
                }, {
                    id: 'diana-chasma',
                    name: 'Diana Chasma'
                }, {
                    id: 'kumsong-vallis',
                    name: 'Kŭmsŏng Vallis'
                }]);
            } else if (planetId === 'mars') {
                resolve([{
                    id: 'aluminum-city',
                    name: 'Aluminum City'
                }, {
                    id: 'new-new-york',
                    name: 'New New York'
                }, {
                    id: 'vishniac',
                    name: 'Vishniac'
                }]);
            } else throw Error('Uknown planet ID: ' + planetId);
        }, 1000);
    });
}

function fetchData(url) {
    if (url === '/planets') {
        return fetchPlanets();
    } else if (url.startsWith('/planets/')) {
        const match = url.match(/^\/planets\/([\w-]+)\/places(\/)?$/);
        if (!match || !match[1] || !match[1].length) {
            throw Error('Expected URL like "/planets/earth/places". Received: "' + url + '".');
        }
        return fetchPlaces(match[1]);
    } else throw Error('Expected URL like "/planets" or "/planets/earth/places". Received: "' + url + '".');
}

// 不同的同步 需要拆分
export function Page() {
    const [planetList, setPlanetList] = useState([])
    const [planetId, setPlanetId] = useState('');

    const [placeList, setPlaceList] = useState([]);
    const [placeId, setPlaceId] = useState('');

    useEffect(() => {
        let ignore = false;
        fetchData('/planets').then(result => {
            if (!ignore) {
                console.log('Fetched a list of planets.');
                setPlanetList(result);
                setPlanetId(result[0].id); // Select the first planet
            }
        });
        return () => {
            ignore = true;
        }
    }, []);

    useEffect(() => {

        if (planetId === '') {
            return;
        }

        let ignore = false;
        fetchData('/planets/' + planetId + '/places')
            .then(result => {
                if (!ignore) {
                    console.log('Fetched a list of places.');
                    setPlaceList(result);
                    setPlaceId(result[0].id); // Select the first place
                }
            });
        return () => {
            ignore = true;
        }
    }, [planetId]);

    return (
        <>
            <label>
                Pick a planet:{' '}
                <select value={planetId} onChange={e => {
                    setPlanetId(e.target.value);
                }}>
                    {planetList.map(planet =>
                        <option key={planet.id} value={planet.id}>{planet.name}</option>
                    )}
                </select>
            </label>
            <label>
                Pick a place:{' '}
                <select value={placeId} onChange={e => {
                    setPlaceId(e.target.value);
                }}>
                    {placeList.map(place =>
                        <option key={place.id} value={place.id}>{place.name}</option>
                    )}
                </select>
            </label>
            <hr />
            <p>You are going to: {placeId || '???'} on {planetId || '???'} </p>
        </>
    );
}


function useSelectOptions(url) {
    const [list, setList] = useState(null);
    const [selectedId, setSelectedId] = useState('');

    useEffect(() => {
        if (url === null) {
            return;
        }

        let ignore = false;
        fetchData(url).then(result => {
            if (!ignore) {
                setList(result);
                setSelectedId(result[0].id);
            }
        });
        return () => {
            ignore = true;
        }
    }, [url]);
    return [list, selectedId, setSelectedId];
}

// 不同的同步 也可以 封装组件
export function PagePlus() {
    const [
        planetList,
        planetId,
        setPlanetId
    ] = useSelectOptions('/planets');
    const [
        placeList,
        placeId,
        setPlaceId
    ] = useSelectOptions(planetId ? `/planets/${planetId}/places` : null);

    return (
        <>
            <label>
                Pick a planet:{' '}
                <select value={planetId} onChange={e => {
                    setPlanetId(e.target.value);
                }}>
                    {planetList?.map(planet =>
                        <option key={planet.id} value={planet.id}>{planet.name}</option>
                    )}
                </select>
            </label>
            <label>
                Pick a place:{' '}
                <select value={placeId} onChange={e => {
                    setPlaceId(e.target.value);
                }}>
                    {placeList?.map(place =>
                        <option key={place.id} value={place.id}>{place.name}</option>
                    )}
                </select>
            </label>
            <hr />
            <p>You are going to: {placeId || '...'} on {planetId || '...'} </p>
        </>
    );
}
