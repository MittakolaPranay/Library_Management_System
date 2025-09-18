async function loginUser(userData) {


    let data = new URLSearchParams();
    data.append("email",userData.email);
    data.append("password",userData.password);
    try {
        let req = await fetch(`http://localhost:8080/backend/login`, {
            method: "POST",
            body : data,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            credentials: "include" // important for session cookies
        });

        let res = await req.json();

        return res;

    } catch (error) {
        console.error("Error registering user:", error);
        return { status: false,}
    }
}

export default loginUser;