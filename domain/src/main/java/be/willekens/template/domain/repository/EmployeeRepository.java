package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.employee.Employee;
import be.willekens.template.domain.models.employee.Role;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {

    private static final Set<Employee> employees = new HashSet<>();

    public EmployeeRepository() {
        init();
    }

    private void init() {
        employees.add(new Employee(UUID.fromString("d774eceb-03c5-4f63-9e92-aa1025b2257f"), "Rafael","willekens.rafael@gmail.com", Role.ADMIN));
    }

    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableSet(employees);
    }
}
