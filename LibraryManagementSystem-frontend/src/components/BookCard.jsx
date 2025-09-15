
import "./BookCard.css";

function BookCard({ book ,userId, bookId}) {

  let handleBtn = () => {
    console.log(userId);
    console.log(bookId)
  }

  return (
    <div className="card" >
      <div className="card-image">
        <img src={"http://localhost:8080"+book.image_url} alt={book.title} />
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
    </div>
  );
}

export default BookCard;
