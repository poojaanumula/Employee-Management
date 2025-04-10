import { Link } from "react-router-dom";
import Classes from "./EmployeeCard.module.scss";
import { Employee } from "../../services/employee-service";

interface EmployeeCardProps {
  employee: Employee;
  onDelete: (id: number) => void;
}

const EmployeeCard = ({ employee, onDelete }: EmployeeCardProps) => {
  return (
    <>
      <div className={Classes.section}>
        <div className={Classes.details}>
          <h3 className={Classes.name}>{employee.firstname}</h3>
          <div className={Classes.email}>
            <p className={Classes.emailId}>{employee.email}</p>
            <div className={Classes.line}></div>
            <p>{employee.employeeStatus}</p>
          </div>
        </div>

        <div className={Classes.buttons}>
          <Link to={`/employee/edit/${employee.id}`} className={Classes.text}>
            Edit
          </Link>
          <button className={Classes.btn} onClick={() => onDelete(employee.id)}>
            Delete
          </button>
          <Link to={`/viewEmployee/${employee.id}`} className={Classes.view} >
           View More
          </Link>
        </div>
      </div>
      <hr></hr>
    </>
  );
};

export default EmployeeCard;
