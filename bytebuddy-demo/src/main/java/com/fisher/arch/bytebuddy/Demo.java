package com.fisher.arch.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.returns;

public class Demo {

    public void test() {
        DynamicType.Unloaded<App> make = new ByteBuddy().subclass(App.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("hello world bytebuddy !"))
                .make();

        Class<? extends App> dynamicType = make.load(getClass().getClassLoader()).getLoaded();
        try {
            System.out.println(dynamicType.newInstance().toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void invoke() throws IllegalAccessException, InstantiationException {
        String sayHelloFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(ElementMatchers.named("sayHelloFoo")
                        .and(isDeclaredBy(Foo.class)
                                .and(returns(String.class))))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .sayHelloFoo();

        System.out.println(sayHelloFoo);
    }


    public void invoke1() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = new ByteBuddy()
                .subclass(Object.class)
                .name("MyClassName")
                .defineMethod("custom", String.class, Modifier.PUBLIC)
                .intercept(MethodDelegation.to(Bar.class))
                .defineField("x", String.class, Modifier.PUBLIC)
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        Method custom = aClass.getDeclaredMethod("custom", null);
        System.out.println(custom.invoke(aClass.newInstance()));
        System.out.println(Bar.sayHelloBar());
        System.out.println(aClass.getDeclaredField("x"));
    }



    public void invoke2() {

    }
}
