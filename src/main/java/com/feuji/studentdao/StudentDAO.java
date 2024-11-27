package com.feuji.studentdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.feuji.bean.Student;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class StudentDAO {
	public  JsonArray display()
	{
		try 
		{	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schema1","root","Thiru@123");
        // Create a SQL statement to retrieve data from the database
        String sql = "SELECT * FROM student";

        // Create a prepared statement
        PreparedStatement statement = con.prepareStatement(sql);

        // Execute the query and retrieve the result set
        ResultSet rs = statement.executeQuery();
        JsonArray jsonArray = new JsonArray();
        while (rs.next()) {
            // Create a JSON object to represent a single record
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", rs.getInt("id"));
            jsonObject.addProperty("name", rs.getString("name"));
            jsonObject.addProperty("phno", rs.getLong("phno"));
            jsonObject.addProperty("city", rs.getString("city"));
            // Add the JSON object to the JSON array
            jsonArray.add(jsonObject);    
            
        }
        rs.close();
        statement.close();
        con.close();
        return jsonArray;
		}  
	    catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	  }	
		return null;	
	   }

	public int insertingRecord(Student student) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Schema1", "root", "Thiru@123");
			PreparedStatement pstmt = con.prepareStatement("insert into student values(?,?,?,?)");
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setLong(3, student.getPhno());
			pstmt.setString(4, student.getCity());
			int res = pstmt.executeUpdate();
			System.out.println("daoinserting");
			pstmt.close();
			con.close();
			return res;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int updatingRecord(Student student) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Schema1", "root",
					"Thiru@123");
			PreparedStatement preparedStatement = connection
					.prepareStatement("update student set name=?,phno=?,city=?  where id=?");
			preparedStatement.setString(1, student.getName());
			preparedStatement.setLong(2, student.getPhno());
			preparedStatement.setString(3, student.getCity());
			preparedStatement.setInt(4, student.getId());

			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int deletingRecord(Student student) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Schema1", "root",
					"Thiru@123");
			PreparedStatement preparedStatement = connection.prepareStatement("delete from student where id=?");

			preparedStatement.setInt(1, student.getId());

			int result = preparedStatement.executeUpdate();
			connection.close();
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public  JsonArray searchRecord(Student student) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Schema1", "root",
					"Thiru@123");
			PreparedStatement preparedStatement = connection.prepareStatement("select *  from student where id=?");

			preparedStatement.setInt(1, student.getId());

			ResultSet resultset=preparedStatement.executeQuery();
			 JsonArray jsonArray2 = new JsonArray();
		        while (resultset.next()) {
		            // Create a JSON object to represent a single record
		            JsonObject jsonObject = new JsonObject();
		            jsonObject.addProperty("id",resultset.getInt("id"));
		            jsonObject.addProperty("name", resultset.getString("name"));
		            jsonObject.addProperty("phno", resultset.getLong("phno"));
		            jsonObject.addProperty("city", resultset.getString("city"));
		            // Add the JSON object to the JSON array
		            jsonArray2.add(jsonObject);    
		            
		        }
		        resultset.close();
		        preparedStatement.close();
			connection.close();
			
			return jsonArray2;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	       return null;

	}
	}
