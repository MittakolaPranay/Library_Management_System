import { useEffect, useState } from "react";
import BorrowedBooksList from "../components/BorrowedBooksList";
import Nav from "../components/Nav";
import getSession from "../services/GetSession";

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
    })
    return <section>
        {
            state.status ?
                <>
                    <Nav />
                    <BorrowedBooksList userId={state.userID}/>
                </> :
                <>
                    <p>{state.message}</p>
                    <button>login</button>
                </>
        }
    </section>
}

export default MyBorrowedBook;