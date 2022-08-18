package com.Matrix.Controllers;

import java.io.IOException;
import java.io.PrintWriter;

/*		NOTES
 *	Remember to manually shutdown your testing server
 */

public class HttpController extends HttpServlet {
	@Override
	protected void GetMatrix(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			String URI = request.getRequestURI();
			System.out.println(URI);
			
			PrintWriter print = response.getWriter();
			print.print("<h1>I know you got the GetMatrix method!</h1>");
			response.setStatus(218);
			response.setHeader("content-type", "text/html");
	}
	
	protected void PostMatrix(HttpServletRequest request, HttpServletResponse response) 
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
