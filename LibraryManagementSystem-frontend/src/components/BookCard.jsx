
import { useSearchParams } from "react-router-dom";
import borrowBook from "../services/borroBook";
import "./BookCard.css";
import { useState } from "react";

function BookCard({ book, userId, bookId }) {

  let [status, setStatus] = useState(true);
  let [message, setMessage] = useState("");

  let handleBtn = async () => {
    try {
      let res = await borrowBook(userId, bookId);
      if (res.status) {
        setStatus(false);
        setMessage("Book is borrowed successfully")
        setTimeout(() => {
          setStatus(true);
        },2000)
      } else {
        setStatus(false)
        setMessage("failed to borrow book or book is alredy borrowed by you");
        setTimeout(() => {
          setStatus(true);
        },2000)
      }
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <>
      <div className="card" >
        <div className="card-image">
          <img src={"http://localhost:8080" + book.image_url} alt={book.title} />
        </div>
        <button className="borrow" onClick={handleBtn}><i class="fa-solid fa-download"></i></button>
        <div className="card-box">
          <h3 className="card-title">{book.title}</h3>
          <p className="card-author">{book.author}</p>
          <p className="card-isbn">ISBN: {book.isbn}</p>
          <p className="card-copies">
            Copies: {book.copies} |{" "}
            <span
              className={book.available > 0 ? "available" : "unavailable"}
            >
              {book.available > 0 ? "Available" : "Unavailable"}
            </span>
          </p>
        </div>
        <div>
          {
            status ? <></> :
              <p style={{ color: "red", padding: "1rem" }}>{message}</p>
          }
        </div>
      </div>
    </>
  );
}

export default BookCard;
