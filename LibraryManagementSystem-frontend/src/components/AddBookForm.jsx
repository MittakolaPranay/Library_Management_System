import React, { useState } from "react";

function AddBookForm() {
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    isbn: "",
    copies: "",
    available: "",
  });

  const [image, setImage] = useState(null);
  const [message, setMessage] = useState("");

  // Handle input changes
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // Handle file change
  const handleFileChange = (e) => {
    setImage(e.target.files[0]);
  };

  // Handle submit
  const handleSubmit = async (e) => {
    e.preventDefault();

    let data = new FormData();
    data.append("title", formData.title);
    data.append("author", formData.author);
    data.append("isbn", formData.isbn);
    data.append("copies", formData.copies);
    data.append("available", formData.available);
    if (image) {
      data.append("image", image);
    }

    try {
      const res = await fetch("http://localhost:8080/backend/addBook", {
        method: "POST",
        body: data,
        credentials: "include", // for CORS with credentials
      });

      const result = await res.json();
      if (result.status) {
        setMessage("✅ Book added successfully!");
      } else {
        setMessage("❌ " + result.message);
      }
    } catch (error) {
      setMessage("⚠️ Server error, please try again.");
      console.error(error);
    }
  };

  return (
    <div className="max-w-md mx-auto p-6 bg-white shadow rounded">
      <h2 className="text-xl font-bold mb-4">Add New Book</h2>
      <form onSubmit={handleSubmit} className="space-y-3">
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={formData.title}
          onChange={handleChange}
          className="w-full border p-2 rounded"
          required
        />
        <input
          type="text"
          name="author"
          placeholder="Author"
          value={formData.author}
          onChange={handleChange}
          className="w-full border p-2 rounded"
          required
        />
        <input
          type="text"
          name="isbn"
          placeholder="ISBN"
          value={formData.isbn}
          onChange={handleChange}
          className="w-full border p-2 rounded"
          required
        />
        <input
          type="number"
          name="copies"
          placeholder="Copies"
          value={formData.copies}
          onChange={handleChange}
          className="w-full border p-2 rounded"
          required
        />
        <input
          type="number"
          name="available"
          placeholder="Available Copies"
          value={formData.available}
          onChange={handleChange}
          className="w-full border p-2 rounded"
          required
        />
        <input
          type="file"
          name="image"
          onChange={handleFileChange}
          className="w-full"
          accept="image/*"
          required
        />
        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
        >
          Add Book
        </button>
      </form>
      {message && <p className="mt-4 text-center">{message}</p>}
    </div>
  );
}

export default AddBookForm;
