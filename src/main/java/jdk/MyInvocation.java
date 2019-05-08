package jdk;

import annotation.MyTransaction;
import service.PersonService;
import service.PersonServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocation implements InvocationHandler {
    private Object target;

    public MyInvocation(Object target) {
        this.target = target;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method method1 = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        System.out.println("jdk sleeping");
        Object object = null;
        if (!method1.isAnnotationPresent(MyTransaction.class)){
            object = method.invoke(target, args);
        }else {
            System.out.println("开启事务");
            try {
                object = method.invoke(target, args);
                System.out.println("提交事务");
            }catch (Exception e){
                System.out.println("事务回滚");
            }
        }
        System.out.println("jdk sleeping again");
        return object;
    }
}
