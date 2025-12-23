package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.*;
import javax.swing.JOptionPane;



public class GradeChart {


public GradeChart(ArrayList<Student> students) {


if (students.isEmpty()) {
JOptionPane.showMessageDialog(null, "No student data available!");
return;
}


Student s = students.get(0); // show first student's chart


DefaultCategoryDataset dataset = new DefaultCategoryDataset();


for (Map.Entry<String, Integer> entry : s.getMarks().entrySet()) {
dataset.addValue(entry.getValue(), "Marks", entry.getKey());
}


JFreeChart chart = ChartFactory.createBarChart(
"Performance Chart - " + s.getName(),
"Subject",
"Marks",
dataset
);


ChartFrame frame = new ChartFrame("Student Chart", chart);
frame.setSize(600, 400);
frame.setVisible(true);
}
}