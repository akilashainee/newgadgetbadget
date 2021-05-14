package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerAPI
 */
@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    Cus1 itemObj = new Cus1();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAPI() {
        super();
        // TODO Auto-generated constructor stub
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
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String output = itemObj.insertItem(
				request.getParameter("C_Name"),
				request.getParameter("C_Address"),
				request.getParameter("C_email"),
				request.getParameter("C_password"),
				request.getParameter("C_ConNum"));
		response.getWriter().write(output);
	
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
				
				String output = itemObj.updateItem(paras.get("hidIDSave").toString(),
				paras.get("C_Name").toString(),
				paras.get("C_Address").toString(),
				paras.get("C_email").toString(),
				paras.get("C_password").toString(),
				paras.get("C_ConNum").toString());
				
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = itemObj.deleteItem(paras.get("ID").toString());
		response.getWriter().write(output);
	}

}
