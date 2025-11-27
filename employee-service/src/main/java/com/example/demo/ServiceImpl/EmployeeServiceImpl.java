package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.lang.reflect.Field;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.ServiceInterface.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = mapper.map(dto, Employee.class);
        Employee saved = repository.save(employee);
        return mapper.map(saved, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
        return mapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream()
                .map(e -> mapper.map(e, EmployeeDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));

       
        mapper.map(dto, existing);
        existing.setId(id);

        Employee saved = repository.save(existing);
        return mapper.map(saved, EmployeeDTO.class);
    }

    
    	@Override
    	public EmployeeDTO patchEmployee(Long id, Map<String, Object> updates) {
    	    Employee employee = repository.findById(id)
    	            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    	 
    	    updates.forEach((key, value) -> {
    	        // Use reflection to update fields dynamically
    	        Field field = ReflectionUtils.findField(Employee.class, key);
    	        if (field != null) {
    	            field.setAccessible(true);
    	            ReflectionUtils.setField(field, employee, value);
    	        }
    	    });
    	 
    	    Employee savedEmployee = repository.save(employee);
    	    return mapper.map(savedEmployee, EmployeeDTO.class);
    	}
    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}