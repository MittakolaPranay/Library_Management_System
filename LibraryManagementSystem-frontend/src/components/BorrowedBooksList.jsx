
import "./BorrowedBooksList.css"
import BorrowedBookCard from "./BorrowedBookCard";
import { useState,useEffect } from "react";
import getBorrowedBook from "../services/GetBorrowedBooks";
function BorrowedBooksList({userId}) {

    let [borrowedBookArray, setBorrowedBookArray] = useState([]);

    useEffect(() => {
        async function getBooksFromServer() {
            console.log("user id from booklist : ",userId)
            let array = await getBorrowedBook(userId);
            console.log(array)
            setBorrowedBookArray(() => {
                return array.books;
            });
        }
        getBooksFromServer();
    }, []);


    return <section>
        <ul className="book-list">
            {
                borrowedBookArray.map((book) => {
                    return <li key={book.id}><BorrowedBookCard book={book} userId={userId}/></li>
                })
            }
        </ul>
    </section>
}

export default BorrowedBooksList;