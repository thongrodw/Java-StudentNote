package org.siit;

import org.siit.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Note> viewStudentNotes(Integer studentId, Integer professorID) {
        List<Note> noteList = new ArrayList<>();
        try {
            Connection connection = SQLUtils.connect();
            String query = "SELECT * FROM Notes WHERE student_id = ? AND professor_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, professorID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Note note = new Note();
                        note.setStudentId(resultSet.getInt("student_id"));
                        note.setProfessorId(resultSet.getInt("professor_id"));
                        note.setNote(resultSet.getString("note"));
                        note.setName(resultSet.getString("student_name"));
                        noteList.add(note);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    public static void addNote(Integer studentId, Integer professorID, String note, String studentName) {
        try {
            Connection connection = SQLUtils.connect();
            String query = "INSERT INTO Notes (student_id, professor_id, note, student_name) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, professorID);
                preparedStatement.setString(3, note);
                preparedStatement.setString(4, studentName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}