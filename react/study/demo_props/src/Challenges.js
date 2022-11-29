import {getImageUrl} from './utils.js';

//优化
function Gallery() {
    return (
        <div>
            <h1>Notable Scientists</h1>
            <section className="profile">
                <h2>Maria Skłodowska-Curie</h2>
                <img
                    className="avatar"
                    src={getImageUrl('512')}
                    alt="Maria Skłodowska-Curie"
                    width={70}
                    height={70}
                />
                <ul>
                    <li>
                        <b>Profession: </b>
                        physicist and chemist
                    </li>
                    <li>
                        <b>Awards: 4 </b>
                        (Nobel Prize in Physics, Nobel Prize in Chemistry, Davy Medal, Matteucci Medal)
                    </li>
                    <li>
                        <b>Discovered: </b>
                        polonium (element)
                    </li>
                </ul>
            </section>
            <section className="profile">
                <h2>Katsuko Saruhashi</h2>
                <img
                    className="avatar"
                    src={getImageUrl('192')}
                    alt="Katsuko Saruhashi"
                    width={70}
                    height={70}
                />
                <ul>
                    <li>
                        <b>Profession: </b>
                        geochemist
                    </li>
                    <li>
                        <b>Awards: 2 </b>
                        (Miyake Prize for geochemistry, Tanaka Prize)
                    </li>
                    <li>
                        <b>Discovered: </b>
                        a method for measuring carbon dioxide in seawater
                    </li>
                </ul>
            </section>
        </div>
    );
}

function Profile({person, size, awards, profession, discovered}) {
    return (
        <section className="profile">
            <h2>{person.name}</h2>
            <img
                className="avatar"
                src={getImageUrl(person)}
                alt={person.name}
                width={size}
                height={size}
            />
            <ul>
                <li>
                    <b>Profession: </b>
                    {profession}
                </li>
                <li>
                    <b>Awards: {awards.num} </b>
                    {awards.desc}
                </li>
                <li>
                    <b>Discovered: </b>
                    {discovered}
                </li>
            </ul>
        </section>
    )
}

const profiles = [
    {
        person: {
            name: 'Maria Skłodowska-Curie',
            imageId: '512'
        },
        size: 70,
        awards: {
            num: 4,
            desc: '(Nobel Prize in Physics, Nobel Prize in Chemistry, Davy Medal, Matteucci Medal)'
        },
        profession: 'physicist and chemist',
        discovered: 'polonium (element)'
    },
    {
        person: {
            name: 'Katsuko Saruhashi',
            imageId: '192'
        },
        size: 70,
        awards: {
            num: 2,
            desc: '(Miyake Prize for geochemistry, Tanaka Prize)'
        },
        profession: 'geochemist',
        discovered: 'a method for measuring carbon dioxide in seawater'
    }
];

function ProfileList({profile}) {
    return (<Profile
        person={profile.person}
        size={profile.size}
        awards={profile.awards}
        profession={profile.profession}
        discovered={profile.discovered}
        key={profile.person.name}
    />);
}

export default function GalleryPro() {
    const ppp = [];
    profiles.map((profile) => {
        ppp.push(<ProfileList profile={profile} key={profile.person.name}/>);
    });

    return (
        <div>
            <h1>Notable Scientists</h1>
            {ppp}
        </div>
    );
}