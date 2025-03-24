// src/pages/EditEmployee/EditEmployee.tsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import EmployeeForm from "../../components/EmployeeForm/EmployeeForm";
import { EmployeeFormData } from "../../components/EmployeeForm/Schema";
import {
  getEmployeeById,
  updateEmployee,
} from "../../services/employee-service";

const EditEmployee = () => {
  const { id } = useParams(); // Extract employee ID from URL
  const navigate = useNavigate();
  const [employeeData, setEmployeeData] = useState<EmployeeFormData | null>(
    null
  );

  useEffect(() => {
    if (id) {
      getEmployeeById(Number(id))
        .then((data) => {
          // Transform the fetched data into the format expected by EmployeeFormData
          setEmployeeData({
            firstname: data.firstname,
            middlename: data.middlename || "",
            lastname: data.lastname,
            email: data.email,
            mobile: data.mobile,
            residentialAddress: data.residentialAddress,
            employeeStatus: data.employeeStatus,
            startDate: data.startDate,
            finishDate: data.finishDate || "",
            ongoing: data.ongoing || false,
            workTypeBasis: data.workTypeBasis,
            hoursPerWeek: data.hoursPerWeek,
          });
        })
        .catch((error) => console.error("Error fetching employee:", error));
    }
  }, [id]);

  const onSubmitEditEmployee = (data: EmployeeFormData) => {
    if (id) {
      updateEmployee(Number(id), data)
        .then((updatedEmp) => {
          console.log("Employee updated:", updatedEmp);
          navigate("/"); // Redirect to the employee list page
        })
        .catch((error) => console.warn("Error updating employee:", error));
    }
  };

  if (!employeeData) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <EmployeeForm
        onSubmit={onSubmitEditEmployee}
        initialData={employeeData}
      />
    </div>
  );
};

export default EditEmployee;
