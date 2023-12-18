package com.project.servlets;

import java.io.IOException; 

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.project.dao.DatabaseConnection;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        // Valider les données (ajoutez vos propres validations si nécessaire)

        // Enregistrer le nom d'utilisateur dans la base de données
        saveUsernameToDatabase(username);

        // Afficher une réponse à l'utilisateur
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Le nom d'utilisateur a été enregistré avec succès dans la base de données.</h2>");
            out.println("</body></html>");
        }
    }

    private void saveUsernameToDatabase(String username) {
        try (Connection connection = DatabaseConnection.connection()) {
            // Utiliser une requête préparée pour éviter les injections SQL
            String sql = "INSERT INTO users (username) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de base de données
        }
    }
}
