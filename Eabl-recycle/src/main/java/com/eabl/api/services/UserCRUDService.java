package com.eabl.api.services;

import com.eabl.api.repositories.UserRepository;
import com.eabl.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                UserCRUDService {

    @Autowired
    private UserRepository repo;

    //	get users
    public List<User> listUsers(){
        List<User> foundUsers = repo.findAll();

        return foundUsers;
    }

    //	get user
    public User listUser(String id) {
        User foundUser = repo.findById(Long.valueOf(id)).get();

        return foundUser;
    }


    //	save user
    public User saveUser(User user) {
//		System.out.println("Requesting to save user, "+ product.toString());
//		User bob = new User("Bob", 13220);
        return repo.save(user);
    }

    //	save users
    public List<User> saveUsers(List<User> users) {
        return repo.saveAll(users);
    }


    //	delete user
    public void deleteUser(String id) {
        repo.deleteById(Long.valueOf(id)) ;
    }

    //	delete all users
    public void deleteAllUsers() {
        repo.deleteAll();
    }


    //	update user
    public User updateUser(User user) {

        User existingUser = repo.findById(user.getId()).orElse(null);

        existingUser.setId(user.getId());
        existingUser.setFullNames(user.getFullNames());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUserRoleEnum(user.getUserRoleEnum());
        existingUser.setEnabled(user.getEnabled());


        return repo.save(existingUser);
    }
}
