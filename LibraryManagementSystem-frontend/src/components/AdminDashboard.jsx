import AdminBookList from "./AdminBookList";
import Nav from "./Nav";
import { useState } from "react";
import Toast from "./Toast";
import AddBookForm from "./AddBookForm";
import AdminNav from "./AdminNav";

function AdminDashboard() {

    let [searchInput, setSearchInput] = useState("");
    let [status, setStatus] = useState(true);
    let [message, setMessage] = useState("")

    function setInput(input) {
        console.log(input)
        setSearchInput(input);
    }

    function setToast(message) {
        setStatus(false);
        setMessage(message);
    }

    function removeToast() {
        setStatus(true);
    }


    return <section>

        <AdminNav setInput={setInput} />
        <AdminBookList searchInput={searchInput} setToast={setToast} />
        {
            status ? <></> :
                <Toast message={message} action={removeToast} />
        }
        
    </section>
}

export default AdminDashboard;