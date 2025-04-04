import EmployeeForm from "../../components/EmployeeForm/EmployeeForm";
import { EmployeeFormData } from "../../components/EmployeeForm/Schema";
import { useNavigate } from "react-router-dom";
import { createEmployee } from "../../services/employee-service";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AddEmployee = () => {
  const navigate = useNavigate();

  const onSubmit = (data: EmployeeFormData) => {
    console.log(data);
    createEmployee(data)
      .then((emp) => {
        console.log("Employee created", emp);
        toast.success("Employee added successfully!", {
          position: "top-right",
        });
        navigate("/");
      })
      .catch((e: any) => {
        console.warn("Error creating employee:", e);
        const errorMessage =
          e.message || "Failed to add employee. Please try again.";
        toast.error(errorMessage, { position: "top-right" });
      });
  };

  return (
    <div>
      <EmployeeForm onSubmit={onSubmit} />
    </div>
  );
};

export default AddEmployee;
