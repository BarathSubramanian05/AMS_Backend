package com.janaeswar.AMS.Service;

import com.janaeswar.AMS.Modal.Employee;
import com.janaeswar.AMS.Repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<?> getEmployees() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getById(String id)
    {
        if(!employeeRepository.existsById(id)) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        Employee e = employeeRepository.findById(id).orElse(null);
        return new ResponseEntity<>(e,HttpStatus.OK);
    }

    public ResponseEntity<?> addEmployee(Employee employeeRequest) {
        Optional<Employee> lastEmployee = employeeRepository.findTopByOrderByEmployeeIdDesc();
        int newEmpId = 101;
        if (lastEmployee.isPresent()) {
            try {
                newEmpId = Integer.parseInt(lastEmployee.get().getEmployeeId()) + 1;
            } catch (NumberFormatException e) {
                return ResponseEntity
                        .internalServerError()
                        .body("Failed to parse existing employeeId: " + lastEmployee.get().getEmployeeId());
            }
        }
        Employee employee = new Employee();
        employee.setEmployeeId(String.valueOf(newEmpId));
        employee.setName(employeeRequest.getName());
        employee.setGender(employeeRequest.getGender());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setAddress(employeeRequest.getAddress());
        employee.setRole(employeeRequest.getRole());
        employee.setSalary(employeeRequest.getSalary());
        employee.setAgencyId(employeeRequest.getagencyId());

        employeeRepository.save(employee);

        return ResponseEntity.ok("Employee added successfully.");
    }

    public ResponseEntity<?> deleteEmployee(String id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Employee with ID '" + id + "' not found.");
        }

        employeeRepository.deleteById(id);

        return ResponseEntity.ok("Employee with ID '" + id + "' deleted successfully.");
    }

    public ResponseEntity<?> getEmployeeByAgencyId(String agencyId) {
        List<Employee> employees = employeeRepository.findByAgencyId(agencyId);

        if (employees.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("No employees found for agency ID: " + agencyId);
        }

        return ResponseEntity.ok(employees);
    }

    public ResponseEntity<?> updateEmployee(Employee employeeRequest) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeRequest.getEmployeeId());

        if (employeeOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Employee with ID '" + employeeRequest.getEmployeeId() + "' not found.");
        }

        Employee employee = employeeOpt.get();

        // Update fields
        employee.setName(employeeRequest.getName());
        employee.setGender(employeeRequest.getGender());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setAddress(employeeRequest.getAddress());
        employee.setRole(employeeRequest.getRole());
        employee.setSalary(employeeRequest.getSalary());
        employee.setAgencyId(employeeRequest.getagencyId());

        employeeRepository.save(employee);

        return ResponseEntity.ok("Employee with ID '" + employeeRequest.getEmployeeId() + "' updated successfully.");
    }

    public ResponseEntity<?> getEmployeeByPhoneNumber(long phoneNumber) {
        Optional<Employee> employee = employeeRepository.findByPhoneNumber(phoneNumber);
        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().body("employee phone number not found");
        }
        return ResponseEntity.ok(employee);
    }
}