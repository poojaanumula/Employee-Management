import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { getEmployeeById } from "../../services/employee-service";
import { Link } from "react-router-dom";
import Classes from './ViewEmployee.module.scss';

const ViewEmployee = () => {
  const { id } = useParams(); 

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["employee", id],
    queryFn: () => getEmployeeById(Number(id)),
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Error fetching employee: {error instanceof Error ? error.message : "Unknown error"}</div>;
  }

  if (!data) {
    return <div>No employee data available</div>;
  }

  return (
    <div className={Classes.main}>
      <button className={Classes.home}><Link to="/" >Back to Home</Link></button>
      <h2><u>Employee Details</u></h2>
      <div className={Classes.section}>
        <div className={Classes.details}>
          <strong className={Classes.strong}>First Name:</strong> 
           {data.firstname}
         
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Middle Name:</strong> {data.middlename || "N/A"}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Last Name:</strong> {data.lastname}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Email:</strong> {data.email}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Mobile:</strong> {data.mobile}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Residential Address:</strong> {data.residentialAddress}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Employee Status:</strong> {data.employeeStatus}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Start Date:</strong> {data.startDate}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Finish Date:</strong> {data.finishDate || "N/A"}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Ongoing:</strong> {data.ongoing ? "Yes" : "No"}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Work Type Basis:</strong> {data.workTypeBasis}
        </div>
        <div className={Classes.details}>
          <strong className={Classes.strong}>Hours per Week:</strong> {data.hoursPerWeek}
        </div>
      </div>
    </div>
  );
};

export default ViewEmployee;
