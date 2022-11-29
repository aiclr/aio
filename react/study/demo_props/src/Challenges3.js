function Card1({children}) {
    return (
        <div className="card">
            <div className="card-content">
                {children}
            </div>
        </div>
    );
}

function Card2({head, content}) {
    return (
        <div className="card">
            <div className="card-content">
                <h1>{head}</h1>
                {content}
            </div>
        </div>
    );
}

export default function Challenges3() {

    return (
        <div>
            <Card1>
                <h1>Photo</h1>
                <img
                    className="avatar"
                    src="logo192.png"
                    alt="Aklilu Lemma"
                    width={70}
                    height={70}
                />
            </Card1>

            <Card2 head={'About'}>
                <p>Aklilu Lemma was a distinguished Ethiopian scientist who discovered a natural treatment to
                    schistosomiasis.</p>
            </Card2>

            <Card2 head={'About'}
                content={
                <p>Aklilu Lemma was a distinguished Ethiopian scientist who discovered a natural treatment to
                    schistosomiasis.</p>}
            />

        </div>
    );
}