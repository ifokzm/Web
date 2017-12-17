package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.User;
import service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    // 注解的方式
    @Autowired
    private UserService userService;
    
    // @Autowired
    // private RedisUtil util;
    
    // @RequestMapping(value = "/getUser/{id}")
    // public User getUser(@PathVariable int id)
    // {
    // return service.findUser(id, "张三");
    // }
    //
    // @RequestMapping(value = "/addUser/{id}/{userName}")
    // public void addUser(@PathVariable int id, @PathVariable String userName)
    // {
    // service.putUser(id, userName);
    // }
    
    // @RequestMapping(value = "/hello")
    // public String hello()
    // {
    // return (String)util.get("a");
    // }
    
    @RequestMapping("userlist")
    public ModelAndView userList()
    {
        userService.getAllList();
        return new ModelAndView("user");
    }
    
    @RequestMapping("save")
    @ResponseBody
    public void save(User user)
    {
    }
    
    @RequestMapping("update")
    @ResponseBody
    public void update(User user)
    {
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public void delete(Integer id)
    {
    }
    
}
