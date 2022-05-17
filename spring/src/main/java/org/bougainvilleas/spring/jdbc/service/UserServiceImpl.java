package org.bougainvilleas.spring.jdbc.service;

import org.bougainvilleas.spring.jdbc.dao.UserDao;
import org.bougainvilleas.spring.jdbc.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author renqiankun
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(int id, User user) {
        return userDao.update(id,user);
    }

    @Override
    public User select(int id) {
        return userDao.select(id);
    }

    @Override
    public User select2(int id) {
        return userDao.select2(id);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }
}
