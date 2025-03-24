// import { useState, useEffect } from "react";
// import Classes from './EmployeeList.module.scss';
// import { Link } from "react-router-dom";
// import {
//   Employee,
//   getAllEmployees,
//   deleteEmployee,
// } from "../../services/employee-service";

// const EmployeeList = () => {
//   const [list, setList] = useState<Employee[]>([]);

//   useEffect(() => {
//     getAllEmployees()
//       .then((employee) => {
//         setList(employee);
//       })
//       .catch((e) => console.error("Error fetching employees:", e));
//   }, []);

//   const handleDelete = async (id: number) => {
//     try {
//       await deleteEmployee(id);
//       setList(list.filter((emp) => emp.id !== id));
//     } catch (error) {
//       console.error("Error deleting employee:", error);
//     }
//   };

//   return (
//     <>
//       <div>
//         <div>
//           <div className={Classes.title}>Employee Management</div>
//         </div>
//         <button className={Classes.addemployee}>
//         <Link to= "/employee/addemployee">
//            Add Employee
//         </Link>
//         </button>
//         <div className={Classes.main}>
//           {list.map((emp) => {
//             return (
//               <div className={Classes.section} key={emp.id}>
//                 <div>
//                   <h3>{emp.firstname}</h3>
//                   <p>{emp.email}</p>
//                   <p>{emp.employeeStatus}</p>
//                 </div>
//                 <div className={Classes.buttons}>
            
//                 <Link to={`/employee/edit/${emp.id}`}>Edit Employee</Link>
//                   <button
//                     className={Classes.btn}
//                     onClick={() => handleDelete(emp.id)}
//                   >
//                     Delete
//                   </button>
//                 </div>
//               </div>
//             );
//           })}
//         </div>
//       </div>
//     </>
//   );
// };

// export default EmployeeList;

import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Classes from "./EmployeeList.module.scss";
import {
  Employee,
  getAllEmployees,
  deleteEmployee,
} from "../../services/employee-service";
import EmployeeCard from "../../components/EmployeeCard/EmployeeCard";

const EmployeeList = () => {
  const [list, setList] = useState<Employee[]>([]);

  useEffect(() => {
    getAllEmployees()
      .then((employees) => setList(employees))
      .catch((e) => console.error("Error fetching employees:", e));
  }, []);

  const handleDelete = async (id: number) => {
    try {
      await deleteEmployee(id);
      setList(list.filter((emp) => emp.id !== id));
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  };

  return (
    <div>
      <div>
        <div className={Classes.title}>Employee Management</div>
      </div>
      <button className={Classes.addemployee}>
        <Link to="/employee/addemployee">Add Employee</Link>
      </button>
      <div className={Classes.main}>
        {list.map((emp) => (
          <EmployeeCard key={emp.id} employee={emp} onDelete={handleDelete} />
        ))}
      </div>
    </div>
  );
};

export default EmployeeList;
