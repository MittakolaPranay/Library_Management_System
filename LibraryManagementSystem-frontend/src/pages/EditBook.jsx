
import { useLocation } from "react-router-dom";
import UpdateBook from "../components/UpdateBook";
import { NavLink } from "react-router-dom";
import { useState,useEffect } from "react";
import getSession from "../services/GetSession";

function EditBook() {

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

    let location = useLocation();
    const { book } = location.state || {};
    return   state.status ? <UpdateBook book={book} /> :
             <p>Your session has expired. Please <NavLink to={"/login"}>log in</NavLink> again.</p>
    
}

export default EditBook;