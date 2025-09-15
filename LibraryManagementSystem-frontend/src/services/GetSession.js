async function getSession() {

    try {
        let request = await fetch("http://localhost:8080/backend/getSession",{
            method : "GET",
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials : "include"
        });

        let response = await request.json();

        return response;
    } catch (Execption){
        console.log(Execption);
    }
}

export default getSession;