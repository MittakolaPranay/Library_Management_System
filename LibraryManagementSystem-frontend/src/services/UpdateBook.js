async function updateBook(book,image) {

    console.log(book)

    let data = new FormData();
    data.append("id",book.id);
    data.append("title",book.title);
    data.append("author",book.author);
    data.append("copies",book.copies);
    data.append("available",book.available);
    data.append("isbn",book.isbn);

    if(image) {
        data.append("image",image);
    }else {
        data.append("image_url",book.image_url);
    }
    try {
        let request = await fetch("http://localhost:8080/backend/update",{
            method : "POST",
            body : data
        });

        let response = await request.json();

        return response;

    } catch (e) {
        console.log(e)
        return {status : false}
    }
}

export default updateBook;