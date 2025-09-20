
import BookCard from "./BookCard";
import "./BookList.css"
import { useEffect, useState } from "react";
import getAllbooks from "../services/GetAllBooks";
import getSearchedBooks from "../services/GetSearchedBooks";


function BookList({ searchInput ,id,response}) {

    let [searchTerm, setSearchTerm] = useState("")
    let [bookArray, setBookArray] = useState([]);
    let [isEmpty,setIsEmpty] = useState(false);


    useEffect(() => {
        setSearchTerm(() => {
            return searchInput;
        });
    }, [searchInput]);

    useEffect(() => {
        async function getBooksFromServer() {
            let array = await getAllbooks();

            setBookArray(() => {
                return array.books;
            });
        }
        getBooksFromServer();
    }, []);


    useEffect(() => {
        async function fetchSearchResults() {
            if (searchTerm && searchTerm.trim() !== "") {
                let array = await getSearchedBooks(searchTerm);
                console.log(array)
                if(array.length == 0) {
                    setIsEmpty(true);
                    console.log(array)
                }else {
                    setIsEmpty(false)
                    setBookArray(array.books);
                }
            } else {
                let array = await getAllbooks();
                if(array.length == 0) {
                    setIsEmpty(true);
                    console.log(array)
                }else {
                    setIsEmpty(false)
                    setBookArray(array.books);
                }
            }
        }
        fetchSearchResults();
    }, [searchTerm]);


    return <ul className="booklist">
        {
     
     isEmpty ? <h1>book not found</h1> :
              bookArray.map((book) => {
                    return <li key={book.id}><BookCard book={book} userId={id} bookId={book.id} response={response}/></li>
                })
        }
    </ul>

}

export default BookList;