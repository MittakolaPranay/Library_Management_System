import { useState } from "react";
import "./AddBookForm.css"
import addBook from "../services/AddBook";
import Toast from "./Toast";

function AddBookForm() {


  let [addBookInput, setAddBookInput] = useState({
    title: "",
    author: "",
    isbn: "",
    copies: "",
    available: "",
  });

  let [status, setStatus] = useState(true);
  let [message, setMessage] = useState("")

  let [file, setFile] = useState(null);

  let handleFile = (e) => {
    setFile(e.target.files[0]);
  }

  let handleAddBookInput = (e) => {
    setAddBookInput((prevBook) => {
      return { ...prevBook, [e.target.name]: e.target.value }
    })
  }


  let handleSubmit = async (event) => {
    event.preventDefault();
    let response = await addBook(addBookInput, file);
    console.log(response);
    setStatus(false);
    setMessage(response.message);
  }

  return <>
    <section className='addbook-form'>
      <form className={`add-book ${status ? '' : 'opacity'} `} onSubmit={handleSubmit}>
        <h1>Add book</h1>
        <div className="title input-box">
          <label htmlFor="input-title">Title:</label>
          <input type="text" placeholder="type here.." id="input-title" value={addBookInput.title} name="title" onChange={handleAddBookInput} />
        </div>
        <div className="author input-box">
          <label htmlFor="input-author">Author:</label>
          <input type="text" placeholder="type here.." id="input-author" value={addBookInput.author} name="author" onChange={handleAddBookInput} />
        </div>
        <div className="isbn input-box">
          <label htmlFor="input-isbn">ISBN:</label>
          <input type="text" placeholder="type here.." id="input-isbn" value={addBookInput.isbn} name="isbn" onChange={handleAddBookInput} />
        </div>
        <div className="copies input-box">
          <label htmlFor="input-copies">Copies:</label>
          <input type="number" placeholder="type here.." id="input-copies" value={addBookInput.copies} name="copies" onChange={handleAddBookInput} />
        </div>
        <div className="available-form input-box">
          <label htmlFor="input-availabe">Available:</label>
          <input type="number" placeholder="type here.." id="input-available" value={addBookInput.available} name="available" onChange={handleAddBookInput} />
        </div>
        <div className="input-box">
          <label htmlFor="input-image">Image:</label>
          <input type="file" placeholder="type here.." id="input-image" name="image" onChange={handleFile}/>
        </div>
        <button className="add-book-btn"  type="submit">Add book</button>
      </form>
      {
        status ? <></> :
          <Toast message={message} action={() => {
            setStatus(true);
            setAddBookInput(() => {
              return {
                title: "",
                author: "",
                isbn: "",
                copies: "",
                available: "",
              }
            });
            setFile(null);
            document.getElementById("input-image").value = "";
          }
          } />
      }
    </section>
  </>
}

export default AddBookForm;
