package model;


import java.sql.*; 

public class Selling {
	//A common method to connect to the DB
		private Connection connect()
		 { 
			 Connection con = null; 
			 try
			 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ stakeholders_gb", "root", ""); 
			 } 
			 catch (Exception e) 
			 {e.printStackTrace();} 
			 return con; 
		 } 
		
		/*public String insertselling(String Itemname, String Itemcategory, String Price, String Discription) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 // create a prepared statement
			 String query = " insert into selling (`ItemID`, `Itemname`, `Itemcategory`, `Price`, `Discription`)"+ " values (?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, Itemname); 
			 preparedStmt.setString(3,Itemcategory); 
			 preparedStmt.setString(4, Price); 
			 preparedStmt.setString(5, Discription); 
			// execute the statement3
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 } */
		public String insertItem(String Iname, String Icategory, String price, String discription) 
		{ 

			String output = ""; 

			try
			{ 

				Connection con = connect(); 

				if (con == null) 

				{return "Error while connecting to the database for inserting."; } 

				// creating a prepared statement
				
				String query = " insert into selling (`ItemID`,`Itemname`,`Itemcategory`,`Price`,`Discription`)"
							+ " values (?, ?, ?, ?, ?)"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				 /// binding values
				
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, Iname); 
				 preparedStmt.setString(3, Icategory); 
				 preparedStmt.setString(4, price); 
				 preparedStmt.setString(5, discription); 
				 

				 /// executing the statement
				 
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				 
				output = "Error while inserting the item."; 
				System.err.println(e.getMessage()); 
				 
			} 
			return output; 
		}
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		public String readItems() 
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>ItemID</th><th>Itemname</th>" +
			 "<th>Itemcategory</th>" + 
			 "<th>Price</th>" +
			 "<th>Discription</th>" +
			 "<th>Update</th>"
			 + "<th>Remove</th></tr>"; 
			 
			 String query = "select * from selling"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String ItemID = Integer.toString(rs.getInt("ItemID")); 
			 String Itemname = rs.getString("Itemname"); 
			 String Itemcategory = rs.getString("Itemcategory"); 
			 String Price = Double.toString(rs.getDouble("Price")); 
			 String Discription = rs.getString("Discription"); 
			 /// Add into the html table
			 output += "<tr><td>" + Itemname + "</td>"; 
			 output += "<td>" + Itemcategory + "</td>"; 
			 output += "<td>" +Price + "</td>"; 
			 output += "<td>" + Discription + "</td>"; 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='ItemID' type='hidden' value='" + ItemID 
			 + "'>" + "</form></td></tr>"; 
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
		
		public String updateselling(String ID, String Iname, String Icategory, String price, String discription)
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE selling SET Itemname=?,Itemcategory=?,Price=?,Discription=? WHERE ItemID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, Iname); 
			 preparedStmt.setString(2, Icategory); 
			 preparedStmt.setString(3, price);
			 preparedStmt.setString(4, discription);
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 

			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 } 
		
		public String deleteItem(String ItemID) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from selling where ItemID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(ItemID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
	 }

}
