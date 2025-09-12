
import './App.css'
import Login from './pages/login.jsx';
import Register from "./pages/Register.jsx";
import { createBrowserRouter ,RouterProvider} from 'react-router-dom';
import Student from './pages/Student.jsx';
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
    }
  ])
  return <RouterProvider router={route}/>
}

export default App
