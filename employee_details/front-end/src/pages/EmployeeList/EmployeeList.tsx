import { Link } from "react-router-dom";
import Classes from "./EmployeeList.module.scss";
import {
  Employee,
  getAllEmployees,
  deleteEmployee,
} from "../../services/employee-service";
import EmployeeCard from "../../components/EmployeeCard/EmployeeCard";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const EmployeeList = () => {
  const queryClient = useQueryClient();

  const {
    data: list,
    isLoading,
    isError,
    error,
  } = useQuery<Employee[]>({
    queryKey: ["employees"],
    queryFn: getAllEmployees,
  });

  const { mutate: handleDelete } = useMutation({
    mutationFn: deleteEmployee,
    onSuccess: () => {
      toast.success("Employee deleted successfully!", {
        position: "top-right",
      });
      queryClient.invalidateQueries({ queryKey: ["employees"] });
    },
    onError: () => {
      toast.error("Failed to delete employee. Please try again.", {
        position: "top-right",
      });
    },
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return (
      <div>
        Error fetching employees:{" "}
        {error instanceof Error ? error.message : "Unknown error"}
      </div>
    );
  }

  return (
    <>
      <div className={Classes.title}>Employee Management</div>
      <div className={Classes.main}>
        <button className={Classes.addemployee}>
          <Link to="/employee/addemployee" className={Classes.btn}>
            Add Employee
          </Link>
        </button>

        <div className={Classes.subTitle}>
          <h3>Our Employees</h3>
        </div>
        <hr></hr>

        <div className={Classes.main}>
          {list?.map((emp) => (
            <EmployeeCard
              key={emp.id}
              employee={emp}
              onDelete={() => handleDelete(emp.id)}
            />
          ))}
        </div>
      </div>
    </>
  );
};

export default EmployeeList;
