
import './App.css'
import Login from './pages/login.jsx';
import Register from "./pages/Register.jsx";
import { createBrowserRouter ,RouterProvider} from 'react-router-dom';
import Student from './pages/Student.jsx';
import MyBorrowedBook from './pages/MyBorrowedBooks.jsx';
import Admin from './pages/Admin.jsx';
import EditBook from './pages/EditBook.jsx';
import AddBook from './pages/AddBook.jsx';
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
    },
    {
      path : "/admin",
      element : <Admin />
    },
    {
      path : "editBook",
      element : <EditBook />
    },
    {
      path : "/addBook",
      element : <AddBook />
    }
  ])
  return <RouterProvider router={route}/>
}

export default App
