import EmployeeForm from '../../components/EmployeeForm/EmployeeForm'
import { EmployeeFormData } from '../../components/EmployeeForm/Schema'
import { useNavigate } from 'react-router-dom'
import { createEmployee } from '../../services/employee-service';

const AddEmployee = () => {
   const navigate = useNavigate();
   const onSubmit=(data:EmployeeFormData)=>{
        console.log(data)
        createEmployee(data).then((emp)=>{
          console.log("employee created", emp);
           navigate('/');
        }).catch((e)=> console.warn(e));
      }
  return (
    <div>
      <EmployeeForm onSubmit={onSubmit} />
    </div>
  )
}

export default AddEmployee