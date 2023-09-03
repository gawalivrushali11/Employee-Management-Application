package com.vrushali.emp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vrushali.emp.service.EmpService;

import jakarta.validation.Valid;
import payload.empDto;

@RestController
@Validated
@RequestMapping("/api/emps")
public class EmpController {

	private EmpService empservice;

	public EmpController(EmpService empservice) {
		super();
		this.empservice = empservice;
	}
	
	//create employee rest api
	@PostMapping
	public ResponseEntity<empDto> createEmp(@Valid @RequestBody empDto empdto)
	{
		return new ResponseEntity<>(empservice.createEmp(empdto),HttpStatus.CREATED);
	}
	
	//get all employees rest api
	@GetMapping()
	public List<empDto> getAllEmps()
	{
		return empservice.getAllEmp();
	}
	
	//get all employees rest api
		@GetMapping("/bysalary")
		public List<empDto> getEmpsBySalaryGreaterThan5000()
		{
			return empservice.getEmpSalaryGreaterThan5000();
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<empDto> getById(@PathVariable(name="id") long id)
	{
		return ResponseEntity.ok(empservice.getEmpById(id));
	}
	
	@GetMapping("/firstname")
	public ResponseEntity<List<empDto>> getEmpsByFirstName(@RequestParam String firstname)
	{
		List<empDto> emps=empservice.getByFirstName(firstname);
		if(emps.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		else
			return new ResponseEntity<>(empservice.getByFirstName(firstname),HttpStatus.OK);
		//return ResponseEntity.ok();
	}
	
	@GetMapping("/lastname")
	public ResponseEntity<List<empDto>> getEmpsByLasttName(@RequestParam String lastname)
	{
		List<empDto> emps=empservice.getByLastName(lastname);
		if(emps.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		else
			return new ResponseEntity<>(empservice.getByLastName(lastname),HttpStatus.OK);
		//return ResponseEntity.ok();
	}
	
	@GetMapping("/fullname")
	public ResponseEntity<List<empDto>> getEmpsByFulltName(@RequestParam String fullname)
	{
		List<empDto> emps=empservice.getByFullName(fullname);
		if(emps.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		else
			return new ResponseEntity<>(empservice.getByFullName(fullname),HttpStatus.OK);
		//return ResponseEntity.ok();
	}
	
				
	@PutMapping("/{id}")
	public ResponseEntity<empDto> updatedEmp(@Valid @RequestBody empDto empdto,@PathVariable(name="id") long id)
	{
		empDto empResponse=empservice.updateEmp(empdto, id);
		return new ResponseEntity<>(empResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable(name="id") long id)
	{
	
		empservice.deleteEmpById(id);
		return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
	}
	
	
}

