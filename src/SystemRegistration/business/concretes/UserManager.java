package SystemRegistration.business.concretes;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import SystemRegistration.business.abstracts.UserService;
import SystemRegistration.core.abstracts.GoogleLoggerService;
import SystemRegistration.dataAccess.abstracts.UserDao;
import SystemRegistration.entities.concretes.User;

public class UserManager implements UserService{

	private UserDao userDao;
	private GoogleLoggerService googleLoggerService;
	
	public static final Pattern Email_Regex = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public UserManager(UserDao userDao, GoogleLoggerService googleLoggerService) {
		super();
		this.userDao = userDao;
		this.googleLoggerService = googleLoggerService;
	} 

	public UserDao getUserDao() {
		return userDao; 
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public GoogleLoggerService getgoogleLoggerService() {
		return googleLoggerService;
	}

	public void setgoogleLoggerService(GoogleLoggerService googleLoggerService) {
		this.googleLoggerService = googleLoggerService;
	}

	@Override
	public void register(User user) {
		if (user.getPassword().length() < 6) {
            System.out.println("Sifreniz en az 6 karakter uzunlugunda olmalidir.");
            return;
        }
		
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            System.out.println("Mail adresi e posta formatinda olmalidir: example@example.com");
            return;
        }
        
        if (userDao.getByEmail(user.getEmail()) != null){
            System.out.println("Kullanici zaten mevcut!");
            return;
        }
        
        if (user.getFirstName().length() < 2 || user.getLastName().length() < 2){
            System.out.println("Isim ve Soyisim 2 karakterden uzun olmalidir");
            return;
        }
        
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("E-Maili doðrulamak için 1'e basýn.");
			int  selecetion = scanner.nextInt();
			if (selecetion != 1){
			    System.out.println("Hata :  dogrulama basarisiz");
			    return;
			}
		}
        
        userDao.add(user);


	}

	@Override
	public void login(User user) {
		 if (user.getEmail() == null || user.getPassword() == null){
	            System.out.println("Email ve password zorunlu alandýr");
	            return;
	        }
		 
	        if (userDao.getByEmail(user.getEmail()) == null){
	            System.out.println("Kullanici bulunamadi.");
	            return;
	        }
	        
	        this.userDao.add(user);
	        this.googleLoggerService.logToSystem("Giris basarili : Google");
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
		System.out.println("Kullanici silindi.");
		
	}

	@Override
	public User getByMail(String mail) {
		 return userDao.getByEmail(mail);
		 
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
