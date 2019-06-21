package com.test04;

public class Test04Main04 {
    public static void main(String[] args) throws Exception{
        Test04Data04 test04Data04 = new Test04Data04("zhangsan",20);
        System.out.println("---" + test04Data04);
        test04Data04.setId(new Long(1));
        Test04Class04 test04Class04 = new Test04Class04();
        Test04Data04 obj = (Test04Data04)test04Class04.coppyObject(test04Data04);
        System.out.println(obj.getId());
        System.out.println(obj.getName());
        System.out.println(obj.getAge());
    }
}
