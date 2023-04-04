package com.example.employeems.controller;

import com.example.employeems.dto.EmployeeDTO;
import com.example.employeems.dto.ResponseDTO;
import com.example.employeems.entity.Employee;
import com.example.employeems.repo.EmployeeRepo;
import com.example.employeems.service.EmployeeService;
import com.example.employeems.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value="api/v1/employee")

public class EmployeeController {
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping("/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String res = employeeService.saveEmployee(employeeDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(employeeDTO);
                return  new ResponseEntity( responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMassage("Employee Registered");
                responseDTO.setContent(employeeDTO);
                return  new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST);

            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMassage("Error");
                responseDTO.setContent(null);
                return  new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST);

            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return  new ResponseEntity( responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String res = employeeService.updateEmployee(employeeDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(employeeDTO);
                return  new ResponseEntity( responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMassage("Not a Registered Employee");
                responseDTO.setContent(employeeDTO);
                return  new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST);

            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMassage("Error");
                responseDTO.setContent(null);
                return  new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST);

            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return  new ResponseEntity( responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllEmployee")
    public ResponseEntity getAllEmployees(){
        try{
           List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
           responseDTO.setCode(VarList.RSP_SUCCESS);
           responseDTO.setMassage("Success");
           responseDTO.setContent(employeeDTOList);
           return  new ResponseEntity( responseDTO, HttpStatus.ACCEPTED);
//           return new ResponseEntity(responseDTO,HttpStatus.);

        }catch(Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//            ResponseEntity.internalServerError();
        }
    }
    @GetMapping ("/searchEmployee/{empId}")
    public ResponseEntity searchEmployee(@PathVariable int empId){

        try {
            EmployeeDTO employeeDTO=employeeService.searchEmployee(empId);

            if (employeeDTO!=null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(employeeDTO);
                return  new ResponseEntity( responseDTO, HttpStatus.ACCEPTED);

            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("No Employee Available for this empId");
                responseDTO.setContent(null);
                return  new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST);

            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return  new ResponseEntity( responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable int empId){
        try {
            String res=employeeService.deleteEmployee(empId);
            if (res=="00"){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(null);
                return  new ResponseEntity( responseDTO, HttpStatus.ACCEPTED);
            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("No Employee Available for this empId");
                responseDTO.setContent(null);
                return  new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return  new ResponseEntity( responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
