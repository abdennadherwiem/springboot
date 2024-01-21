package soa.services;

import soa.entities.User;

public interface UserService {

    User registerUser(User user);

    String loginUser(User user);
}
