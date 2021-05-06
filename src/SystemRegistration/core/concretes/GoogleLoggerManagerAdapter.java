package SystemRegistration.core.concretes;

import SystemRegistration.core.abstracts.GoogleLoggerService;
import SystemRegistration.thirdPartyServices.GoogleLoggerManager;

public class GoogleLoggerManagerAdapter implements GoogleLoggerService{

	@Override
	public void logToSystem(String message) {
		GoogleLoggerManager googleLoggerManager = new GoogleLoggerManager();
		googleLoggerManager.logInWithGoogleAccount(message);
		 
	}

}
