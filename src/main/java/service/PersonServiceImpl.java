package service;

import annotation.MyTransaction;

public class PersonServiceImpl implements PersonService{
    @MyTransaction
    public void working() {
        System.out.println("coding......");
    }
}
