package com.test01;

public class Decorater implements Component {
    private Component component;
    public Decorater(Component component){
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
