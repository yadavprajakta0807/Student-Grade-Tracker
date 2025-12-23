package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GradeTracker extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();
    JLabel l1, l2, l3, l4;
    JTextField nameField, rollField, marksField;
    JComboBox<String> subjectBox;
    JButton addBtn, reportBtn, clearBtn, chartBtn;
    JTextArea reportArea;
    JPanel JP;

    public GradeTracker() {

        setTitle("Student Grade Tracker");
        setSize(700, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
        JP = new JPanel(new GridLayout(6, 2, 10, 10));

      
        l1 = new JLabel("Student name:");
        l2 = new JLabel("Roll no:");
        l3 = new JLabel("Subject:");
        l4 = new JLabel("Marks (0-100):");

        
        nameField = new JTextField();
        rollField = new JTextField();
        marksField = new JTextField();

        
        subjectBox = new JComboBox<>(new String[]{"Maths", "Science", "English", "Computer", "History"});

       
        addBtn = new JButton("Add Marks");
        reportBtn = new JButton("Show Report");
        clearBtn = new JButton("Reset");
        chartBtn = new JButton("Show Chart");

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        
        JP.add(l1); JP.add(nameField);
        JP.add(l2); JP.add(rollField);
        JP.add(l3); JP.add(subjectBox);
        JP.add(l4); JP.add(marksField);
        JP.add(addBtn); JP.add(reportBtn);
        JP.add(clearBtn); JP.add(chartBtn);  // <-- Reset + Chart buttons

        add(JP, BorderLayout.NORTH);

        
        addBtn.addActionListener(evt -> addMarks());

      
        reportBtn.addActionListener(e -> showReport());

      
        clearBtn.addActionListener(e -> {
            nameField.setText("");
            rollField.setText("");
            marksField.setText("");
            reportArea.setText("");
        });

        
        chartBtn.addActionListener(e -> new GradeChart(students));

        setVisible(true);
    }

    private void addMarks() {
        String name = nameField.getText();
        String roll = rollField.getText();
        String subject = (String) subjectBox.getSelectedItem();
        int marks;

        try {
            marks = Integer.parseInt(marksField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid marks!");
            return;
        }

        Student s = findStudent(roll);
        if (s == null) {
            s = new Student(name, roll);
            students.add(s);
        }

        s.addMarks(subject, marks);
        JOptionPane.showMessageDialog(this, "Marks Added Successfully!");

        marksField.setText("");
    }

    private Student findStudent(String roll) {
        for (Student s : students) {
            if (s.getRollno().equals(roll))
                return s;
        }
        return null;
    }

    public void showReport() {
        String roll = rollField.getText();
        Student s = findStudent(roll);

        if (s == null) {
            reportArea.setText("Student not found!!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- Student Report ---\n");
        sb.append("Name: ").append(s.getName()).append("\n");
        sb.append("Roll No: ").append(s.getRollno()).append("\n");
        sb.append("Marks:\n");

        for (Map.Entry<String, Integer> entry : s.getMarks().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        sb.append("Total Marks: ").append(s.getTotalMarks()).append("\n");
        sb.append("GPA: ").append(String.format("%.2f", s.calculateGPA())).append("\n");

        reportArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new GradeTracker();
    }
}
