import { useState } from 'react'

const foods = [{
    id: 0,
    name: 'Sushi',
    description: 'Sushi is a traditional Japanese dish of prepared vinegared rice'
}, {
    id: 1,
    name: 'Dal',
    description: 'The most common way of preparing dal is in the form of a soup to which onions, tomatoes and various spices may be added'
}, {
    id: 2,
    name: 'Pierogi',
    description: 'Pierogi are filled dumplings made by wrapping unleavened dough around a savoury or sweet filling and cooking in boiling water'
}, {
    id: 3,
    name: 'Shish kebab',
    description: 'Shish kebab is a popular meal of skewered and grilled cubes of meat.'
}, {
    id: 4,
    name: 'Dim sum',
    description: 'Dim sum is a large range of small dishes that Cantonese people traditionally enjoy in restaurants for breakfast and lunch'
}];

function filterItems(items, query) {

    query = query.toLowerCase();
    return items.filter(item =>
        item.name.split(' ')
        .some(word =>
            word.toLowerCase().startsWith(query)
        )
    );
}

function SearchBar({query, onChange}) {

    return (
        <label>
            Search:{' '}
            <input
                value={query}
                onChange={e => onChange(e)}
            />
        </label>
    );
}

function List({ items }) {
    
    return (
        <table>
            <tbody>
                {items.map(food => (
                    <tr key={food.id}>
                        <td>{food.name}</td>
                        <td>{food.description}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}



export function FilterableList() {

    const [query, setQuery] = useState('');

    const results = filterItems(foods, query);

    function handleChange(e) {
        setQuery(e.target.value);
    }

    return (
        <>
            <SearchBar query={query} onChange={handleChange} />
            <hr />
            <List items={results} />
        </>
    );
}