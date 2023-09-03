package com.vrushali.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vrushali.emp.entity.empinfo;

public interface EmpRepo extends JpaRepository<empinfo, Long>{
	
	List<empinfo> findByFirstName(String firstname);
	List<empinfo> findByLastName(String lastname);
	
	//custom query:JPQL
	@Query("SELECT e FROM empinfo e WHERE CONCAT(e.firstName,' ',e.lastName)=:fullname")
	List<empinfo> findByFullName(@Param("fullname") String fullname);
	

	
	
	
}
