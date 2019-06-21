package student.test01;

import java.util.HashSet;
import java.util.Set;

public class ListSet {
	private Set<Course> listToSelect;
	public Set<Course> getListToSelect(){
		return listToSelect;
	}
	public void setListToSelect(Set<Course> listToSelect) {
		this.listToSelect = listToSelect;
	}
	public ListSet() {
		this.listToSelect = new HashSet<Course>();
	}
	public void testAdd() {
		Course tr1 = new Course("1", "数据结构");
		Course tr2 = new Course("2", "大学英语");
		Course tr3 = new Course("3", "高等数学");
		listToSelect.add(tr1);
		listToSelect.add(tr2);
		listToSelect.add(tr3);
	}
	//set是无序的,只能用迭代器编历和foreach编历
	public void testForEachSet() {
		for(Course temp: listToSelect) {
			System.out.println("课程ID：" + temp.getId() + "课程名称：" + temp.getName());
		}
	}
	public void testSetContains() {
		Course tr1 = new Course();
		tr1.setName("大学英语");
		System.out.println("指定课程tr1:" + tr1.getName());
		System.out.println("课程tr1：" + tr1.getName() + " " + "为 " + listToSelect.contains(tr1));
	}
}
