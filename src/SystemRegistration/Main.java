package SystemRegistration;
import SystemRegistration.business.abstracts.UserService;
import SystemRegistration.business.concretes.UserManager;
import SystemRegistration.core.concretes.GoogleLoggerManagerAdapter;
import SystemRegistration.dataAccess.concretes.InMemoryUserDao;
import SystemRegistration.entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		
		
		UserService userService = new UserManager(new InMemoryUserDao(),new GoogleLoggerManagerAdapter());
	    userService.register(new User("Yusuf Enes","Aras","enesaras551@gmail.com","1234enes")); // Dogrulandi!
		userService.register(new User("Yusuf Enes","Aras","enesaras551","1234enes")); // Yanlis girildi ve hata alindi.
		
		User user1 = new User("Yusuf Enes","Aras","enesaras551@gmail.com","1234enes");
		User user2 = new User("Baran","Aras","baranaras@gmail.com","1234baran");
		userService.login(user1); //Google ile giris islemi
		userService.login(user2); // sistemde kayitli olmadigi icin kullanici bulunamadi hatasi.
		userService.delete(user1); // silme islemi.
		
		// Daha fazla islem de yapilabilir.
	}

}
