import { useState } from "react";
import { useNavigate } from "react-router-dom";
import registerUser from "../services/userServices";

function Register() {

    let [registerInput, setRegisterinput] = useState({
        name: "",
        email: "",
        password: "",
        confirm_password: "",
        role : "student"
    });


    let [inputError, setInputError] = useState({
        name: false,
        email: false,
        password: false,
        confirm_password: false,
    });

    let [hidePassword, setHidePassword] = useState(true);

    let [hideConfirmPassword, setHideConfirmPasswor] = useState(true);

    let Navigate = useNavigate();

    let handleInput = (e) => {
        setInputError({
            name: false,
            email: false,
            password: false,
            confirm_password: false,
        })
        setRegisterinput((prevInput) => {
            return { ...prevInput, [e.target.name]: e.target.value };
        });
    }

    let showConfirmPassword = () => {
        setHideConfirmPasswor(!hideConfirmPassword)
    }

    let showPassword = () => {
        setHidePassword(!hidePassword)
    }

    let handleOnSubmit = async (e) => {

        e.preventDefault();

        if (registerInput.name == "") {
            setInputError((prevError) => {
                return { ...prevError, name: true }
            })
        }

        if (registerInput.email == "") {
            setInputError((prevError) => {
                return { ...prevError, email: true }
            })
        }

        if (registerInput.password == "") {
            setInputError((prevError) => {
                return { ...prevError, password: true }
            })
        }

        if (registerInput.confirm_password == "" || registerInput.confirm_password != registerInput.password) {
            setInputError((prevError) => {
                return { ...prevError, confirm_password: true }
            })
        }        
        
        const response = await registerUser(registerInput)

        if(response.status) {
            Navigate("/login");
        }else {
            alert(response.message);
        }
    }


    return <section className="form-section">
        <form className="form" onSubmit={handleOnSubmit}>
            <h1>Create Account</h1>
            <div className="box">
                <label htmlFor="name">Full name:</label>
                <input type="text" placeholder="type here..." id="name" name="name" value={registerInput.name} onChange={handleInput} />
                <span style={{ color: "red" }}>{inputError.name && "please fill this detail"}</span>
            </div>
            <div className="box">
                <label htmlFor="email">Email:</label>
                <input type="email" id="email" placeholder="type here..." name="email" value={registerInput.email} onChange={handleInput} />
                <span style={{ color: "red" }}>{inputError.email && "please fill this detail"}</span>
            </div>
            <div className="box">
                <label htmlFor="password">Password:</label>
                <div className="password">
                    <input type={hidePassword ? "password" : "text"} placeholder="type here..." id="password" name="password" value={registerInput.password} onChange={handleInput} />
                    <span><i className="fa-solid fa-eye" onClick={showPassword}></i></span>
                </div>
                <span style={{ color: "red" }}>{inputError.password && "please fill this detail"}</span>
            </div>
            <div className="box">
                <label htmlFor="confirm-password">Confirm password:</label>
                <div className="password">
                    <input type={hideConfirmPassword ? "password" : "text"} id="confirm-password" placeholder="type here..." name="confirm_password" value={registerInput.confirm_password} onChange={handleInput} />
                    <span><i className="fa-solid fa-eye" onClick={showConfirmPassword}></i></span>
                </div>
                <span style={{ color: "red" }}>{inputError.confirm_password && "please fill this detail"}</span>
            </div>
            <div className="box">
                <label htmlFor="role">Select role:</label>
                <select name="role" id="role" onChange={handleInput} value={registerInput.role}>
                    <option value="student">student</option>
                    <option value="librarian">librarian</option>
                </select>
            </div>
            <div className="extras">
                <input type="checkbox" id="checkbox" />
                <label htmlFor="checkbox" style={{ "fontSize": "1rem" }}>accept terms and policy</label>
            </div>
            <div>
                <button type="submit">Create account</button>
            </div>
        </form>
    </section>
}

export default Register;