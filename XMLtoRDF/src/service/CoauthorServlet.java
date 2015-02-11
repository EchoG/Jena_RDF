package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DB;
import model.DOMParser;


public class CoauthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoauthorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String author = request.getParameter("author");
		//System.out.println("author"+author);
		
		//start parse XML and init database
		try {
			
			ArrayList<String> coauthors = DB.queryCoauthors(author);
			ArrayList<String> publications = DB.queryPublication(author);
			request.setAttribute("coauthors", coauthors+"<br/>");
			request.setAttribute("publications", publications);
			RequestDispatcher rd = request.getRequestDispatcher("/designlab3.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
