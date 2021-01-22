package dsbd2020.lab.usermanager.service;

import dsbd2020.lab.usermanager.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.User;

import java.util.Collections;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUser(Integer id){
        return repository.findById(id).get();
    }

    public User getUser(String email){
        return repository.findByEmail(email);
    }

    public Iterable<User> getAll(){
        return repository.findAll();
    }

    public User addUser(User user){
        user.setRoles(Collections.singletonList("USER"));
        return repository.save(user);
    }

    public String deleteUser(Integer id){
        repository.deleteById(id);
        return "the user with id= " + id + " has been deleted";
    }

}
