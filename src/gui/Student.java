package gui;
import java.util.*;
public class Student {
private String name ;
private String rollno;
private HashMap<String ,Integer>marksMap = new HashMap();

public Student(String name ,String rollno)
{
	this.name=name;
	this.rollno=rollno;
	
}
public void addMarks(String subject ,int marks)
{
	marksMap.put(subject ,marks);
}

public String getName() {
	return name;
}

public String getRollno() {
	return rollno;
}


public HashMap<String, Integer> getMarks() {
return marksMap;
}
public double calculateGPA(){
	if(marksMap.isEmpty())
		return 0;
	  
	int total = 0;
	for(int m : marksMap.values()) total += m;
	double percentage =(double)total/(marksMap.size()*100)*100;
	return percentage /10;
	
}
public int getTotalMarks()
{
	int total = 0;
	for(int m : marksMap.values()) total += m;
	return total;
}

}
