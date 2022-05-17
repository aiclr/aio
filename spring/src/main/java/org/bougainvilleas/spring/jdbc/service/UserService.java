package org.bougainvilleas.spring.jdbc.service;

import org.bougainvilleas.spring.jdbc.po.User;

/**
 * @author renqiankun
 */
public interface UserService {
    int insert(User user);

    int update(int id,User user);

    User select(int id);
    User select2(int id);

    int delete(int id);
}
