package org.viktoriianikitiuk.artoffreedom.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.viktoriianikitiuk.artoffreedom.model.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>{
    public Role findRoleByName(String role);

    @Query(value = "select * from role where role.id= (select role_id from users_roles where user_id = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(@Param("id") long id);
}