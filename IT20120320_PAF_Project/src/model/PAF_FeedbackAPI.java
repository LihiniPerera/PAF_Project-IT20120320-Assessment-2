package model;

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
 * Servlet implementation class PAF_FeedbackAPI
 */
@WebServlet("/PAF_FeedbackAPI")
public class PAF_FeedbackAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PAF_Feedback fdObj = new PAF_Feedback();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PAF_FeedbackAPI() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PAF_Feedback fdObj = new PAF_Feedback();
		
		String output = "";
		output = fdObj.readFeedbacks();
		
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = fdObj.insertFeedback(
				request.getParameter("F_Name"),
				request.getParameter("F_ContactNo"),
				request.getParameter("F_Email"),
				request.getParameter("F_Message"));

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = "";
		if (paras.get("hididSave") != null) {
		output = fdObj.updateFeedback(
				paras.get("hididSave").toString(),
				paras.get("F_Name").toString(),
				paras.get("F_ContactNo").toString(),
				paras.get("F_Email").toString(), 
				paras.get("F_Message").toString()); 

		}
		else {
			output = fdObj.updateFeedback(
					request.getParameter("hididSave"),
					request.getParameter("F_Name"),
					request.getParameter("F_ContactNo"),
					request.getParameter("F_Email"),
					request.getParameter("F_Message")); 

		}
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = "";
		
		if (paras.get("F_ID") != null) {
			output = fdObj.deleteFeedback(paras.get("F_ID").toString());
		}
		else {
			
			output = fdObj.deleteFeedback(request.getParameter("F_ID"));
		}
		System.out.println("ID: " + output);
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
