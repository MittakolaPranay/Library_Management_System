
import BookList from "./BookList";
import Nav from "./Nav";

function BooksDashboard() {

    let searchInput;
    
    function setSearchInput (input) {
        searchInput = input;
    }
    return <section>
        <Nav setInput={setSearchInput}/>
        <BookList />
    </section>
}

export default BooksDashboard;