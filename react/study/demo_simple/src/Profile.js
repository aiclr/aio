// this variable make Profile Header Avatar impure
let currentPerson;

export function Profile({ person }) {
    currentPerson = person;
    return (
            <>
                <Header />
                <Avatar />
            </>
            )
}

function Header() {
    return <h1>{currentPerson.name}</h1>;
}

function Avatar() {
    return (
            <img
                className="avatar"
                src='./logo192.png'
                alt={currentPerson.name}
                width={50}
                height={50}
            />
            );
}


export function PureProfile({person}) {
    return (
            <>
            <PureHeader person={person}/>
            <PureAvatar person={person}/>
            </>
            )
}
function PureHeader({person}) {
    return <h1>{person.name}</h1>;
}

function PureAvatar({person}) {
    return (
            <img
                className="avatar"
                src='./logo192.png'
                alt={person.name}
                width={50}
                height={50}
            />
            );
}