package application.service;

import application.domain.ChangePasswordForm;
import application.domain.NcUser;
import application.domain.SignupForm;

public interface NcUserService {
	public NcUser createUser(NcUser user);
	public NcUser findById(int id);
	public NcUser findByName(String name);
	public NcUser transformFromSignupForm(SignupForm form);
	public void changePassword(NcUser user,ChangePasswordForm form);
}
