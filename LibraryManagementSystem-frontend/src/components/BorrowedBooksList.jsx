
import "./BorrowedBooksList.css"
import BorrowedBookCard from "./BorrowedBookCard";
import { useState,useEffect } from "react";
import getBorrowedBook from "../services/GetBorrowedBooks";
import Toast from "./Toast";
function BorrowedBooksList({userId}) {

    let [borrowedBookArray, setBorrowedBookArray] = useState([]);

    let [toast,setToast] = useState({
        status : true,
        message : ""
    });


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

    let hanldeAction = () => {
        setToast(() => {
            return {
                status : true,
                message : ""
            }
        })
    }

    let handleResponse = (response) => {
        setToast(() => {
            return {
                status : false,
                message : response
            }
        })
    }


    return <section>
        <ul className="book-list">
            {
                borrowedBookArray.map((book) => {
                    return <li key={book.id}><BorrowedBookCard book={book} userId={userId} response={handleResponse}/></li>
                })
            }
        </ul>
        {
            toast.status ? <></> : 
            <Toast message={toast.message} action={hanldeAction}/>
        }
    </section>
}

export default BorrowedBooksList;