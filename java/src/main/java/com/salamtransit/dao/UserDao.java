package com.salamtransit.dao;

import com.salamtransit.model.RegisterUserDto;
import com.salamtransit.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
