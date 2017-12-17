package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import pojo.User;

@Service
public class UserService extends BaseService
{
    @Autowired
    private UserDao userDao;
    
    // @Cacheable(value = "test", keyGenerator = "KeyGenerator")
    // public User findUser(Integer id, String userName)
    // {
    // User user = (User)this.getCache().get(String.valueOf(id));
    // if (null == user)
    // {
    // user = dao.select(id);
    // }
    // return user;
    // }
    
    // @Cacheable(value = "test", keyGenerator = "KeyGenerator")
    // public void putUser(Integer id, String userName)
    // {
    // User user = new User();
    //
    // this.getCache().set(String.valueOf(id), user);
    // }
    
    public User getById(Integer id)
    {
        return userDao.getById(id);
    }
    
    public List<User> getAllList()
    {
        return userDao.getAllList();
    }
}
