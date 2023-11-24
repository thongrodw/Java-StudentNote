package org.siit;

import org.siit.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private Integer studentId;
    private String name;

    public static List<Student> getAllStudents(Integer userID) {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = SQLUtils.connect();
            String query = "SELECT * FROM Students WHERE professor_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentId(resultSet.getInt("student_id"));
                        student.setName(resultSet.getString("name"));
                        students.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static Student getStudentById(Integer studentId) {
        try (Connection connection = SQLUtils.connect()) {
            String query = "SELECT * FROM Students WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentId(resultSet.getInt("student_id"));
                        student.setName(resultSet.getString("name"));
                        return student;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
