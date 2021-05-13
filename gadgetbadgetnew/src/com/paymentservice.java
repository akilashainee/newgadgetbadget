package com;

import com.payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/payment")

public class paymentservice {
	
	 payment payObj = new payment(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		 return payObj.readItems();
	 } 
	
	

	 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertpayment(
	 @FormParam("amount") String amount, 
	 @FormParam("cardName") String cardName, 
	 @FormParam("cardType") String cardType, 
	 @FormParam("cardNo") String cardNo,
	 @FormParam("expMonth") String expMonth, 
	@FormParam("expYear") String expYear, 
	@FormParam("CVV") String CVV)

	{ 
	 String output = payObj.insertItem( amount,  cardName,  cardType, cardNo,expMonth, expYear, CVV); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String PID = paymentObject.get("PID").getAsString(); 
	 String amount = paymentObject.get("amount").getAsString(); 
	 String cardNo = paymentObject.get("cardNo").getAsString(); 
	 String cardName = paymentObject.get("cardName").getAsString(); 
	 String cardType = paymentObject.get("cardType").getAsString();
	 String expMonth = paymentObject.get("expMonth").getAsString();
	 String expYear = paymentObject.get("expYear").getAsString();
	 String CVV = paymentObject.get("CVV").getAsString();

	 String output = payObj.updateItem(PID,amount, cardName,  cardType, cardNo,expMonth, expYear, CVV); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String paymentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String PID = doc.select("PID").text(); 
	 String output = payObj.deleteItem(PID); 
	return output; 
	}
	
}
