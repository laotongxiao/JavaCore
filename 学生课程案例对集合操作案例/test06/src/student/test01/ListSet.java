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
		Course tr1 = new Course("1", "���ݽṹ");
		Course tr2 = new Course("2", "��ѧӢ��");
		Course tr3 = new Course("3", "�ߵ���ѧ");
		listToSelect.add(tr1);
		listToSelect.add(tr2);
		listToSelect.add(tr3);
	}
	//set�������,ֻ���õ�����������foreach����
	public void testForEachSet() {
		for(Course temp: listToSelect) {
			System.out.println("�γ�ID��" + temp.getId() + "�γ����ƣ�" + temp.getName());
		}
	}
	public void testSetContains() {
		Course tr1 = new Course();
		tr1.setName("��ѧӢ��");
		System.out.println("ָ���γ�tr1:" + tr1.getName());
		System.out.println("�γ�tr1��" + tr1.getName() + " " + "Ϊ " + listToSelect.contains(tr1));
	}
}
