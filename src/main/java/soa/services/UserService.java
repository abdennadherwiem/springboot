package soa.services;

import soa.entities.User;

import java.util.Map;

public interface UserService {

    User registerUser(User user);

    Map<String,Object> loginUser(User user);
}
