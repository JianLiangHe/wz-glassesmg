package com.wz.dao;

import java.util.List;

import com.wz.dto.FindUserDto;
import com.wz.pojo.Page;
import com.wz.pojo.User;

import io.lettuce.core.dynamic.annotation.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> userLists(FindUserDto dto);
    
    User getByName(String username);
    
    User getUserInfo(Integer id);
    
    int changePassword(String username,String password);
    
    
}