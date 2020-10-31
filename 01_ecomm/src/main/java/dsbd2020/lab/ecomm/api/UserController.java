package dsbd2020.lab.ecomm.api;

import dsbd2020.lab.ecomm.DataModel.User;
import dsbd2020.lab.ecomm.DataModel.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;

@Controller
@RequestMapping (path = "/user")
public class UserController {
    @Autowired //serve per collegare il controller al repository automaticamente
    UserRepository userRepository;

    @GetMapping (path = "/id/{id}")
    public @ResponseBody User getUser(@PathVariable Integer id){
        /*
        @ResponseBody is a Spring annotation which binds a method return value to the web response body.
        It is not interpreted as a view name.
         It uses HTTP Message converters to convert the return value to HTTP response body,
          based on the content-type in the request HTTP header.
         */
        return userRepository.findById(id).get(); //01:50:00
    }

    @GetMapping (path = "/email/{email}")
    public @ResponseBody User getUser(@PathVariable String email){
        return userRepository.findByEmail(email);
    }

    @GetMapping (path = "/all")
    public @ResponseBody Iterable <User> getUser(){
        return userRepository.findAll();
    }

    @PostMapping(path="/register")
    public @ResponseBody User register(@RequestBody User user){
        //@RequestBody indica che questo parametro si deve trovare nel Body della richiesta http che quindi deve essere una POST
        user.setRoles(Collections.singletonList("USER"));
        //settiamo il ruolo di questo utente che inseriamo, siccome avevamo fatto una lista, utilizziamo singletonList.
        return userRepository.save(user);
    }

    @DeleteMapping (path = "/{id}")
    public @ResponseBody String delete(@PathVariable Integer id)
    {
        userRepository.deleteById(id);
        return "the user with id = " +id + " has been deleted";
    }





}
