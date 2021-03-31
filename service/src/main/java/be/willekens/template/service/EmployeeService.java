package be.willekens.template.service;

import be.willekens.template.domain.models.employee.Employee;
import be.willekens.template.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Collection<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public boolean isAdmin(String authorizationId) {
        return employeeRepository.getAllEmployees().stream()
                .anyMatch(id -> id.getId().toString().equals(authorizationId));
    }
}
