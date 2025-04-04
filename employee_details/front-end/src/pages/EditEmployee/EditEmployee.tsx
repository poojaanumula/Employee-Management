import { useParams, useNavigate } from "react-router-dom";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import EmployeeForm from "../../components/EmployeeForm/EmployeeForm";
import { EmployeeFormData } from "../../components/EmployeeForm/Schema";
import {
  getEmployeeById,
  updateEmployee,
} from "../../services/employee-service";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const EditEmployee = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["employee", id],
    queryFn: () => getEmployeeById(Number(id)),
  });

  const employeeData: EmployeeFormData | undefined = data
    ? {
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
      }
    : undefined;

  const mutation = useMutation({
    mutationFn: (formData: EmployeeFormData) =>
      updateEmployee(Number(id), formData),
    onSuccess: (updatedEmployee) => {
      console.log("Employee updated:", updatedEmployee);
      toast.success("Employee updated successfully!", {
        position: "top-right",
      });
      queryClient.invalidateQueries({ queryKey: ["employee", id] });
      navigate("/");
    },
    onError: (error:any) => {
      console.warn("Error updating employee:", error);
      let errorMessage = "Failed to update employee. Please try again.";
      if (error.response) {
        errorMessage = error.response.data?.message || errorMessage;
      } else if (error.message) {
        errorMessage = error.message;
      }
  
      toast.error(errorMessage, {
        position: "top-right",
      });
    },
  });

  const onSubmitEditEmployee = (formData: EmployeeFormData) => {
    mutation.mutate(formData);
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return (
      <div>
        Error fetching employee:{" "}
        {error instanceof Error ? error.message : "Unknown error"}
      </div>
    );
  }
  if (!employeeData) {
    return <div>No employee data available</div>;
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
