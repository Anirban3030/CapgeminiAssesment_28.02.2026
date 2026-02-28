package com.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.student.model.Student;

public class OracleStudentDAO {
	private Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "1234";

        return DriverManager.getConnection(url, username, password);
    }

	@Override
	public void add(Student student) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		String query = "INSERT INTO student(name,email,age,mobile) VALUES(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, student.getName());
		statement.setString(2, student.getEmail());
		statement.setInt(3, student.getAge());
        statement.setLong(4, student.getMobile());
        statement.executeUpdate();
        connection.close(); 
        System.out.println("Studentdb Added"); 
	}

	@Override
	public List<Student> allStudent() throws Exception {
		// TODO Auto-generated method stub
	    Connection connection = getConnection();
	    List<Student> list = new ArrayList<>();
	    String sql = "SELECT * FROM students";
	    PreparedStatement statement = connection.prepareStatement(sql);
	    ResultSet result = statement.executeQuery();

	    while (result.next()) {
	         Student st = new Student(
	            result.getInt("id"),
	            result.getString("name"),
	            result.getString("email"),
	            result.getInt("age"),
	            result.getLong("mobile")
	         );
	         list.add(st);
	        }
	    connection.close();
	    return list;
	}

	@Override
	public void update(Student student) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		String query = "UPDATE students SET email=? WHERE mobile=?"; 
		PreparedStatement statement = connection.prepareStatement(query);	        
		statement.setString(1, student.getName());
		statement.setString(2, student.getEmail());
		statement.setInt(3, student.getAge());
		statement.setLong(4, student.getMobile());
		statement.setInt(5, student.getId());
		statement.executeUpdate();
	    connection.close();
	    System.out.println("Studentdb Updated ");
		
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
        String query = "DELETE FROM students WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
        System.out.println("row Deleted");
	}

}
