package guru.springframework.services.MapServices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
//import guru.springframework.services.MapServices.AbstractMapService;
import guru.springframework.services.UserService;

import java.util.List;


public class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
