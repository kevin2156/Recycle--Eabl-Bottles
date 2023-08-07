package com.eabl.api.controllers;

import com.eabl.api.dtosAndstructures.UserRoleEnum;
import com.eabl.api.services.UserCRUDService;
import com.eabl.api.models.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;


@RestController
@RequestMapping("/eabl/api")
public class OverallController {

    @Autowired
    UserCRUDService servo;



    //	save all users
    @PostMapping(value="/saveall", consumes={"application/xml", "application/json", "*/*"})
    public List<User> saveUsers(@RequestBody List<User> users) throws InvalidObjectException {

        List<User> userz;

        try {
            userz = servo.saveUsers(users);
        }catch (Exception e){
            throw new InvalidObjectException ("The format or type to save is not valid!!!" + e);
        }



        return userz;

    }



    //	save a single user
    @PostMapping(value="/save", consumes={"*/*",  "application/xml", "application/json", "*/*"})
    public User saveUser(@RequestBody User user) {

        return servo.saveUser(user);
    }

    //	list all users
    @RequestMapping("/users")
    public List<User> listUsers() {
        return servo.listUsers();
    }

    //	list a single user
    @GetMapping("/user/{id}")
    public User listUser(@PathVariable String id) {

        return servo.listUser(id);
    }

    //	delete a user
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        String deletedEmail = servo.listUser(id).getEmail() ;
        String deletedNatId = String.valueOf(servo.listUser(id).getNationalID());


        servo.deleteUser(id);

        return deletedNatId + ": "  + deletedEmail   +  " deleted successfully";
    }


    //	delete all users
    @DeleteMapping("/deleteall")
    public String deleteUser() {

        servo.deleteAllUsers();

        return   "All users deleted successfully";
    }

    //	update user
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User receivedUser) {

        User existingUser = servo.listUser(id);

        User toBeUpdated = existingUser;

//    String mail =        existingUser.getEmail();
//        toBeUpdated.setId(Long.valueOf(id));
        toBeUpdated.setFullNames(receivedUser.getFullNames());
//        toBeUpdated.setEmail(mail);
//        System.out.println(mail);
//        toBeUpdated.setPassword(existingUser.getPassword());
//        toBeUpdated.setNationalID(existingUser.getNationalID());
//        toBeUpdated.setUserRoleEnum(UserRoleEnum.USER);

//        toBeUpdated.setEmail(receivedUser.getEmail());
//        toBeUpdated.setNationalID(receivedUser.getNationalID());
//        toBeUpdated.setPassword(receivedUser.getPassword());
//        toBeUpdated.setUserRoleEnum(receivedUser.getUserRoleEnum());


        User updatedUser =servo.updateUser(toBeUpdated);


        return updatedUser;
    }

}
