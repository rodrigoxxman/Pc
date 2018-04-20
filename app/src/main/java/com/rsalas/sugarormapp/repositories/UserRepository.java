package com.rsalas.sugarormapp.repositories;

import com.orm.SugarRecord;
import com.rsalas.sugarormapp.models.User;

import java.util.List;

/**
 * Created by Alumno on 19/04/2018.
 */

public class UserRepository {

    public static List<User> list(){
        List<User> users= SugarRecord.listAll(User.class);
        return users;
    }
    public static User read(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void create(String username, String fullname, String email, String password){
        User user = new User(username, fullname, email, password);
        SugarRecord.save(user);
    }

    public static void update(String username, String fullname, String email, String password, Long id){
        User user = SugarRecord.findById(User.class, id);
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }

    public static void delete(Long id){
        User user = SugarRecord.findById(User.class, id);
        SugarRecord.delete(user);
    }
    public static User login(String username, String password){
        List<User> users= SugarRecord.listAll(User.class);
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        List<User> users= SugarRecord.listAll(User.class);
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }


}
