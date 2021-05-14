package com;
import java.sql.*;
public class admin
{
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gadgetbadget","root","");
			
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
			output = "<table border='1'><tr>"
					+ "<th>AccountID</th>"
					+ "<th> Name</th>" +
					"<th>Address</th>" + 
					"<th>Email</th>" +
					"<th>Password</th>"+
					"<th>Contact number</th>"+
					"<th>Update</th>"
					+ "<th>Remove</th></tr>"; 
					
			String query = "select * from admin";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			
			while (rs.next())
			{
				String accountID = Integer.toString(rs.getInt("accountID")); 
				String Name = rs.getString("Name"); 
				String Address = rs.getString("Address"); 
				String email = rs.getString("email"); 
				String password = rs.getString("password");
				String ConNum = rs.getString("ConNum");
				// Add into the html table
				output += "<tr><td>" + accountID + "</td>"; 
				output += "<td>" + Name + "</td>"; 
				output += "<td>" + Address + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" + password + "</td>";
				output += "<td>" + ConNum + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='registration.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='accountID' type='hidden' value='" + accountID 
						+ "'>" + "</form></td></tr>"; 
			}
				con.close();
			// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the item.";
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
			String query = " insert into admin (`accountID`,`Name`,`Address`,`email`,`password`,`ConNum`)"
					+ " values (?, ?, ?, ?, ?,?)"; 

		PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		// binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, Address); 
		 preparedStmt.setString(4, emailadd); 
		 preparedStmt.setString(5, pass); 
		 preparedStmt.setString(6, ConNum);

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
				String query = "UPDATE admin SET Name=?,Address=?,email=?,password=?,ConNum=? WHERE accountID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, Name); 
			 	preparedStmt.setString(2, Address); 
			 	preparedStmt.setString(3, emailadd);
			 	preparedStmt.setString(4, pass); 
			 	preparedStmt.setString(5, ConNum);
			 	preparedStmt.setInt(6, Integer.parseInt(ID)); 
				con.close();
				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
				System.err.println(e.getMessage());
			}
		return output;
	}
	
	public String deleteItem(String itemID)
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
			String query = "delete from items where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}