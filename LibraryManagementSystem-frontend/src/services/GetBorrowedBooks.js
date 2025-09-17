async function getBorrowedBook(userId) {
    console.log("user id from method : ",userId);
    try {
        let request = await fetch(`http://localhost:8080/backend/getBorrowedBook?userId=${userId}`,{
            method : "GET",
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });
        let response = await request.json();
        if(response.status){
            return response;
        }else {
            console.log("user id from else :",userId)
            return [];
        }
    } catch (e) {
        console.log(e)
        return [];
    }
} 

export default getBorrowedBook;