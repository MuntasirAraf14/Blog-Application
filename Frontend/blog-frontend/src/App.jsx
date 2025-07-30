
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import {Button} from 'reactstrap'
import Base from './components/Base'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Signup from './pages/Signup'

function App() {
  return (
   

    <BrowserRouter>
    <Routes>
      <Route path="home" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
    </Routes>
    </BrowserRouter>

);
}

export default App
