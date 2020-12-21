package dsbd2020.lab.ecommdocker.DataModel;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    public User findByEmail(String email);

}
