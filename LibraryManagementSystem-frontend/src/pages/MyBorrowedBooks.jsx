import { useEffect, useState } from "react";
import BorrowedBooksList from "../components/BorrowedBooksList";
import Nav from "../components/Nav";
import getSession from "../services/GetSession";
import { NavLink } from "react-router-dom";

function MyBorrowedBook() {

    let [state, setState] = useState({});

    useEffect(() => {
        async function setSession() {
            let request = await getSession();
            setState(() => {
                return request;
            });
        }
        setSession();
    },[])
    return <section>
        {
            state.status && state.userRole == "student"?
                <>
                    <Nav />
                    <BorrowedBooksList userId={state.userID}/>
                </> :
                <p>Your session has expired. Please <NavLink to={"/login"}>log in</NavLink> again.</p>
        }
    </section>
}

export default MyBorrowedBook;