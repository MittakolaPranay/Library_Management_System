
import BookContext from "../services/BookContext";
import deleteBook from "../services/DeleteBook";
import "./AdminBookCard.css"
import { useNavigate } from "react-router-dom";


function AdminBookCard({book,setToast}) {

    let navigate = useNavigate();
    let handleEdit = () => {
        navigate("/editBook",{ state: { book } })
    }

    let handleDeletBook = async () => {
        let response = await deleteBook(book.id);
        setToast(response.message);
    }
    return <div className="borrowed-book">
        <div className="book">
            <div className="book-image">
                <img src={"http://localhost:8080" + book.image_url} alt={book.title} />
            </div>
            <div className="book-info">
                <h3 className="card-title">{book.title}</h3>
                <p className="card-author">{book.author}</p>
                <p className="card-isbn">ISBN: {book.isbn}</p>
                <p className="card-copies">Copies: {book.copies}</p>
                <p className="card-available">Available: {book.available}</p>
            </div>
        </div>
        <div className="btn">
            <button id="delete-btn" onClick={handleDeletBook}>Delete</button>
            <button id="edit-btn" onClick={handleEdit}>Edit</button>
        </div>
    </div>
}

export default AdminBookCard;