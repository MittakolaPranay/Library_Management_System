async function deleteBook(bookId) {
    
    try {
        let request = await fetch(`http://localhost:8080/backend/delete?id=${bookId}`,{
            method : "DELETE",
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });

        let response = await request.json();
        return response;
    } catch (e) {
        console.log(e)
        return {status : false,message : "Server error"}
    }
}

export default deleteBook;