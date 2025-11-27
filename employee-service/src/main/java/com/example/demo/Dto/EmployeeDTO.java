package com.example.demo.Dto;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Long id;
    private String name;
    private String email;
    private String department;
    private double salary;

}
