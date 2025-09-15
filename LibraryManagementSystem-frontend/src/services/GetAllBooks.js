async function getAllbooks() {
    try {
        let request = await fetch("http://localhost:8080/backend/getBooks",{
            method : "GET",
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });

        let response = await request.json();
        if(response.status) {
            return response;
        } else {
            return [];
        }
    } catch (e) {
        console.log(e);
    }
}

export default getAllbooks;