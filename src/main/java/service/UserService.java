package service;

import entity.User;

public class UserService {
    public User createUser(long id, String name, String email, String phone){
        return new User(id, name, email, phone);
    }
}
