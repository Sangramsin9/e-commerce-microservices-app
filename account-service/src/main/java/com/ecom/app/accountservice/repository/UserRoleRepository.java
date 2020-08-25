package com.ecom.app.accountservice.repository;

import com.ecom.app.accountservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    public UserRole findUserRoleByName(String roleName);

}
