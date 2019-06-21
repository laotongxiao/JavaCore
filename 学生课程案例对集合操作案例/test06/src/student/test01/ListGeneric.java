package student.test01;

import java.util.ArrayList;
import java.util.List;

public class ListGeneric {
	private List<Course> listToSelect;
	public ListGeneric() {
		this.listToSelect = new ArrayList<Course>();
	}
	public List getListToSelect() {
		return listToSelect;
	}
	public void setListToSelect(List<Course> listToSelect) {
		this.listToSelect = listToSelect;
	}
	public void testAdd() {
		Course tr = new Course("1", "数据结构");
		listToSelect.add(tr);
	}
	public void testForEach() {
		for(Course temp : listToSelect) {
			System.out.println("课程ID：" + temp.getId() + "课程名称：" + temp.getName());
		}
	}
	public void testBasicType() {
		List<Integer> list = new ArrayList<Integer>();//不可以是int基本类，要用相应对象型
		list.add(1);
		for(Integer temp: list) {
			System.out.println(temp);
		}
	}
	public void testChild() {
		CourseChild tr = new CourseChild();
		tr.setId("2");
		tr.setName("大学英语");
		listToSelect.add(tr);
	}
}
