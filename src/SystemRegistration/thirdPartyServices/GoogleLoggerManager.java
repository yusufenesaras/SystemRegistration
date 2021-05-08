package SystemRegistration.thirdPartyServices;

import SystemRegistration.entities.concretes.User;

public class GoogleLoggerManager {
	
	public void logInWithGoogleAccount(String message) {
		System.out.println("Google ile giris yapildi. " + message);
	}
	public void register(User user){ 
        System.out.println("Kullanici Google ile eklendi : " + user.getFirstName() + 
        		" " + user.getLastName().toUpperCase());
    } 
}
