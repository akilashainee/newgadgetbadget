package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cus1 {

	private Connection connect()

	{
			Connection con = null;
			try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cc", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			return con;
	}


	public String readItems()
		{
			String output = "";
		try
		{
			Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for reading.";
		}
		
		// Prepare the html table to be displayed
		
		output = "<table border='1'><tr><th>Customer Name</th>"+
				 "<th>Address</th><th>Email</th>"+
				 "<th>password</th>"+
				 "<th>Phone Number</th>"+
				 "<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from customer";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
		// iterate through the rows in the result set
				
		while (rs.next())
		{
			String ID = Integer.toString(rs.getInt("ID"));
			String C_Name = rs.getString("C_Name");
			String C_Address = rs.getString("C_Address");
			String C_email = rs.getString("C_email");
			String C_password = rs.getString("C_password");
			String C_ConNum = rs.getString("C_ConNum");

			
		// Add into the html table
			
		output += "<tr><td><input id='hidIDUpdate'"+
					"name='hidIDUpdate'"+
					"type='hidden' value='" + ID
					+ "'>" + C_Name + "</td>";
				output += "<td>" + C_Address + "</td>";
				output += "<td>" + C_email + "</td>";
				output += "<td>" + C_password + "</td>";
				output += "<td>" + C_ConNum + "</td>";
		
		// buttons
		
		output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
		+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ ID + "'>" + "</td></tr>";
		}
		con.close();
		
		// Complete the html table
		
		output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
			return output;
		}


	public String insertItem(String name, String Address, String emailadd, String pass , String ConNum)
		{
				String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{
		return "Error while connecting to the database for inserting.";
		}
		
		// create a prepared statement
		String query = " insert into customer(`ID`,`C_Name`,`C_Address`,`C_email`,`C_password`,`C_ConNum`)"+ "values (?, ?, ?, ?, ?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, Address );
			preparedStmt.setString(4, emailadd );
			preparedStmt.setString(5, pass);
			preparedStmt.setString(5, ConNum);
			
		// execute the statement
		preparedStmt.execute();
		con.close();
		
		String newItems = readItems();
		output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
			return output;
		}


	public String updateItem(String ID, String Name, String Address, String emailadd, String pass , String ConNum)
		{
				String output = "";
		try
		{
			Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for updating.";
		}
		
		// create a prepared statement
			String query = "UPDATE cutomer SET C_Name=?,C_Address=?,C_email=?,C_password=?,C_ConNum=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
				preparedStmt.setString(1, Name);
				preparedStmt.setString(2, Address);
				preparedStmt.setString(3, emailadd );
				preparedStmt.setString(4, pass);
				preparedStmt.setString(5, ConNum);
				preparedStmt.setInt(6, Integer.parseInt(ID));
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newItems = readItems();
		output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
			return output;
		}


	public String deleteItem(String cusID)
		{
				String output = "";
		try
		{
			Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for deleting.";
		}
		
		// create a prepared statement
		String query = "delete from customer where ID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(cusID));
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newItems = readItems();
		output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
		}
		catch (Exception e)
		{
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
		System.err.println(e.getMessage());
		}
		return output;
		}
	
}



