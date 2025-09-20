import { useEffect, useState } from "react";
import updateBook from "../services/UpdateBook";
import Toast from "./Toast";
import { useNavigate } from "react-router-dom";

function UpdateBook({ book }) {

  let [editInput, setEditInput] = useState({
    id: "",
    title: "",
    author: "",
    isbn: "",
    copies: "",
    available: "",
    image_url: ""
  });

  let [toast,setToast] = useState({
    status : true,
    message : "",
  });

  let [file, setFile] = useState("");

  let navigate = useNavigate();

  let handleInput = (e) => {
    setEditInput((prevInput) => {
      return { ...prevInput, [e.target.name]: e.target.value };
    })
  }

  let handleImage = (e) => {
    setFile(e.target.files[0]);
  }


  useEffect(() => {
    setEditInput(() => {
      return {
        id: book.id || "",
        title: book.title || "",
        author: book.author || "",
        isbn: book.isbn || "",
        copies: book.copies || "",
        available: book.available || "",
        image_url : book.image_url || "",
      }
    });
  }, [book]);


  let handleSubmit = async (e) => {
    e.preventDefault();
    let request = await updateBook(editInput,file);
    setToast(() => {
      return {
        status : false,
        message : request.message
      }
    })
  }

  let handleAction = () => {
    setToast(() => {
      return {
        status : true,
        message : ""
      }
    });
    setTimeout(() => {
      navigate("/admin");
    },500);
  }

  return <section className="addbook-form">
    <form className={`add-book`} onSubmit={handleSubmit}>
      <h1>Edit book</h1>
      <div className="title input-box">
        <label htmlFor="input-title">Title:</label>
        <input type="text" placeholder="type here.." value={editInput.title} name="title" onChange={handleInput} />
      </div>
      <div className="author input-box">
        <label htmlFor="input-author">Author:</label>
        <input type="text" placeholder="type here.." id="input-author" value={editInput.author} name="author" onChange={handleInput} />
      </div>
      <div className="isbn input-box">
        <label htmlFor="input-isbn">ISBN:</label>
        <input type="text" placeholder="type here.." id="input-isbn" value={editInput.isbn} name="isbn" onChange={handleInput} />
      </div>
      <div className="copies input-box">
        <label htmlFor="input-copies">Copies:</label>
        <input type="number" placeholder="type here.." id="input-copies" value={editInput.copies} name="copies" onChange={handleInput} />
      </div>
      <div className="available-form input-box">
        <label htmlFor="input-availabe">Available:</label>
        <input type="number" placeholder="type here.." id="input-available" value={editInput.available} name="available" onChange={handleInput} />
      </div>
      <div className="input-box">
        <label htmlFor="input-image">Image:</label>
        <input type="file" placeholder="type here.." id="input-image" name="image" onChange={handleImage} />
      </div>
      <button className="add-book-btn" type="submit">Edit book</button>
    </form>
    {
      toast.status ? <></> :
      <Toast message={toast.message} action={handleAction}/>
    }
  </section>
}

export default UpdateBook;