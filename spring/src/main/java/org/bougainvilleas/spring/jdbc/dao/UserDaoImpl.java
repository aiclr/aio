package org.bougainvilleas.spring.jdbc.dao;

import org.bougainvilleas.spring.jdbc.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author renqiankun
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insert(User user) {
        String sql="insert into spring5_user values(?,?,?)";
        Object[] args={user.getId(), user.getName(), user.getAge()};
        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int update(int id, User user) {
        String sql="update spring5_user set name=?,age=? where id=?";
        Object[] args={user.getName(), user.getAge(),id};
        return jdbcTemplate.update(sql,args);
    }

    @Override
    public User select(int id) {
        String sql="select count(*) from spring5_user where id=?";
        Object[] args={id};
        System.out.println(jdbcTemplate.queryForObject(sql,Integer.class,args));


        String sql2="select * from spring5_user where id=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql2, args);
        User user=new User();
        user.setAge((Integer) map.get("age"));
        user.setName((String) map.get("name"));
        user.setId((Integer) map.get("id"));
        return user;
    }

    @Override
    public User select2(int id) {
        String sql2="select * from spring5_user where id=?";
        Object[] args={id};
        return jdbcTemplate.queryForObject(sql2, new BeanPropertyRowMapper<>(User.class),args);
    }

    @Override
    public int[] batchInsert(List<User> userList) {
        String sql="insert into spring5_user values(?,?,?)";
        List<Object[]> args=new ArrayList<>();
        for (User user:userList) {
            Object[] o={user.getId(),user.getName(),user.getAge()};
            args.add(o);
        }
        return jdbcTemplate.batchUpdate(sql,args);
    }



    @Override
    public int[] batchDelete(int[] id) {
        String sql="delete from spring5_user where id=?";
        List<Object[]> args=new ArrayList<>();
        for (int i=0;i<id.length;i++) {
            Object[] o={id[i]};
            args.add(o);
        }
        return jdbcTemplate.batchUpdate(sql,args);
    }

    @Override
    public int[] batchUpdate(int id, List<User> userList) {
        String sql="update spring5_user set name=?,age=? where id=?";
        List<Object[]> args=new ArrayList<>();
        for (User user:userList) {
            Object[] o={user.getName(),user.getAge(),user.getId()};
            args.add(o);
        }
        return jdbcTemplate.batchUpdate(sql,args);
    }

    @Override
    public List<User> selectList(int id) {
        String sql2="select * from spring5_user";
        return jdbcTemplate.query(sql2,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int delete(int id) {
        String sql="delete from spring5_user where id=?";
        Object[] args={id};
        return jdbcTemplate.update(sql,args);
    }

    /**
     * 事务
     */
    @Override
    public void reduceAge(int id,int num) {
        String sql="update spring5_user set age=age-? where id=?";
        Object[] args={num,id};
        jdbcTemplate.update(sql,args);
    }

    @Override
    public void addAge(int id,int num) {
        String sql="update spring5_user set age=age+? where id=?";
        Object[] args={num,id};
        jdbcTemplate.update(sql,args);
    }
}
