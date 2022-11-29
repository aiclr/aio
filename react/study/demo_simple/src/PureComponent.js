let guest = 0;
export function Cup(){
    guest = guest+1;
    return <h2>Tea cup for guest #{guest}</h2>
}

export function PureCup({guest}){
    return <h2>Tea cup for guest #{guest}</h2>
}

export function Cups(){
    let cups=[];
    for(let i =1;i<=12;i++){
        cups.push(<PureCup key={i} guest={i}/> );
    }
    return cups;
}