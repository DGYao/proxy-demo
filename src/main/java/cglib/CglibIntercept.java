package cglib;

import annotation.MyTransaction;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibIntercept implements MethodInterceptor {
    Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib sleeping");
        Object object = null;
        if(!method.isAnnotationPresent(MyTransaction.class)){
            object = methodProxy.invokeSuper(o, objects);
        }else {
            System.out.println("开启事务");
            try {
                object = methodProxy.invokeSuper(o,objects);
                System.out.println("提交事务");
            }catch (Exception e){
                System.out.println("事务回滚");
            }
        }
        System.out.println("cglib sleeping again");
        return object;
    }
}
