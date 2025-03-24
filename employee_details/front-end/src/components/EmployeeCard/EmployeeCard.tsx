import { Link } from "react-router-dom";
import Classes from "./EmployeeCard.module.scss";
import { Employee } from "../../services/employee-service";

interface EmployeeCardProps {
  employee: Employee;
  onDelete: (id: number) => void;
}

const EmployeeCard = ({ employee, onDelete }: EmployeeCardProps) => {
  return (
    <div className={Classes.section}>
      <div>
        <h3>{employee.firstname}</h3>
        <p>{employee.email}</p>
        <p>{employee.employeeStatus}</p>
      </div>
      <div className={Classes.buttons}>
        <Link to={`/employee/edit/${employee.id}`}>Edit Employee</Link>
        <button className={Classes.btn} onClick={() => onDelete(employee.id)}>
          Delete
        </button>
      </div>
    </div>
  );
};

export default EmployeeCard;
