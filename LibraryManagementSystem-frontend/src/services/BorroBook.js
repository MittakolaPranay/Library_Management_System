async function borrowBook(userId,bookId) {
    let data = new URLSearchParams();
    data.append("userId",userId);
    data.append("bookId",bookId);
    try {
        let request = await fetch("http://localhost:8080/backend/borrowBook",{
            method : "POST",
            body : data,
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });

        let response = await request.json();

        return response;

    } catch (e) {
        console.log(e);
        return {status : false};
    }
} 

export default borrowBook;