package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve user answers from the request
        String answer1 = request.getParameter("q1");
        String answer2 = request.getParameter("q2");

        // TODO: Validate and sanitize data as needed

        // Save user answers to the database
        saveToDatabase(answer1, answer2);

        // Send a response back to the client
        PrintWriter out = response.getWriter();
        out.println("Quiz submitted successfully!");
    }

    private void saveToDatabase(String answer1, String answer2) {
        // TODO: Implement database insertion logic
        // Use JDBC or an ORM framework like Hibernate for database interactions
        // Make sure to handle database connections, prepared statements, and error handling
    }
}

