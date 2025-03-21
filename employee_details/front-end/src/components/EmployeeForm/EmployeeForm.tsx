import Classes from './EmployeeForm.module.scss';
import { EmployeeFormData, schema } from './Schema';
import { useForm } from "react-hook-form";
import { zodResolver } from '@hookform/resolvers/zod';

interface EmployeeFormProps {
  onSubmit: (data: EmployeeFormData) => unknown;
}

const AddEmployee = ({ onSubmit }: EmployeeFormProps) => {
  const {
    register,
    handleSubmit,
    formState: { isSubmitSuccessful, errors },
    reset,
  } = useForm<EmployeeFormData>({ resolver: zodResolver(schema) });
  isSubmitSuccessful && reset();

  return (
    <div className={Classes.form}>
      <h3 className={Classes.title}>Add an Employee</h3>
      <form onSubmit={handleSubmit(onSubmit)} >
        <div className={Classes.row}>
          <p>Personal Information</p>
          <hr />
          <div className={Classes.styleRows}>
            <label htmlFor="firstname">First Name</label>
            <input 
              id="firstname" 
              type="text" 
              placeholder="John" 
              required 
              {...register('firstname')} 
            />
            {errors.firstname && (
              <small style={{ color: 'red' }}>{errors.firstname.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <div className={Classes.styleRows}>
            <label htmlFor="middlename">Middle Name</label>
            <input 
              id="middlename"
              type="text" 
          
              {...register('middlename')} 
            />
            {errors.middlename && (
              <small style={{ color: 'red' }}>{errors.middlename.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <div className={Classes.styleRows}>
            <label htmlFor="lastname">Last Name</label>
            <input 
              id="lastname"
              type="text" 
              placeholder="Smith" 
              required 
              {...register('lastname')} 
            />
            {errors.lastname && (
              <small style={{ color: 'red' }}>{errors.lastname.message}</small>
            )}
          </div>
        </div>
        <br />
        <div className={Classes.row}>
          <p>Contact Details</p>
          <hr />
          <div className={Classes.styleRows}>
            <label htmlFor="email">Email Address</label>
            <input 
              id="email"
              type="email" 
              placeholder="sam.railey@gmail.com" 
              required  
              {...register('email')} 
            />
            {errors.email && (
              <small style={{ color: 'red' }}>{errors.email.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <div className={Classes.styleRows}>
            <label htmlFor="mobile">Mobile Number</label>
            <input 
              id="mobile"
              type="tel" 
              required   
              {...register('mobile')} 
            />
            {errors.mobile && (
              <small style={{ color: 'red' }}>{errors.mobile.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <div className={Classes.styleRows}>
            <label htmlFor="residentialAddress">Residential Address</label>
            <input 
              id="residentialAddress"
              type="text" 
              required  
              {...register('residentialAddress')} 
            />
            {errors.residentialAddress && (
              <small style={{ color: 'red' }}>{errors.residentialAddress.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <p>Employee Status</p>
          <hr />
          <label>What is the contract type?</label>
          <div className={Classes.styleradio}>
            <label>
              <input
                type="radio"
                value="PERMANENT"
                required
                {...register('employeeStatus')}
              />
              Permanent
            </label>
            <label>
              <input
                type="radio"
                value="CONTRACT"
                required
                {...register('employeeStatus')}
              />
              Contract
            </label>
          </div>
          {errors.employeeStatus && (
            <small style={{ color: 'red' }}>{errors.employeeStatus.message}</small>
          )}
        </div>
        <div className={Classes.row}>
          <div className={Classes.styleRows}>
            <label htmlFor="startDate">Start Date (YYYY-MM-DD)</label>
            <input 
              id="startDate"
              type="date" 
              required  
              {...register('startDate')} 
            />
            {errors.startDate && (
              <small style={{ color: 'red' }}>{errors.startDate.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <div className={Classes.styleRows}>
            <label htmlFor="finishDate">End Date (YYYY-MM-DD)</label>
            <input 
              id="finishDate"
              type="date" 
          
              {...register('finishDate')} 
            />
            {errors.finishDate && (
              <small style={{ color: 'red' }}>{errors.finishDate.message}</small>
            )}
          </div>
        </div>
        <div className={Classes.row}>
          <label htmlFor="ongoing">
            <input 
              id="ongoing"
              type="checkbox" 
              {...register('ongoing')} 
            />
            Ongoing
          </label>
        </div>
        <div className={Classes.row}>
          <label>Is this on a full time or part time basis?</label>
          <div className={Classes.styleradio}>
            <label>
              <input
                type="radio"
                value="FULL_TIME"
                required
                {...register('workTypeBasis')}
              />
              Full time
            </label>
            <label>
              <input
                type="radio"
                value="PART_TIME"
                required
                {...register('workTypeBasis')}
              />
              Part time
            </label>
          </div>
          {errors.workTypeBasis && (
            <small style={{ color: 'red' }}>{errors.workTypeBasis.message}</small>
          )}
        </div>
        <div className={Classes.styleRows}>
          <label htmlFor="hoursPerWeek">Work Hours Per Week</label>
          <input 
            id="hoursPerWeek"
            type="number" 
            placeholder="e.g. 40" 
            required 
            {...register('hoursPerWeek', { valueAsNumber: true })} 
          />
          {errors.hoursPerWeek && (
            <small style={{ color: 'red' }}>{errors.hoursPerWeek.message}</small>
          )}
        </div>
        <button className={Classes.btn}>Create</button>
      </form>
    </div>
  );
};

export default AddEmployee;
