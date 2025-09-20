import { NavLink } from "react-router-dom";
import AdminDashboard from "../components/AdminDashboard";
import { useState,useEffect } from "react";
import getSession from "../services/GetSession";


function Admin() {
    let [state, setState] = useState({});

    useEffect(() => {
        async function setSession() {
            let request = await getSession();
            setState(() => {
                return request;
            });
        }
        setSession();
    },[])
    return state.status && state.userRole == "librarian"? <AdminDashboard /> : <div>
        <p>Your session has expired. Please <NavLink to={"/login"}>log in</NavLink> again.</p>
    </div>
}
export default Admin;