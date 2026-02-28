package com.student.dao;

import java.util.List;

import com.student.model.*;
public interface StudentDAO {
	void add(Student student) throws Exception; 
	
	List<Student> allStudent() throws Exception; 
	
	void update(Student student) throws Exception;
	
	void delete(int id) throws Exception;
}
