import * as z from "zod";

export const employeeStatus = ["PERMANENT", "CONTRACT"] as const;

export const workTypeBasis = ["PART_TIME", "FULL_TIME"] as const;

export const schema = z.object({
  firstname: z.string().min(1, "First name is required"),
  middlename: z.string().optional(),
  lastname: z.string().min(1, "Last name is required"),
  email: z.string().email("Invalid email format"),
  mobile: z
    .string()
    .regex(/^(?:\+\d{1,3}[- ]?)?\d{10}$/, "Invalid mobile number format"),
  residentialAddress: z.string().min(1, "Residential address is required"),
  employeeStatus: z.enum(employeeStatus),
  startDate: z.string().refine((val) => !isNaN(Date.parse(val)), {
    message: "Invalid date format",
  }),
  finishDate: z.string().optional().nullable(),
  ongoing: z.boolean(),
  workTypeBasis: z.enum(workTypeBasis),
  hoursPerWeek: z
    .number()
    .min(1, "Hours per week must be at least 1")
    .max(40, "Hours per week cannot exceed 40"),
});
export type EmployeeFormData = z.infer<typeof schema>;
