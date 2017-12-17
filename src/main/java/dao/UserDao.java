package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pojo.User;

public interface UserDao
{
    @Select("select id,username,password,serverstartday,serverendday,errortimes,iflock,logintime,userlev,groupname,session_id,lastUseCityCode,candownrows,beizhu from users where id = #{id}")
    public User getById(@Param("id") Integer id);
    
    @Select("select id,username,password,serverstartday,serverendday,errortimes,iflock,logintime,userlev,groupname,session_id,lastUseCityCode,candownrows,beizhu from users")
    public List<User> getAllList();
}
