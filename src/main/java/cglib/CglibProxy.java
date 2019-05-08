package cglib;

import service.PersonService;
import service.PersonServiceImpl;

public class CglibProxy {
    public static void main(String[] args) {
        CglibIntercept cglibIntercept = new CglibIntercept();
        PersonService personService = (PersonServiceImpl) cglibIntercept.getProxy(PersonServiceImpl.class);
        personService.working();
    }
}
