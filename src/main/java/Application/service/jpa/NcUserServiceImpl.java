package Application.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Application.domain.NcUser;
import Application.repository.NcUserRepository;
import Application.service.NcUserService;

@Service
@Transactional
public class NcUserServiceImpl implements NcUserService {
	@Autowired
	private NcUserRepository userRepository;

	@Override
	public NcUser createUser(NcUser user) {
		NcUser result=userRepository.save(user);
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

}
