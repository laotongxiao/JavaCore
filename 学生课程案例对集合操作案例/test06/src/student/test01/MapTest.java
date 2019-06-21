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
//			System.out.println("请输入学生ID：");
//			String id = output.next();
//			Student st = students.get(id);//说明Map每个<key,value>，只有知道key对象就能找到value对象好处是准确定位每一个，entry做不到
//			if(st == null) {
//				System.out.println("请输入学生姓名：");
//				String name = output.next();
//				students.put(id, new Student(id, name));
//				System.out.println("成功添加学生：" + students.get(id).getName());
//				i++;
//			}else {
//				System.out.println("该学生ID已被占用！");
//				continue;
//			}
//		}
//	}
	public void testPut() {
		Student stu1 = new Student("1","小明");
		Student stu2 = new Student("2","小红");
		Student stu3 = new Student("3","小兰");
		students.put("a", stu1);
		students.put("b", stu2);
		students.put("c", stu3);
	}
	public void testKeySet() {
		Set<String> keyset = students.keySet();//map 的keySet 是不可以有重复所以只有是存set中 不能为list
		for(String key : keyset) {
			Student temp = students.get(key);
			System.out.println(temp.getName());
		}
	}
	public void testRemove() {
		Scanner output = new Scanner(System.in);
		while(true) {
			System.out.println("请输入要删除学生ID：");
			String id = output.next();
			Student temp = students.get(id);
			if(temp == null) {
				System.out.println("该ID学生不存在！请重新输入！");
				continue;
			}
			students.remove(id);
			System.out.println("成功删除学生：" + temp.getName());
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
		System.out.println("请输入要修改的学生ID：");
		while(true) {
			String id = output.next();
			Student temp = students.get(id);
			if(temp == null) {
				System.out.println("该ID不存在！请重新输入！");
				continue;
			}
			System.out.println("你要修改的学生ID：" + id + " " + "原姓名：" + temp.getName());
			System.out.println("请输入新的姓名");
			String name = output.next();
			Student newstudent = new Student(id,name);
			students.put(id, newstudent);
			System.out.println("修改成功！");
			break;
		}
	}
	public void testContainsKeyOrValue() {
		Student stu1 = new Student();
		stu1.setId("a");
		stu1.setName("小明");
		Student temp = students.get("a");
		System.out.println("所要查找学生 map key 为：" + "a" + " " + students.containsKey(stu1.getId()) + " " + "姓名为：" +temp.getName());
		System.out.println("姓名为：" + stu1.getName() + " " + students.containsValue(stu1));
	}
}
