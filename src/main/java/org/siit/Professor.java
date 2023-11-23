package org.siit;

import org.siit.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Professor class
public class Professor extends User {

    public void addStudent(String name, Integer professorID) {
        try {
            Connection connection = SQLUtils.connect();
            String query = "INSERT INTO Students (professor_id, name) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, professorID);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String viewStudentNotes(String studentName) {
        try {
            Connection connection = SQLUtils.connect();
            String query = "SELECT note FROM Notes WHERE student_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(2, studentName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    StringBuilder notes = new StringBuilder();
                    while (resultSet.next()) {
                        notes.append(resultSet.getString("noteText")).append("\n");
                    }
                    return notes.toString();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addNote(String studentName, String note) {
        try {
            Connection connection = SQLUtils.connect();
            String query = "INSERT INTO Notes (professor_id, note, student_name) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, getUserID().toString());
                preparedStatement.setString(2, note);
                preparedStatement.setString(3, studentName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}