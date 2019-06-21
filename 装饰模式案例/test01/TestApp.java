package com.test01;

public class TestApp {
    public static void main(String[] args){
        Component component = new ConreteDecorater2(new ConreteDecorater1(new ConreteCompnent()));
        component.doSomething();
        System.out.println("------------");
        Component component1 = new ConreteDecorater1(new ConreteCompnent());
        component1.doSomething();
        System.out.println("------------");
        Component component2 = new ConreteDecorater2(new ConreteCompnent());
        component2.doSomething();
        System.out.println("------------");
    }
}
