import { Link } from "react-router-dom";
import "./Login.css";
import { useState } from "react";
import loginUser from "../services/loginServices";


function Login() {

    let [loginInput,setLoginInput] = useState({
        email : "",
        password : "",
    });

    let [inputError,setInputError] = useState({
        email : false,
        password : false,
    }) ;

    let [hidePassword,setHidePassword] = useState(true);

    let handleForm = async (e) => {

        setInputError({
            name: false,
            email: false,
            password: false,
            confirm_password: false,
        });
        
        setLoginInput((prevInput) => {
            return {...prevInput, [e.target.name] : e.target.value};
        });
    }


    let handleOnSubmit = async (e) => {
       
        e.preventDefault();

        if(loginInput.email == "") {
            setInputError((prevError) => {
                return {...prevError, email : true}
            });
        }

        if(loginInput.password == "") {
            setInputError((prevError) => {
                return {...prevError, password : true}
            });
        }

        const response = await loginUser(loginInput);

        if(response.true){
            console.log("login successfull")
            console.log(response);
        }

    }

    let showPassword = () => {
        setHidePassword(!hidePassword);
    }

    return <section className="form-section">
        <form className="form" onSubmit={handleOnSubmit}>
            <h1>Log in</h1>
            <div className="box">
                <label htmlFor="email">Email:</label>
                <input type="email" placeholder="type here..." id="email" name="email" onChange={handleForm} value={loginInput.email}/>
                <span style={{color : "red"}}>{inputError.email && "invalid email"}</span>
            </div>
            <div className="box">
                <label htmlFor="password">Password:</label>
                <div className="password">
                    <input type={hidePassword ? "password" : "text"} placeholder="type here..." id="password" name="password" onChange={handleForm} value={loginInput.password}/>
                    <span><i className="fa-solid fa-eye" onClick={showPassword}></i></span>
                </div>
                <span style={{color : "red"}}>{inputError.password && "invalid password"}</span>
            </div>
            <div className="extras">
                <input type="checkbox" id="checkbox"/>
                <label htmlFor="checkbox" style={{"fontSize" : "1rem"}}>remember me</label>
            </div>
            <div className="extras">
                <Link to={"#"}>forgot password</Link>
            </div>
            <div>
                <button type="submit">login</button>
            </div>
        </form>
    </section>
}

export default Login;