import axios from "axios";
import { EmployeeFormData } from "../components/EmployeeForm/Schema";

export interface Employee {
  id: number;
  firstname: string;
  middlename: string | null;
  lastname: string;
  email: string;
  mobile: string;
  residentialAddress: string;
  employeeStatus: "PERMANENT" | "CONTRACT";
  startDate: string;
  finishDate: string | null;
  ongoing: boolean | null;
  workTypeBasis: "PART_TIME" | "FULL_TIME";
  hoursPerWeek: number;
}

export const getAllEmployees = async (): Promise<Employee[]> => {
  try {
    const response = await axios.get("http://localhost:8080/employee");
    return response.data;
  } catch (error) {
    throw new Error("Could not fetch employee list");
  }
};

export const deleteEmployee = async (id: number): Promise<void> => {
  try {
    await axios.delete(`http://localhost:8080/employee/${id}`);
  } catch (error) {
    throw new Error(`Could not delete employee with id: ${id}`);
  }
};
export const createEmployee = async (data: EmployeeFormData) => {
  try {
    const response = await axios.post('http://localhost:8080/employee', data, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data as Employee;
  } catch (error) {
    console.error('Error posting employee:', error);
    throw error;
  }
};

export const getEmployeeById = async (id: number): Promise<Employee> => {
  try {
    const response = await axios.get(`http://localhost:8080/employee/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching employee with ID ${id}:`, error);
    throw error;
  }
};

export const updateEmployee = async (id: number, data: EmployeeFormData) => {
  try {
    const response = await axios.put(`http://localhost:8080/employee/${id}`, data, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
     console.log("submitteed ..." , response.data)
    return response.data;
  } catch (error) {
    console.error(`Error updating employee with ID ${id}:`, error);
    throw error;
  }
};
