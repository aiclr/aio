package org.bougainvilleas.spring.jdbc.dao;

import org.bougainvilleas.spring.jdbc.po.User;

import java.util.List;

/**
 * @author renqiankun
 */
public interface UserDao {

    int insert(User user);
    int[] batchInsert(List<User> userList);

    int update(int id,User user);
    int[] batchUpdate(int id,List<User> userList);

    User select(int id);
    User select2(int id);
    List<User> selectList(int id);

    int delete(int id);
    int[] batchDelete(int[] id);

    /**
     *事务
     */
    void reduceAge(int id,int num);
    void addAge(int id,int num);
}
