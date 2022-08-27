package com.MatrixWeb.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*		NOTES
 *	Remember to manually shutdown your testing server
 */

public class HttpController extends HttpServlet {

	protected void GetRequest(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			String URI = request.getRequestURI();
			System.out.println(URI);
			
			PrintWriter print = response.getWriter();
			print.print("<h1>I know you got the GetMatrix method!</h1>");
			response.setStatus(218);
			response.setHeader("content-type", "text/html");
	}
	
	protected void PostRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
//		Need post request to trigger.
				String URI = request.getRequestURI();
				System.out.println(URI);
				
				PrintWriter print = response.getWriter();
				print.print("<h1>I know you got the PostMatrix method!</h1>");
				response.setStatus(218);
				response.setHeader("content-type", "text/html");
		}
}
