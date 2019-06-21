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
		Course tr1 = new Course("1", "c数据结构");
		listToSelect.add(tr1);
		Course temp = (Course)listToSelect.get(0);//把Object强转为 Course Object
		System.out.println("课程ID：" + temp.getId() + "课程名称：" + temp.getName());
		Course[] tr2 = {new Course("3", "a大学英语"), new Course("2", "b高等数学")};
		listToSelect.addAll(Arrays.asList(tr2));//把数组变成集合
		Course temp2 = (Course)listToSelect.get(1);
		System.out.println("课程ID：" + temp2.getId() + "课程名称：" + temp2.getName());
	}
	public void getTest() {
		int i;
		System.out.println("list for编历");
		for(i=0; i<listToSelect.size(); i++) {
			Course temp3 = (Course)listToSelect.get(i);
			System.out.println("课程ID：" + temp3.getId() + "课程名称：" + temp3.getName());
		}
	}
	public void testIterator() {
		System.out.println("list 迭代器编历");
		Iterator it = listToSelect.iterator();
		while(it.hasNext()) {
			Course temp = (Course)it.next();
			System.out.println("课程ID：" + temp.getId() + "课程名称：" + temp.getName());
		}
	}
	public void testForEach() {
		System.out.println("list for each编历");
		for(Object obj : listToSelect) {
			Course temp = (Course) obj;
			System.out.println("课程ID：" + temp.getId() + "课程名称：" + temp.getName());
		}
	}
	public void testModify() {
		System.out.println("list 修改毛概课程");
		Course tr = new Course("4", "毛概");
		listToSelect.set(1, tr);
	}
	public void testRemove() {
		System.out.println("list 删除毛概课程");
		Course tr = (Course)listToSelect.get(1);
		listToSelect.remove(tr);
	}
	public void testContains() {
		Course tr1 = (Course)listToSelect.get(0);
		System.out.println("取得课程tr1:" + tr1.getName());
		System.out.println("课程tr1：" + tr1.getName() + " " + "为 " + listToSelect.contains(tr1));
		Course tr2 = new Course();
		System.out.println("取得课程tr2:" + tr1.getName());
		System.out.println("课程tr2：" + tr1.getName() + " " + "为 " + listToSelect.contains(new Course(tr1.getId(), tr1.getName())));
		Course tr3 = new Course();
		tr3.setName("数据结构");
		System.out.println("取得课程tr3:" + "数据结构");
		System.out.println("课程tr3：" + tr1.getName() + " " + "为 " + listToSelect.contains(tr3));
		System.out.println("课程tr3：" + tr1.getName() + " " + "为 " + listToSelect.contains(tr3) + " " +"索引位置为：" + listToSelect.indexOf(tr3));
	}
	public void testSort() {
		System.out.println("排序后--------：");
		Collections.sort(listToSelect, new CourseComparator());
	}
}
