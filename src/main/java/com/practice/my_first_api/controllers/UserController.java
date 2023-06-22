package com.practice.my_first_api.controllers;



import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private Map<Integer, User> users = new HashMap<>();

//    when the spring application starts, the constructor will run and add each user to the "users" HashMap
    public UserController() {
        addUser("Billy", "OR",1);
        addUser("julie", "CA",2);
        addUser("Billy", "OR",3);
        addUser("jack", "PA",4);
    }

    private void addUser(String name, String location, Integer id){
        User user = new User(name, location, id);
        users.put(id,user);
    }

    @GetMapping("/all")
    public Map<Integer, User> getAll(){
        return this.users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
//        if(!users.containsKey(id)){
//
//        }
        return this.users.get(id);
    }

    @GetMapping("/findBy")
    public ArrayList<User> findByNameAndLocation(@RequestParam String name, @RequestParam String location){
        ArrayList<User> foundUsers = new ArrayList<>();

        this.users.forEach((id, user) -> {
            if(user.getName().equals(name) && user.getLocation().equals(location)){
                foundUsers.add(user);
            }
        });

        return foundUsers;
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user){
//        adds the user to the map
        this.users.put(user.getId(), user);
//        sends a response back to the client, sends the user from the map
        return this.users.get(user.getId());
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updates){
        User userToUpdate = this.users.get(id);
        userToUpdate.setName(updates.getName());
        userToUpdate.setLocation(updates.getLocation());
        this.users.put(id, userToUpdate);
        return this.users.get(id);
    }

    @DeleteMapping("/{id}")
    public User deleteById(@PathVariable int id){
        return this.users.remove(id);
    }




}
