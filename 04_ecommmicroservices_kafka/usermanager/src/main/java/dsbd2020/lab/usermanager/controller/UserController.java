package dsbd2020.lab.usermanager.controller;

import dsbd2020.lab.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.User;

import java.util.Collections;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(path="/id/{id}")
    public @ResponseBody
    User getUser(@PathVariable Integer id){
        return service.getUser(id);
    }

    @GetMapping(path="/email/{email}")
    public @ResponseBody
    User getUser(@PathVariable String email){
        return service.getUser(email);
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAll(){
        return service.getAll();
    }


    @PostMapping(path="/register")
    public @ResponseBody
    User register(@RequestBody User user){
        user.setRoles(Collections.singletonList("USER"));
        return service.addUser(user);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    String delete(@PathVariable Integer id){
        service.deleteUser(id);
        return "the user with id= " + id + " has been deleted";
    }


}
