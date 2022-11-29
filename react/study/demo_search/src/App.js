import {useState} from 'react';


function TH({name}) {
    return (
        <tr>
            <th colSpan='2'>{name}</th>
        </tr>
    );
}

function TBody({product}) {

    const name = product.stocked ? product.name :
        <span style={{color: 'red'}}>
        {product.name}
    </span>;

    return (
        <tr>
            <td>{name}</td>
            <td>{product.price}</td>
        </tr>
    );
}


function TableShow({data, show, filter}) {

    const categorys = [];

    //获取 种类
    data.forEach((product) => {
        if (!categorys.includes(product.category)) {
            categorys.push(product.category);
        }
    });

    const rows = [];

    categorys.forEach((category) => {
        rows.push(<TH name={category} key={category}/>);
        data.forEach(product => {
            // 优化 if-else
            //show            true=1 false=0
            //product.stocked true=1 false=0
            // show product.stocked
            //1  1 = 1
            //1  0 = 0  ===> true (？运算符)  false = false
            //0  1 = 1
            //0  0 = 1
            // !show || product.stocked
            //0  1 = 1
            //0  0 = 0   ===> false (|| 运算符)  false = false
            //1  1 = 1
            //1  0 = 1
            // show && !product.stocked
            //1  0 = 1
            //1  1 = 0   ===>  !(true (&& 运算符) true) = false
            //0  0 = 1
            //0  1 = 1
            // ！show  !product.stocked
            //0  0 = 1
            //0  1 = 0    ===>  false (？运算符)  true = false
            //1  0 = 1
            //1  1 = 1
            if (show && !product.stocked) {
                return;
            }
            //"hello".indexOf.('')=0
            //"hello".indexOf.('o')=4
            //"hello".indexOf.('a')=-1
            if (category === product.category &&
                product.name.toLowerCase().indexOf(filter.toLowerCase()) !== -1
            ) {
                rows.push(<TBody product={product} key={product.name}/>)
            }
        });
    });
    return (
        <table>
            <thead>
            <tr>
                <td>name</td>
                <td>price</td>
            </tr>
            </thead>
            <tbody>{rows}</tbody>
        </table>
    );
}

function SearchBar({show, setShow, filter, setFilter}) {
    return (
        <form>
            <input className="filter" type="text" value={filter} placeholder="Search..."
                   onChange={(e) => setFilter(e.target.value)}/>
            <br/>
            <label>
                <input type="checkbox" onChange={(e) => setShow(e.target.checked)} checked={show}/>
                {' '}
                Only show products in stock
            </label>
        </form>
    );
}

function ProductsTable({products}) {

    const [show, setShow] = useState(false);
    const [filter, setFilter] = useState('');

    return (
        <div>
            <h1>Welcome to my app</h1>
            <SearchBar show={show} setShow={setShow} filter={filter} setFilter={setFilter}/>
            <TableShow data={products} show={show} filter={filter}/>
        </div>
    );

}

const data = [
    {category: "Fruits", price: "$1", stocked: true, name: "Apple"},
    {category: "Fruits", price: "$1", stocked: true, name: "Dragonfruit"},
    {category: "Fruits", price: "$2", stocked: false, name: "Passionfruit"},
    {category: "Vegetables", price: "$2", stocked: true, name: "Spinach"},
    {category: "Vegetables", price: "$4", stocked: false, name: "Pumpkin"},
    {category: "Vegetables", price: "$1", stocked: true, name: "Peas"},
    {category: "Meet", price: "$1", stocked: true, name: "Chicken"}
];

export default function MyApp() {
    //字符对比
    console.log("\"hello\".indexOf.('')="+"hello".indexOf(''))
    console.log("\"hello\".indexOf.('o')="+"hello".indexOf('o'))
    console.log("\"hello\".indexOf.('a')="+"hello".indexOf('a'))
    return (<ProductsTable products={data}/>);
}