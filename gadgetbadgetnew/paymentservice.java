package com;

import model.payment; 
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
	
	 payment itemObj = new payment(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		 return itemObj.readItems();
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
	 String output = itemObj.insertpayment( amount,  cardName,  cardType, cardNo,expMonth, expYear, CVV); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String PID = itemObject.get("PID").getAsString(); 
	 String amount = itemObject.get("amount").getAsString(); 
	 String cardNo = itemObject.get("cardNo").getAsString(); 
	 String cardName = itemObject.get("cardName").getAsString(); 
	 String cardType = itemObject.get("cardType").getAsString();
	 String expMonth = itemObject.get("expMonth").getAsString();
	 String expYear = itemObject.get("expYear").getAsString();
	 String CVV = itemObject.get("CVV").getAsString();

	 String output = itemObj.updatepayment(PID,amount, cardName,  cardType, cardNo,expMonth, expYear, CVV); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String PID = doc.select("PID").text(); 
	 String output = itemObj.deletepayment(PID); 
	return output; 
	}
	
}
