package org.viktoriianikitiuk.artoffreedom.dao;

import org.springframework.data.repository.CrudRepository;
import org.viktoriianikitiuk.artoffreedom.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    public User findUserByUserName(String name);
}