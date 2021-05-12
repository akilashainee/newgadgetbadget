package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/paymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	 payment payObj = new payment();
	   
	
    public paymentAPI() {
        super();
      
    }
    
    private static Map getParasMap(HttpServletRequest request)
    {
	    Map<String, String> map = new HashMap<String, String>();
	    try
	    {
		    Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		    String queryString = scanner.hasNext() ?
		    scanner.useDelimiter("\\A").next() : "";
		    scanner.close();
		    String[] params = queryString.split("&");
		    for (String param : params)
		    {
		    	
		    	String[] p = param.split("=");
		    	map.put(p[0], p[1]);
		    	}
	    }
	    catch (Exception e)
	    {
	    }
	    return map;
    }
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = payObj.insertpayment(request.getParameter("amount"), 
				request.getParameter("cardNO"), 
				request.getParameter("cardName"), 
				request.getParameter("cardType"),
				request.getParameter("expMonth"),
				request.getParameter("expYear"),
				request.getParameter("CVV"));
		
				response.getWriter().write(output); 
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = payObj.updatepayment(paras.get("hidPIDSave").toString(),
		paras.get("amount").toString(),
		paras.get("cardNo").toString(),
		paras.get("cardName").toString(),
		paras.get("cardType").toString(),
		paras.get("expMonth").toString(),
		paras.get("expYear").toString(),
		paras.get("CVV").toString());
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = payObj.deletepayment(paras.get("PID").toString());
		response.getWriter().write(output);
	}

}
