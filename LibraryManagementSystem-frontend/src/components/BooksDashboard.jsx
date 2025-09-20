
import { useState } from "react";
import BookList from "./BookList";
import Nav from "./Nav";
import Toast from "./Toast";

function BooksDashboard({id}) {

    let [searchInput, setSearchInput] = useState("");

    let [toast,setToast] = useState({
        status : true,
        message : ""
    })
    
    function setInput (input) {
        console.log(input)
        setSearchInput(input);
    }

    function handleResponse(response) {
        setToast(() => {
            return {
                status : false,
                message : response
            }
        })
    }

    let setAction = () => {
        setToast(() => {
            return {
                status : true,
                message : ""
            }
        })
    }

    return <section>
        <Nav setInput={setInput}/>
        <BookList searchInput={searchInput} id={id} response={handleResponse}/>
        {
            toast.status ? <></> : 
            <Toast message={toast.message} action={setAction}/>
        }
    </section>
}

export default BooksDashboard;