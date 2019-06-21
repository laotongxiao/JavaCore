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
		Course tr = new Course("1", "���ݽṹ");
		listToSelect.add(tr);
	}
	public void testForEach() {
		for(Course temp : listToSelect) {
			System.out.println("�γ�ID��" + temp.getId() + "�γ����ƣ�" + temp.getName());
		}
	}
	public void testBasicType() {
		List<Integer> list = new ArrayList<Integer>();//��������int�����࣬Ҫ����Ӧ������
		list.add(1);
		for(Integer temp: list) {
			System.out.println(temp);
		}
	}
	public void testChild() {
		CourseChild tr = new CourseChild();
		tr.setId("2");
		tr.setName("��ѧӢ��");
		listToSelect.add(tr);
	}
}
