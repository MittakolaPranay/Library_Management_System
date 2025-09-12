import { Link } from "react-router-dom";
import "./Nav.css";
import { useState } from "react";

function Nav({setInput}) {

    let [showMenu, setShowMenu] = useState(false);

    let [searchInput, setSearchInput] = useState("");

    let handleSearchInput = (e) => {
        setSearchInput(() => {
            return e.target.value;
        });
    }

    let handleSubmit = (e) => {
        e.preventDefault();
        setInput(searchInput);
    }
    return (
        <>
            <nav className="navbar">
                <div className="navbar-box">
                    <h1 className="nav-heading">Library System</h1>

                    <form className="nav-form" onSubmit={handleSubmit}>
                        <input type="search" placeholder="Search here..." value={searchInput} onChange={handleSearchInput} name="searchInput"/>
                        <button type="submit">Search</button>
                    </form>
                </div>
                <span className="nav-icon" onClick={() => setShowMenu(!showMenu)}>
                    <i className="fa-solid fa-bars fa-2x"></i>
                </span>
            </nav>
            <div className="menu">
                <ul className={showMenu ? "showmenu" : "hidemenu"}>
                    <li><Link to={"#"}>Profile</Link></li>
                    <li><Link to={"#"}>Log out</Link></li>
                </ul>
            </div>

        </>
    );
}

export default Nav;



