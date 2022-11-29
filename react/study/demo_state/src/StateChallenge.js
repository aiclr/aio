import { useState } from 'react';
import { useImmer } from 'use-immer';
//challenges
const initialProducts = [{
    id: 0,
    name: 'Baklava',
    count: 1,
}, {
    id: 1,
    name: 'Cheese',
    count: 5,
}, {
    id: 2,
    name: 'Spaghetti',
    count: 2,
}];

export function ShoppingCart() {
    const [products, setProducts] = useState(initialProducts)

    function handleIncreaseClick(productId) {
        setProducts(
            products.map(tmp => {
                if (tmp.id === productId) {
                    return { ...tmp, count: tmp.count + 1 }
                } else {
                    return tmp;
                }
            })
        );
    }

    function handleDecreaseClick(productId) {
        setProducts(
            products.map(tmp => {
                if (tmp.id === productId && tmp.count > 0) {
                    return { ...tmp, count: tmp.count - 1 }
                } else {
                    return tmp;
                }
            })
        );
    }

    return (
        <ul>
            {products.map(product => (
                <li key={product.id}>
                    {product.name}
                    {' '}
                    (<b>{product.count}</b>)
                    <button onClick={() => {
                        handleIncreaseClick(product.id);
                    }}>
                        +
                    </button>
                    <button onClick={() => {
                        handleDecreaseClick(product.id);
                    }}>
                        -
                    </button>
                </li>
            ))}
        </ul>
    );
}


export function ShoppingCartUseImmer() {
    const [products, updateProducts] = useImmer(initialProducts)

    function handleIncreaseClick(productId) {
        updateProducts(draft => {
            const prodct = draft.find(tmp => tmp.id === productId)
            if (prodct !== undefined) {
                prodct.count += 1;
            }
        });
    }
    function handleDecreaseClick(productId) {
        updateProducts(draft => {
            const prodct = draft.find(tmp => tmp.id === productId);
            if (prodct !== undefined && prodct.count > 0) {
                prodct.count -= 1;
            }
        });
    }

    return (
        <ul>
            {products.map(product => (
                <li key={product.id}>
                    {product.name}
                    {' '}
                    (<b>{product.count}</b>)
                    <button onClick={() => {
                        handleIncreaseClick(product.id);
                    }}>
                        +
                    </button>
                    <button onClick={() => {
                        handleDecreaseClick(product.id);
                    }}>
                        -
                    </button>
                </li>
            ))}
        </ul>
    );
}
