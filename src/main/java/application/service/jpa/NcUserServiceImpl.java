package application.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.domain.Authority;
import application.domain.ChangePasswordForm;
import application.domain.NcUser;
import application.domain.SignupForm;
import application.repository.NcUserRepository;
import application.service.NcUserService;

@Service
@Transactional
public class NcUserServiceImpl implements NcUserService {
	@Autowired
	private NcUserRepository userRepository;
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Override
	public NcUser createUser(NcUser user) {
		NcUser result = userRepository.save(user);
		return result;
	}

	@Override
	public NcUser findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public NcUser findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public NcUser transformFromSignupForm(SignupForm form) {
		NcUser u = new NcUser();
		u.setName(form.getName());
		u.setPassword(encodePassword(form.getPassword()));
		u.setAuthority(new Authority("ROLE_USER"));
		return createUser(u);
	}

	@Override
	public void changePassword(NcUser user, ChangePasswordForm form) {
		NcUser result = userRepository.findOne(user.getId());
		result.setPassword(encodePassword(user.getPassword()));
		userRepository.save(result);
	}

	private String encodePassword(String source) {
		return passwordEncoder.encodePassword(source, null);
	}
}
