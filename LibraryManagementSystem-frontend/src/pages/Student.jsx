import { useEffect, useState } from "react";
import BooksDashboard from "../components/BooksDashboard";
import getSession from "../services/GetSession";

function Student() {


    let [state, setState] = useState({});

    useEffect(() => {
        async function setSession() {
            let req = await getSession();
            setState(() => {
                return req;
            });
        }
        setSession();
    }, []);

    return <section style={{position : "relative"}}>
        {
            state.status ? 
            <BooksDashboard id={state.userID} /> :
            <div>
                <p>{state.message}</p>
                <button>Login</button>
            </div>
        }
    </section>
}

export default Student;