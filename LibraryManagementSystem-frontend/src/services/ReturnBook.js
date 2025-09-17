async function returnBook(userId,bookid) {

    let data = new URLSearchParams();
    data.append("userId",userId);
    data.append("bookId",bookid);
    try {
        let request = await fetch("http://localhost:8080/backend/returnBook",{
            body : data,
            method : "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });

        let response = await request.json();
        if(!response.status) {
            console.log("false from api");
            return response.status;
        }else {
            return response.status;
        }
    } catch (e) {
        console.log(e);
        return false;
    }
}

export default returnBook;