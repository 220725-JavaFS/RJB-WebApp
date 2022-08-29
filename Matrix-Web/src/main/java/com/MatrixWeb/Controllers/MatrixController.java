package com.MatrixWeb.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.Matrix.Matrix;
import com.MatrixWeb.Services.MatrixWebService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*		NOTES
 * 	It seems from testing i have to override the 
 * 	doGet, doUpdate, doPost, doDelete methods
 */
@SuppressWarnings("serial")
public class MatrixController extends HttpServlet {
//	MATRIX ORM
	private MatrixWebService matrix = new MatrixWebService();
//	JSON MAPPER
	private ObjectMapper Mapper = new ObjectMapper();
	
	@Override // THERE ARE NO MATRICES IN THE DATABASE SO POST SOME FIRST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		StringBuilder sb = new StringBuilder();
//		From Java.io
		BufferedReader BuffRead = request.getReader();
//		the using this to read the line of incoming request in the GUI/browser
		String Line = BuffRead.readLine();
//		the response may be longer than 1 length of string so ill use a while
		while (Line != null) {
//			Appending the Line to the string builder
			sb.append(Line);
			Line = BuffRead.readLine();
		}
//		creating a new string object with my StrinBuilder variable
		String json = new String(sb);
		System.out.println(json);
//		My instance of Matrix, if i had more time 
//		i would create multi-level inheritance with my Matrix Class
		Matrix MatrixUnraveled = Mapper.readValue(json, Matrix.class);
//		Activating the Matrix (Unknown) ORM (dependency) Service (DAO Pattern)
		matrix.StoreMatrix(MatrixUnraveled);
//		"Created" a new matrix
		response.setStatus(201);
		response.setHeader("content-type", "text/html");
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
//		From Java.io
		BufferedReader BuffRead = request.getReader();
//		the using this to read the line of incoming request in the GUI/browser
		String Line = BuffRead.readLine();

		while (Line != null) {
//			Appending the Line to the string builder
			sb.append(Line);
			Line = BuffRead.readLine();
		}
//		creating a new string object with my StrinBuilder variable
		String json = new String(sb);
//		i would create multi-level inheritance with my Matrix Class
		Matrix MatrixUnraveled = Mapper.readValue(json, Matrix.class);
//		If the updated matrix is true
		if (matrix.UpdateMatrix(MatrixUnraveled)) {
//			"Ok" the matrix updated
//			i could send this to another server.
			response.setStatus(200);
			response.setHeader("content-type", "text/html");
		} else {
//			"Bad Request" of matrix not updating
			response.setStatus(400);

		}

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
/*
 * 		I figured i wouldn't need to make a if 
		else statement to check the length of the split URL
		since inside of my ORM List could have one or more 
		matrices. (I dont have a custom script to limit the amount of
		data inside of the AWS RDS (relational database)
 */
//		Looking for the first line of the HTTP request
		String URI = request.getRequestURI();
		
		System.out.println(URI);
//		Could be sent to a analytic server.
		List<Matrix> MatrixTypes = matrix.GetMatrices(Matrix.class);
		// showing one (if there is one) to all (More than one)
//		Method used to serialize any java value as a String.
		String json = Mapper.writeValueAsString(MatrixTypes);
//		Prints formatted representations of objects to a text-output stream.
		PrintWriter printWriter = response.getWriter();
//		Printing a string
		printWriter.print(json);
//		The the ok status code
		response.setStatus(200);
//		Setting the content type to application/json
		response.setContentType("application/json");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
//		From Java.io
		BufferedReader BuffRead = request.getReader();
//		the using this to read the line of incoming request in the GUI/browser
		String Line = BuffRead.readLine();


		try {
//			making id equal to he value of the Line made with readLine().
			id = Integer.valueOf(Line);
		} catch (NumberFormatException e) {
			System.out.println(e);
//			"bad Request" setting the status
			response.setStatus(400);
			return;
		}

		List<Matrix> MatrixTypes = matrix.GetMatrices(Matrix.class);
//		For the non-functional requirements ill be using the lambdas 
		Matrix MatrixType = MatrixTypes.stream()
//				Returns a sequential Stream with this collection as its source
				.filter(pointer -> pointer.getId() == id)
//				Returns a stream consisting of the elements of this 
//				stream that match the given predicate.
				.collect(Collectors.toList()).get(0);
//				mutable method used to reduce elements of the list from the class 
//				Collectors class.

		if (matrix.DeleteMatrix(MatrixType)) {
//			"Ok" the matrix updated
//			i could send this to another server.
			response.setStatus(200);
			response.setHeader("content-type", "text/html");
		} else {
//			"Bad Request" of matrix not updating
			response.setStatus(400);

		}
	}
	
}
