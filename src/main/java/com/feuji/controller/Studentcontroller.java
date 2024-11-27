package com.feuji.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feuji.bean.Student;
import com.feuji.studentdao.StudentDAO;
import com.google.gson.JsonArray;
@WebServlet("/abc")
public class Studentcontroller extends HttpServlet{
	 
		private static final long serialVersionUID = 1L;
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			StudentDAO studentjdbc = new StudentDAO();
			String action = req.getParameter("operation");
			System.out.println(action);
			Student student = new Student();
			if ("insert".equalsIgnoreCase(action) || "update".equalsIgnoreCase(action)) {
				System.out.println("inserted");
				int id = Integer.parseInt(req.getParameter("id"));
				String name = req.getParameter("name");
				Long phno = Long.parseLong(req.getParameter("phno"));
				String city = req.getParameter("city");
				student.setId((id));
				student.setName(name);
				student.setPhno((phno));
				student.setCity(city);
				switch (action) {
				case "insert":
					System.out.println("inserted1");
					int res1 = studentjdbc.insertingRecord(student);
					if (res1 > 0) {
						PrintWriter printWriter = resp.getWriter();
						System.out.println("inserted2");

						printWriter.println("saved success");
					}

					break;
				case "update":
					int res2 = studentjdbc.updatingRecord(student);
					if (res2 > 0) {
						PrintWriter printWriter = resp.getWriter();
						printWriter.println("update success");
					}

					break;
				default:
					break;
				}
			} else if ("delete".equalsIgnoreCase(action) || "search".equalsIgnoreCase(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
				student.setId((id));
				switch (action) {
				case "delete":

					int res3 = studentjdbc.deletingRecord(student);
					if (res3 > 0) {
						PrintWriter printWriter = resp.getWriter();
						printWriter.println("deleted success");
					}
					break;
				case "search":
					JsonArray jsonarray = studentjdbc.searchRecord(student);
					resp.setContentType("application/json");
					// Write the JSON array to the response output stream
					PrintWriter out = resp.getWriter();
					out.print(jsonarray.toString());
					out.flush();

					break;
				}
			}
			if (!"search".equalsIgnoreCase(action)) {
				JsonArray jsonarray = studentjdbc.display();
				resp.setContentType("application/json");
				System.out.println("yes");
				PrintWriter out = resp.getWriter();
				out.print(jsonarray.toString());
				out.flush();
			}

		}
}
