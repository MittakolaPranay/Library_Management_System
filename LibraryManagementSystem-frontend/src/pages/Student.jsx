import { useEffect, useState } from "react";
import BooksDashboard from "../components/BooksDashboard";
import getSession from "../services/GetSession";
import { NavLink } from "react-router-dom";

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
            state.status && state.userRole == "student"? 
            <BooksDashboard id={state.userID} /> :
            <p>Your session has expired. Please <NavLink to={"/login"}>log in</NavLink> again.</p>
        }
    </section>
}

export default Student;