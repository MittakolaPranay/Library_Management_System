import AdminBookList from "./AdminBookList";
import Nav from "./Nav";
import { useState } from "react";

function AdminDashboard() {

    let [searchInput, setSearchInput] = useState("");
        
        function setInput (input) {
            console.log(input)
            setSearchInput(input);
        }

    return <section>
        <Nav setInput={setInput}/>
        <AdminBookList searchInput={searchInput}/>
    </section>
}

export default AdminDashboard;