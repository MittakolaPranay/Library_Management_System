import AddBookForm from "../components/AddBookForm";
import { useState,useEffect } from "react";
import getSession from "../services/GetSession";
import { NavLink } from "react-router-dom";

function AddBook() {
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
    return state.status && state.userRole == "librarian"? <AddBookForm />:
        <p>Your session has expired. Please <NavLink to={"/login"}>log in</NavLink> again.</p>
}

export default AddBook;