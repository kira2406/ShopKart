import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Register from './components/Register/Register';
import Login from './components/Login/Login';
import Home from './components/Home/Home';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';

function App() {
  const [user, setUser] = useState(null)
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Home user={user} />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
