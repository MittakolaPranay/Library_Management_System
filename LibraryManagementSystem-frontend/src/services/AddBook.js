async function addBook(book,image) {

    let data = new FormData();
    data.append("title",book.title);
    data.append("author",book.author);
    data.append("isbn",book.isbn);
    data.append("copies",book.copies);
    data.append("available",book.available);
    data.append("image",image);
    try {
        let request = await fetch("http://localhost:8080/backend/addBook",{
            method : "POST",
            body : data
        });
        let response = await request.json();
        return response;
    } catch(exception) {
        console.log(exception);
        return {status : false,message : "error from frontend"}
    }
}

export default addBook;