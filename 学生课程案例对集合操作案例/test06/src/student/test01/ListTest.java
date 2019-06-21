package student.test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListTest {
	private List listToSelect;
	public List getListToSelect() {
		return listToSelect;
	}
	public void SetListToSelect(List listToSelect) {
		this.listToSelect = listToSelect;
	}
	public ListTest() {
		this.listToSelect = new ArrayList();
	}
	public void testAdd() {
		Course tr1 = new Course("1", "c���ݽṹ");
		listToSelect.add(tr1);
		Course temp = (Course)listToSelect.get(0);//��ObjectǿתΪ Course Object
		System.out.println("�γ�ID��" + temp.getId() + "�γ����ƣ�" + temp.getName());
		Course[] tr2 = {new Course("3", "a��ѧӢ��"), new Course("2", "b�ߵ���ѧ")};
		listToSelect.addAll(Arrays.asList(tr2));//�������ɼ���
		Course temp2 = (Course)listToSelect.get(1);
		System.out.println("�γ�ID��" + temp2.getId() + "�γ����ƣ�" + temp2.getName());
	}
	public void getTest() {
		int i;
		System.out.println("list for����");
		for(i=0; i<listToSelect.size(); i++) {
			Course temp3 = (Course)listToSelect.get(i);
			System.out.println("�γ�ID��" + temp3.getId() + "�γ����ƣ�" + temp3.getName());
		}
	}
	public void testIterator() {
		System.out.println("list ����������");
		Iterator it = listToSelect.iterator();
		while(it.hasNext()) {
			Course temp = (Course)it.next();
			System.out.println("�γ�ID��" + temp.getId() + "�γ����ƣ�" + temp.getName());
		}
	}
	public void testForEach() {
		System.out.println("list for each����");
		for(Object obj : listToSelect) {
			Course temp = (Course) obj;
			System.out.println("�γ�ID��" + temp.getId() + "�γ����ƣ�" + temp.getName());
		}
	}
	public void testModify() {
		System.out.println("list �޸�ë�ſγ�");
		Course tr = new Course("4", "ë��");
		listToSelect.set(1, tr);
	}
	public void testRemove() {
		System.out.println("list ɾ��ë�ſγ�");
		Course tr = (Course)listToSelect.get(1);
		listToSelect.remove(tr);
	}
	public void testContains() {
		Course tr1 = (Course)listToSelect.get(0);
		System.out.println("ȡ�ÿγ�tr1:" + tr1.getName());
		System.out.println("�γ�tr1��" + tr1.getName() + " " + "Ϊ " + listToSelect.contains(tr1));
		Course tr2 = new Course();
		System.out.println("ȡ�ÿγ�tr2:" + tr1.getName());
		System.out.println("�γ�tr2��" + tr1.getName() + " " + "Ϊ " + listToSelect.contains(new Course(tr1.getId(), tr1.getName())));
		Course tr3 = new Course();
		tr3.setName("���ݽṹ");
		System.out.println("ȡ�ÿγ�tr3:" + "���ݽṹ");
		System.out.println("�γ�tr3��" + tr1.getName() + " " + "Ϊ " + listToSelect.contains(tr3));
		System.out.println("�γ�tr3��" + tr1.getName() + " " + "Ϊ " + listToSelect.contains(tr3) + " " +"����λ��Ϊ��" + listToSelect.indexOf(tr3));
	}
	public void testSort() {
		System.out.println("�����--------��");
		Collections.sort(listToSelect, new CourseComparator());
	}
}
