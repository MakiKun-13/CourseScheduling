package employee;

import java.util.List;

public class EmployeeDirectory {
    List<Employee> employees;
    public Employee findOrInsert(String email) {
        if(!employees.stream().allMatch(x -> x.email.equalsIgnoreCase(email))) {
            Employee employee = new Employee(email);
            employees.add(employee);
            return employee;
        }
        for (Employee employee:
             employees) {
            if(employee.email.equalsIgnoreCase(email))
                return employee;
        }// Write this better
        return null;
    }
}
