package hello.application.dto;

import com.google.gson.annotations.Expose;

import hello.domain.User;
import hello.utilities.NotFoundException;

public class UserDTO {
	@Expose
	private int id;
	@Expose
	private String name, email;	
	private String password;

	public UserDTO(User user) throws NotFoundException {
		if (user == null)
			throw new NotFoundException();

		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.id = user.getId();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		if(name == null) return "";
		return name;
	}

	public String getEmail() {
		if(email == null) return "";
		return email;
	}

	public String getPassword() {
		if(password == null) return "";
		return password;
	}

	@Override
	public String toString() {
		return name;
	}

}
