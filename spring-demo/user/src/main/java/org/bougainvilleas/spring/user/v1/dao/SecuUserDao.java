package org.bougainvilleas.spring.user.v1.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecuUserDao {

    int getUserByName(String username);
    List<String> getRolesByName(String username);
    List<String> getPermsByName(String username);
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SecuUser record);
//
//    int insertSelective(SecuUser record);
//
//    SecuUser selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(SecuUser record);
//
//    int updateByPrimaryKey(SecuUser record);
}