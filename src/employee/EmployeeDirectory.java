package employee;

import java.util.List;
import java.util.Optional;

public class EmployeeDirectory {
    List<Employee> employees;
    public Employee findOrInsert(String email) {
         Optional<Employee> employeeOptional = employees.stream().filter(x -> x.email.equalsIgnoreCase(email)).findFirst();
         if(employeeOptional.isPresent())
             return employeeOptional.get();
         Employee employee = new Employee(email);
         employees.add(employee);
         return employee;
    }
}
