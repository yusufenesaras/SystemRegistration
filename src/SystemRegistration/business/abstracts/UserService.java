package SystemRegistration.business.abstracts;

import java.util.List;

import SystemRegistration.entities.concretes.User;

public interface UserService {
	
	void register(User user);
    void login(User user);
    void delete(User user);
    User getByMail(String mail);
	List<User> getAll();
}
