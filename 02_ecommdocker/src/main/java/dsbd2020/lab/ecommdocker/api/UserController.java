package dsbd2020.lab.ecommdocker.api;

import dsbd2020.lab.ecommdocker.DataModel.User;
import dsbd2020.lab.ecommdocker.DataModel.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping(path="/id/{id}")
    public @ResponseBody User getUser(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    @GetMapping(path="/email/{email}")
    public @ResponseBody User getUser(@PathVariable String email){
        return repository.findByEmail(email);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAll(){
        return repository.findAll();
    }


    @PostMapping(path="/register")
    public @ResponseBody User register(@RequestBody User user){
        user.setRoles(Collections.singletonList("USER"));
        return repository.save(user);
    }

    @DeleteMapping (path="/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        repository.deleteById(id);
        return "the user with id= " + id + " has been deleted";
    }


}
