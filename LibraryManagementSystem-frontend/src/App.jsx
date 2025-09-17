
import './App.css'
import Login from './pages/login.jsx';
import Register from "./pages/Register.jsx";
import { createBrowserRouter ,RouterProvider} from 'react-router-dom';
import Student from './pages/Student.jsx';
import MyBorrowedBook from './pages/MyBorroedBooks.jsx';
function App() {

  let route = createBrowserRouter([
    {
      path : "/login",
      element : <Login />
    },
    {
      path : "/register",
      element : <Register />
    },
    {
      path : "/student",
      element : <Student />
    },
    {
      path : "/borrowedbooks",
      element : <MyBorrowedBook />
    }
  ])
  return <RouterProvider router={route}/>
}

export default App
