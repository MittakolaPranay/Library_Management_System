async function registerUser(userData) {
    
    let data = new URLSearchParams();
    data.append("name", userData.name);
    data.append("email", userData.email);
    data.append("password", userData.password);
    data.append("role", userData.role);

    try {
        let req = await fetch("http://localhost:8080/backend/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: data,
            credentials: "include" // important for session cookies
        });

        let response = await req.json();
        console.log(response);
        return response;

    } catch (error) {
        console.error("Error registering user:", error);
        return { status: false, message: "Network error" };
    }
}



export default registerUser;
