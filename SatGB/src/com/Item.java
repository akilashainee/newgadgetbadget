package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
private Connection connect()

{
		Connection con = null;
		try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stakeholders_gb", "root", "");
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
	
	output = "<table border='1'><tr><th>Item Name</th>"+
			 "<th>Item Catagory</th><th>Item Price</th>"+
			 "<th>Item Description</th>"+
			 "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from selling";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
	// iterate through the rows in the result set
			
	while (rs.next())
	{
		String ItemID = Integer.toString(rs.getInt("ItemID"));
		String Itemname = rs.getString("Itemname");
		String Itemcategory = rs.getString("Itemcategory");
		String Price = rs.getString("Price");
		String Discription = rs.getString("Discription");
		
	// Add into the html table
		
		//output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + itemID
				//+ "'>" + itemCode + "</td>";

		
	output += "<tr><td><input id='hidItemIDUpdate'"+
				"name='hidItemIDUpdate'"+
				"type='hidden' value='" + ItemID
				+ "'>" + Itemname + "</td>";
			output += "<td>" + Itemcategory + "</td>";
			output += "<td>" + Price + "</td>";
			output += "<td>" + Discription + "</td>";
	
	// buttons
	
	output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
	+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ ItemID + "'>" + "</td></tr>";
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


public String insertItem(String name, String cat,String price, String desc)
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
	String query = " insert into selling(`ItemID`,`Itemname`,`Itemcategory`,`Price`,`Discription`)"+ "values (?, ?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, cat );
		preparedStmt.setString(4, price );
		preparedStmt.setString(5, desc);
		
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


public String updateItem(String ID, String name, String cat,String price, String desc)
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
		String query = "UPDATE selling SET Itemname=?,Itemcategory=?,Price=?,Discription=? WHERE ItemID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, cat);
			preparedStmt.setString(3, price );
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
	
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


public String deleteItem(String iD)
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
	String query = "delete from selling where ItemID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(iD));
	
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


