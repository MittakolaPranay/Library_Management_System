
import { use, useState } from "react";
import BookList from "./BookList";
import Nav from "./Nav";

function BooksDashboard() {

    let [searchInput, setSearchInput] = useState("");
    
    function setInput (input) {
        console.log(input)
        setSearchInput(input);
    }

    return <section>
        <Nav setInput={setInput}/>
        <BookList searchInput={searchInput}/>
    </section>
}

export default BooksDashboard;