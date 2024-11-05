package com.iyed.users_microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyed.users_microservice.entities.Role;
import com.iyed.users_microservice.entities.User;
import com.iyed.users_microservice.repos.RoleRepository;
import com.iyed.users_microservice.repos.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository; 
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User user = findUserByUsername(username);
        Role role = roleRepository.findByRole(rolename);
        user.getRoles().add(role);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
