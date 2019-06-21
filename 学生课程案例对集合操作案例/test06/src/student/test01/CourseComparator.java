package student.test01;

import java.util.Comparator;

public class CourseComparator implements Comparator<Course> {

	public int compare(Course obj1, Course obj2) {
		return obj1.getName().compareTo(obj2.getName());
	}

}
