package model;
import java.sql.*; 

public class payment {

	//A common method to connect to the DB
		private Connection connect()
		 { 
			 Connection con = null; 
			 try
			 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb", "root", ""); 
			 } 
			 catch (Exception e) 
			 {e.printStackTrace();} 
			 return con; 
		 } 
		
		public String insertpayment(String amount, String cardName, String cardType,String cardNo,String expMonth,String expYear,String CVV) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 // create a prepared statement
			 String query = " insert into payment (`PID`,`amount`,`cardNo`,`cardName`,`cardType`,`expMonth`,`expYear`,`CVV`)"+ " values (?, ?, ?, ?, ?,?,?,?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, amount); 
			 preparedStmt.setString(3, cardNo); 
			 preparedStmt.setString(4, cardName); 
			 preparedStmt.setString(5, cardType); 
			 preparedStmt.setString(6, expMonth);
			 preparedStmt.setString(7, expYear);
			 preparedStmt.setString(8, CVV);
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
		 } 
		
		public String readItems() 
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>PID</th><th>amount</th>" +
			 "<th>cardNo</th>" + 
			 "<th>cardName</th>" +
			 "<th>cardType</th>" +
			 "<th>expMonth</th>" +
			 "<th>expYear</th>" +
			 "<th>CVV</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from payment"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String PID = Integer.toString(rs.getInt("PID")); 
			 String amount =  rs.getString("amount");
			 String cardNo =  rs.getString("cardNo");
			 String cardName = rs.getString("cardName"); 
			 String cardType = rs.getString("cardType"); 
			 String expMonth =  rs.getString("expMonth");
			 String expYear =  rs.getString("expYear");
			 String CVV =  rs.getString("CVV");
			 
			 // Add into the html table
			 output += "<tr><td>" + PID + "</td>"; 
			 output += "<td>" + amount + "</td>"; 
			 output += "<td>" + cardNo + "</td>"; 
			 output += "<td>" + cardName + "</td>"; 
			 output += "<td>" + cardType + "</td>"; 
			 output += "<td>" + expMonth + "</td>"; 
			 output += "<td>" + expYear + "</td>"; 
			 output += "<td>" + CVV + "</td>"; 
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' value='" + PID 
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
		
		public String updatepayment(String PID, String amount, String cardNo, String cardName, String cardType,String expMonth,String expYear,String CVV)
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE payment SET amount=?,cardNo=?,cardName=?,cardType=?,expMonth=?,expYear=?,CVV=? WHERE PID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			/* preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, cardType); 
			 preparedStmt.setString(3, cardName); 
			 preparedStmt.setString(4,amount); 
			 preparedStmt.setString(5, cardNo); 
			 preparedStmt.setString(6, expMonth);
			 preparedStmt.setString(7, expYear);
			 preparedStmt.setString(8, CVV);
			 preparedStmt.setInt(9, Integer.parseInt(PID)); */
			 
			 preparedStmt.setString(1, amount); 
			 preparedStmt.setString(2, cardNo); 
			 preparedStmt.setString(3,cardName);
			 preparedStmt.setString(4,cardType);
			 preparedStmt.setString(5, expMonth); 
			 preparedStmt.setString(6, expYear);
			 preparedStmt.setString(7, CVV);
			 preparedStmt.setInt(8, Integer.parseInt(PID));
				 
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
		
		public String deletepayment(String PID) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from payment where PID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(PID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the payment."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
	 } 
}
