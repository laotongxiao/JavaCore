package student.test01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import java.util.Map.Entry;

public class MapTest {
	private Map<String, Student> students;
	public Map<String, Student> getStudents(){
		return students;
	}
	public void setStudents(Map<String, Student> students) {
		this.students = students;
	}
	public MapTest() {
		this.students = new HashMap<String, Student>();
	}
//	public void testPut() {
//		Scanner output = new Scanner(System.in);
//		int i = 0;
//		while(i<3) {
//			System.out.println("������ѧ��ID��");
//			String id = output.next();
//			Student st = students.get(id);//˵��Mapÿ��<key,value>��ֻ��֪��key��������ҵ�value����ô���׼ȷ��λÿһ����entry������
//			if(st == null) {
//				System.out.println("������ѧ��������");
//				String name = output.next();
//				students.put(id, new Student(id, name));
//				System.out.println("�ɹ����ѧ����" + students.get(id).getName());
//				i++;
//			}else {
//				System.out.println("��ѧ��ID�ѱ�ռ�ã�");
//				continue;
//			}
//		}
//	}
	public void testPut() {
		Student stu1 = new Student("1","С��");
		Student stu2 = new Student("2","С��");
		Student stu3 = new Student("3","С��");
		students.put("a", stu1);
		students.put("b", stu2);
		students.put("c", stu3);
	}
	public void testKeySet() {
		Set<String> keyset = students.keySet();//map ��keySet �ǲ��������ظ�����ֻ���Ǵ�set�� ����Ϊlist
		for(String key : keyset) {
			Student temp = students.get(key);
			System.out.println(temp.getName());
		}
	}
	public void testRemove() {
		Scanner output = new Scanner(System.in);
		while(true) {
			System.out.println("������Ҫɾ��ѧ��ID��");
			String id = output.next();
			Student temp = students.get(id);
			if(temp == null) {
				System.out.println("��IDѧ�������ڣ����������룡");
				continue;
			}
			students.remove(id);
			System.out.println("�ɹ�ɾ��ѧ����" + temp.getName());
			break;
		}
	}
	public void testEntrySet() {
		Set<Entry<String, Student>> entryset = students.entrySet();
		for(Entry<String, Student> temp: entryset) {
			System.out.println(temp.getValue().getName());
		}
	}
	public void testModify() {
		Scanner output = new Scanner(System.in);
		System.out.println("������Ҫ�޸ĵ�ѧ��ID��");
		while(true) {
			String id = output.next();
			Student temp = students.get(id);
			if(temp == null) {
				System.out.println("��ID�����ڣ����������룡");
				continue;
			}
			System.out.println("��Ҫ�޸ĵ�ѧ��ID��" + id + " " + "ԭ������" + temp.getName());
			System.out.println("�������µ�����");
			String name = output.next();
			Student newstudent = new Student(id,name);
			students.put(id, newstudent);
			System.out.println("�޸ĳɹ���");
			break;
		}
	}
	public void testContainsKeyOrValue() {
		Student stu1 = new Student();
		stu1.setId("a");
		stu1.setName("С��");
		Student temp = students.get("a");
		System.out.println("��Ҫ����ѧ�� map key Ϊ��" + "a" + " " + students.containsKey(stu1.getId()) + " " + "����Ϊ��" +temp.getName());
		System.out.println("����Ϊ��" + stu1.getName() + " " + students.containsValue(stu1));
	}
}
