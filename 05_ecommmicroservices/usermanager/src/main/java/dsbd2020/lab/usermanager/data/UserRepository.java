package dsbd2020.lab.usermanager.data;

import org.springframework.data.repository.CrudRepository;
import user.User;

public interface UserRepository extends CrudRepository<User,Integer> {
    public User findByEmail(String email);

}
