package com.janaeswar.AMS.Controller;

import com.janaeswar.AMS.Modal.Employee;
import com.janaeswar.AMS.Modal.Request;
import com.janaeswar.AMS.Service.EmployeeService;
import com.janaeswar.AMS.Service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final RequestService requestService;

    public EmployeeController(EmployeeService employeeService,RequestService requestService) {
        this.employeeService = employeeService;
        this.requestService = requestService;
    }

    @PostMapping("/addemployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        System.out.println("Payload:\n"+employee);
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/getemployeebyid/{agencyId}")
    public ResponseEntity<?> getEmployeeByAgencyId(@PathVariable String agencyId) {
        return employeeService.getEmployeeByAgencyId(agencyId);
    }

    @PutMapping("/updateemployee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @GetMapping("/getallemployee")
    public long getAllEmployee()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getemployeebyphonenumber")
    public ResponseEntity<?> getEmployeeByPhoneNumber(@RequestParam long phoneNumber) {
        return employeeService.getEmployeeByPhoneNumber(phoneNumber);
    }

    @PostMapping("/sendrequest")
    public ResponseEntity<?> sendClaimRequest(@RequestBody Request req)
    {
        return requestService.addRequest(req);
    }

    @GetMapping("/getallrequest")
    public ResponseEntity<?> getAllRequest()
    {
        return requestService.getRequests();
    }

    @PutMapping("/toggleemployeestatus/{employeeId}")
    public ResponseEntity<?> toggleEmployeeStatus(@PathVariable String agencyId) {
        return employeeService.toggleEmployeeStatus(agencyId);
    }

        @GetMapping("/countbyagencyid/{agencyId}")
        public ResponseEntity<?> getCountOfEmployeesByAgencyId(@PathVariable String agencyId){
            return employeeService.getCountOfEmployeesByAgencyId(agencyId);
        }
}
