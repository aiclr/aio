import { LevelContext ,LevelContext2} from "./LevelContext";

import {useContext} from 'react'

// provide the LevelContext

export default function Section({level, children }) {
    return (
        <section className="section">
            <LevelContext.Provider value={level}>
                {children}
            </LevelContext.Provider>
        </section>
    );
}

// 不使用 props 传level 
export function Section2({children }) {
    //import {useContext} from 'react'
    const level = useContext(LevelContext2);
    return (
        <section className="section">
            <LevelContext2.Provider value={level+1}>
                {children}
            </LevelContext2.Provider>
        </section>
    );
}
