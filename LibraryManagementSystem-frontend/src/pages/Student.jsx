import { useEffect, useState } from "react";
import BooksDashboard from "../components/BooksDashboard";
import getSession from "../services/GetSession";

function Student() {


    let [userID,setUserID] = useState(0);

    useEffect(() => {
        async function setSession() {
            let session = await getSession();
            setUserID(session.userID)
        }
        setSession();
    }, []);

    return <section>
        <BooksDashboard id={userID} />
    </section>
}

export default Student;