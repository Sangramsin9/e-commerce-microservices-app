package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.model.Role;
import com.ecom.app.accountservice.model.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Role> dbRoles = user.getRoles().stream().map(r -> roleRepository.findById(r.getId()).get()).collect(Collectors.toSet());
        user.setRoles(dbRoles);
        return getUserRepository().save(user);
    }

}
