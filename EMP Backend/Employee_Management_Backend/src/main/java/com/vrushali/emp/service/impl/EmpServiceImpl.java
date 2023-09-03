package com.vrushali.emp.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vrushali.emp.entity.empinfo;
import com.vrushali.emp.repository.EmpRepo;
import com.vrushali.emp.service.EmpService;
import com.vrushali.emp.exception.ResourceNotFoundException;

import payload.empDto;


@Service
public class EmpServiceImpl implements EmpService  {
	
	private EmpRepo emprepo; 
	
	
	public EmpServiceImpl(EmpRepo emprepo) {
		super();
		this.emprepo = emprepo;
	}


	@Override
	public empDto createEmp(empDto edto)
	{
		
		//Convert DTO to entity
		empinfo e=mapToEntity(edto);
		empinfo newemp=emprepo.save(e);
		
		//Convert entity to DTO
		empDto empResponse=mapToDTO(newemp);
		
		return empResponse;
	}
	
	@Override
	public List<empDto> getAllEmp()
	{
		List<empinfo> emps=emprepo.findAll();
		return emps.stream().map(e->mapToDTO(e)).collect(Collectors.toList());
		
	}
	
	@Override
	public empDto getEmpById(long id)
	{
		empinfo emp=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
		return mapToDTO(emp);
	}
	


	@Override
	public List<empDto> getByFirstName(String firstname) {
		List<empinfo> emps=emprepo.findByFirstName(firstname);
		
		return emps.stream().map(e->mapToDTO(e)).collect(Collectors.toList());
	}
	
	@Override
	public List<empDto> getByLastName(String lastname) {
		List<empinfo> emps=emprepo.findByLastName(lastname);
		
		return emps.stream().map(e->mapToDTO(e)).collect(Collectors.toList());
	}
	
	@Override
	public List<empDto> getByFullName(String fullname) {
		List<empinfo> emps=emprepo.findByFullName(fullname);
		
		return emps.stream().map(e->mapToDTO(e)).collect(Collectors.toList());
	}
	
	
	
	@Override
	public empDto updateEmp(empDto empdto, long id) 
	{
		empinfo emp=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
		emp.setAddress(empdto.getAddress());
		emp.setEmail(empdto.getEmail());
		emp.setFirstName(empdto.getFirstName());
		emp.setLastName(empdto.getLastName());
		emp.setPhone(empdto.getPhone());
		emp.setSalary(emp.getSalary());
		
		empinfo updatedemp=emprepo.save(emp);
		
		return mapToDTO(updatedemp);
	}
	
	@Override
	public void deleteEmpById(long id) {
		// TODO Auto-generated method stub
		empinfo emp=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
		emprepo.delete(emp);
	}
	
	@Override
	public List<empDto> getEmpSalaryGreaterThan5000() {
		List<empinfo> emps=emprepo.findAll()
							.stream().filter(e->e.getSalary()>5000)
							.sorted(Comparator.comparing(e->e.getFirstName().toLowerCase()))
							.collect(Collectors.toList());
		//emps.forEach(e->System.out.println(e));

		return emps.stream().map(e->mapToDTO(e)).collect(Collectors.toList());
		
	}
	
	//Convert Entity to DTO
	private empDto mapToDTO(empinfo e)
	{
		empDto empdto=new empDto();
		empdto.setEmpId(e.getEmpId());
		empdto.setAddress(e.getAddress());
		empdto.setEmail(e.getEmail());
		empdto.setFirstName(e.getFirstName());
		empdto.setLastName(e.getLastName());
		empdto.setPhone(e.getPhone());
		empdto.setSalary(e.getSalary());
		return empdto;	
	
	}

	private empinfo mapToEntity(empDto empdto)
	{
		empinfo emp=new empinfo();
		emp.setAddress(empdto.getAddress());
		emp.setEmail(empdto.getEmail());
		emp.setFirstName(empdto.getFirstName());
		emp.setLastName(empdto.getLastName());
		emp.setPhone(empdto.getPhone());
		emp.setSalary(empdto.getSalary());
		return emp;
	}





}
