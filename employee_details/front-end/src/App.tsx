import './App.css'
import AddEmployee from './pages/AddEmployee/AddEmployee';
import EmployeeList from './pages/EmployeeList/EmployeeList'
import { BrowserRouter, Routes, Route } from "react-router-dom";
function App() {
  return (
    <>
     <BrowserRouter>
     <Routes>
          <Route path="/" element={<EmployeeList/>} />
          <Route path="/employee/addempoyee" element={<AddEmployee />} />
        </Routes>
     </BrowserRouter>
    </>
  )
}

export default App
