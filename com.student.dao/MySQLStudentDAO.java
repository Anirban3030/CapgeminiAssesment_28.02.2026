package com.student.dao;

import com.student.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class MySQLStudentDAO implements StudentDAO{
	private Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/studentdb";
        String username = "postgres";
        String password = "user113030";

        return DriverManager.getConnection(url, username, password);
    }

	@Override
	public void add(Student student) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		String query = "INSERT INTO students(name,email,age,mobile) VALUES(?,?,?,?)";
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
		statement.setString(1,student.getEmail());
		statement.setLong(2, student.getMobile());
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
