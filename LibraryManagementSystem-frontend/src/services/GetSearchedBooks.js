async function getSearchedBooks(searchterm) {

    try {
        let request = await fetch(`http://localhost:8080/backend/search?q=${searchterm}`,{
            method : "GET",
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });

        let response = await request.json();
        console.log(response);
        if(response.status) {
            return response;
        } else {
            return []
        }
    } catch (e) {
        console.log(e)
    }
   
}

export default getSearchedBooks;