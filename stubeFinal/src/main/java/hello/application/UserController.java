package hello.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.application.dto.UserDTO;
import hello.domain.User;
import hello.persistence.UserRepository;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	public UserDTO register(UserDTO userDTO) throws InvalidParamException, NotFoundException {

		User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

		userRepository.save(user);

		return new UserDTO(user);
	}

	public UserDTO login(UserDTO userToLog) throws InvalidParamException, NotFoundException {

		User user = userRepository.getUserByEmail(userToLog.getEmail());

		user.checkPasswordIsCorrect(userToLog.getPassword());

		return new UserDTO(user);
	}

	public List<UserDTO> listUsers() throws NotFoundException {
		List<User> userList = userRepository.getAllUsers();
		List<UserDTO> userDTOList = new ArrayList<>();

		if (userList.isEmpty())
			throw new NotFoundException();

		for (User u : userList) {
			userDTOList.add(new UserDTO(u));
		}

		return userDTOList;
	}

	User getUser(int userId) throws NotFoundException {
		User user = userRepository.getUserById(userId);

		return user;
	}

	public UserDTO getUserDTO(int userId) throws NotFoundException {

		User user = userRepository.getUserById(userId);

		return new UserDTO(user);
	}

	public UserDTO updateUser(int userId, UserDTO userToUpdate) throws NotFoundException, InvalidParamException {

		User user = userRepository.getUserById(userId);

		if (!userToUpdate.getEmail().equals(""))
			user.setEmail(userToUpdate.getEmail());

		if (!userToUpdate.getName().equals(""))
			user.setName(userToUpdate.getName());

		userRepository.save(user);

		return new UserDTO(user);
	}

}
