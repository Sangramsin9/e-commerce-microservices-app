package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.model.User;
import com.ecom.app.accountservice.model.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserManagerImpl extends BaseManager implements UserManager {

    @Override
    public User getUserByUsername(String username) {
        return getUserRepository().findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return getUserRepository().findAll();
    }

    @Override
    public User getUserById(Long id) {
        return getUserRepository().getOne(id);
    }

    @Override
    public User save(User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setActive(1);
        /*UserRole userRole = userRoleRepository.findUserRoleByName("ROLE_CUSTOMER");
        user.setRole(userRole);*/
        return getUserRepository().save(user);
    }

}
