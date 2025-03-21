
// import BookForm from '../components/BookForm/BookForm'
// import { BookFormData } from '../components/BookForm/Schema'
// import { createBook } from '../services/book-services'
// import { useNavigate } from 'react-router-dom'

// const NewBookPage = () => {
//   const navigate = useNavigate();
//   const onSubmit=(data:BookFormData)=>{
//     createBook(data).then((book)=>{
//       console.log("book created", book);
//        navigate('/books/' + book.id);
//     }).catch((e)=> console.warn(e));
//   }
//   return (
//     <div>
//         <h3>Create a new Book!</h3>
//       <BookForm onSubmit={onSubmit}/>
//     </div> 
//   )
// }
// export default NewBookPage

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
           navigate('/' + emp.id);
        }).catch((e)=> console.warn(e));
      }
  return (
    <div>
      <EmployeeForm onSubmit={onSubmit} />
    </div>
  )
}

export default AddEmployee
