package com.example.demo.ServiceInterface;

import java.util.List;
import java.util.Map;

import com.example.demo.Dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO dto);
    EmployeeDTO getEmployee(Long id);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);       
    EmployeeDTO patchEmployee(Long id, Map<String, Object> updates);
    void deleteEmployee(Long id);
}
