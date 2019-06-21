package com.test01;

public class ConreteDecorater1 extends Decorater {
    public ConreteDecorater1(Component component){
        super(component);
    }
    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }
    private void doAnotherThing(){
        System.out.println("功能B");
    }
}
