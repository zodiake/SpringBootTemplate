package Application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.repository.NcUserRepository;

@Service
public class NcUserService {
	@Autowired
	private NcUserRepository userRepository;

}
