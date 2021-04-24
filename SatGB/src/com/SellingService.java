package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Selling;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Selling")

public class SellingService {
	
	Selling itemObj = new Selling(); 
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
    public String insertselling(
     @FormParam("Itemname") String Itemname, 
     @FormParam("Itemcategory") String Itemcategory, 
     @FormParam("Price") String Price, 
     @FormParam("Discription") String Discription)
   

    { 
     String output = itemObj.insertItem( Itemname,  Itemcategory, Price, Discription); 
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
     String ItemID = itemObject.get("ItemID").getAsString(); 
     String Itemname = itemObject.get("Itemname").getAsString(); 
     String Itemcatogery = itemObject.get("Itemcategory").getAsString(); 
     String Price = itemObject.get("Price").getAsString(); 
     String Discription = itemObject.get("Discription").getAsString();
    
 

     String output = itemObj.updateselling(ItemID,Itemname, Itemcatogery,  Price, Discription); 
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
     String ItemID = doc.select("ItemID").text(); 
     String output = itemObj.deleteItem(ItemID); 
    return output; 
    }
    
}
 
