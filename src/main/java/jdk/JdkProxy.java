package jdk;

import service.PersonService;
import service.PersonServiceImpl;

import java.lang.reflect.Proxy;

public class JdkProxy {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();
        MyInvocation myInvocation = new MyInvocation(personService);
        PersonService o = (PersonService) Proxy.newProxyInstance(personService.getClass().getClassLoader(), personService.getClass().getInterfaces(), myInvocation);
        o.working();

        //生成代理类
        ProxyUtils.generateClassFile(personService.getClass(),"PersonServiceProxy");
    }
}
