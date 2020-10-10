package com.employee.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.model.EmployeeInfo;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Long> {
	
	
	// Update all the attributes of an existing employee
	@Modifying
	@Transactional
	@Query(value = "update employee_info  set first_name = :first_name,last_name = :last_name , email_id = :email_id , age = :age, gender = :gender , address = :address where id= :id ;", nativeQuery = true)
	public int updateEmployeeInfo(@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("email_id") String email_id, @Param("age") String age, @Param("gender") String gender, @Param("address") String address, @Param("id") Long id);

	//Display non deleted records
	@Override
	@Query(value="select * from employee_info where delete_flag =false;",nativeQuery=true)
	public List<EmployeeInfo> findAll();
	
	//Display deleted records
	@Query(value="select * from employee_info where delete_flag=true;",nativeQuery =true)
	public List<EmployeeInfo> deletedEntities();

	//Soft Delete
	@Modifying
	@Transactional
	@Query(value="update employee_info set delete_flag = true where id = :id ;",nativeQuery=true)
	public void softDelete(@Param("id") Long id);
	
}
