import "./AdminBookCard.css"


function AdminBookCard({book}) {
    return <div className="borrowed-book">
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
        <div className="form">
            <button >Delete</button>
            <button >Edit</button>
        </div>
    </div>
}

export default AdminBookCard;