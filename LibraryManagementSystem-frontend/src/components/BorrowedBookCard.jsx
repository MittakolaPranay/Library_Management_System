import { useState } from "react";
import returnBook from "../services/ReturnBook";
import "./BorrowedBookCard.css"


function BorrowedBookCard({ book, userId }) {

    let handleSubmit = async (event) => {
        event.preventDefault();
        let request = await returnBook(userId, book.id);
        if (request) {
            alert("book returned");
        } else {
            alert("failed to return book");
        }
    }

    return (
        <>
            <div className="borrowed-book">
                <div className="book">
                    <div className="book-image">
                        <img src={"http://localhost:8080" + book.image_url} alt={book.title} />
                    </div>
                    <div className="book-info">
                        <h3 className="card-title">{book.title}</h3>
                        <p className="card-author">{book.author}</p>
                        <p className="card-isbn">ISBN: {book.isbn}</p>
                    </div>
                </div>
                <form className="return-form" onSubmit={handleSubmit}>
                    <button type="submit">Return</button>
                </form>
            </div>
        </>);
}

export default BorrowedBookCard;