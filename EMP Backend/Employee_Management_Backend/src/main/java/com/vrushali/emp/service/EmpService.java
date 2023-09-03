package com.vrushali.emp.service;

import java.util.List;

import com.vrushali.emp.entity.empinfo;

import payload.empDto;

public interface EmpService {

	empDto createEmp(empDto edto);
	
	List<empDto> getAllEmp();
	
	empDto getEmpById(long id);
	
	//empDto getEmpByFirstname(String firstname);
	
	empDto updateEmp(empDto empdto,long id);
	List<empDto> getByFirstName(String firstname);
	List<empDto> getByLastName(String lastname);
	List<empDto> getByFullName(String fullname);
	
	List<empDto> getEmpSalaryGreaterThan5000();
	
	
	void deleteEmpById(long id);
}
