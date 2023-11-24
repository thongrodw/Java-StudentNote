package org.siit.UI;

import org.siit.Note;
import org.siit.Professor;
import org.siit.Student;
import org.siit.UI.Components.LogoutComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class NotePage extends JFrame {

    private Integer studentId;
    private Integer professorId;
    private List<Note> studentNotes;
    private String studentName;
    private JTextArea notesTextArea;

    public NotePage(Integer studentId, Integer professorId) {
        this.studentId = studentId;
        this.professorId = professorId;
        this.studentNotes =  Professor.viewStudentNotes(studentId, professorId);
        this.studentName = Student.getStudentById(studentId).getName();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Note Page - " + studentId);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        displayNotes(panel);

        JButton addNoteButton = new JButton("Add New Note");
        JButton backButton = new JButton("Back");

        JPanel buttonPanelC = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelC.add(addNoteButton);

        JPanel buttonPanelL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanelL.add(backButton);

        panel.add(buttonPanelL, BorderLayout.NORTH);
        panel.add(buttonPanelC, BorderLayout.SOUTH);

        addNoteButton.addActionListener(this::onAddNote);
        backButton.addActionListener(e -> dispose());
    }

    private void displayNotes(Container container) {
        notesTextArea = new JTextArea(10, 30);
        notesTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(notesTextArea);
        container.add(scrollPane, BorderLayout.CENTER);

        updateNotesText(container);
    }

    private void updateNotesText(Container container) {
        JPanel notesPanel = new JPanel(new GridLayout(studentNotes.size(), 1));
        for (Note note : studentNotes) {
            JTextArea noteTextArea = new JTextArea(note.getNote());
            noteTextArea.setEditable(false);
            noteTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border for separation
            notesPanel.add(noteTextArea);
        }
        JScrollPane scrollPane = new JScrollPane(notesPanel);
        container.add(scrollPane, BorderLayout.CENTER);
    }

    private void onAddNote(ActionEvent e) {
        String newNote = JOptionPane.showInputDialog(this, "Enter a new note:");
        if (newNote != null && !newNote.trim().isEmpty()) {
            Professor.addNote(studentId, professorId, newNote, Student.getStudentById(studentId).getName());
            new NotePage(studentId, professorId).setVisible(true);
        }
    }
}
