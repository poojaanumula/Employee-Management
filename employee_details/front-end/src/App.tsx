
import './App.css'
import AddEmployee from './pages/AddEmployee/AddEmployee';
import EditEmployee from './pages/EditEmployee/EditEmployee';
import EmployeeList from './pages/EmployeeList/EmployeeList'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
function App() {
  return (
    <>
     <BrowserRouter>
     <ToastContainer />
     <Routes>
          <Route path="/" element={<EmployeeList/>} />
          <Route path="/employee/addemployee" element={<AddEmployee />} />
          <Route path="/employee/edit/:id" element={<EditEmployee />} />
        </Routes>
     </BrowserRouter>
    </>
  )
}

export default App