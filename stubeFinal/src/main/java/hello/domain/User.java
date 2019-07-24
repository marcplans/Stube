package hello.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hello.utilities.Encryptor;
import hello.utilities.InvalidParamException;

@Entity(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="userId")
	private Integer id;
	private String name;
	private String email;
	private String password;

	public User() {
	}

	public User(String name, String email, String password) throws InvalidParamException {
		checkValidName(name);
		checkValidEmail(email);
		checkValidPassword(password);
		this.name = name;
		this.email = email;
		this.password = Encryptor.encryptPassword(password);
	}

	private void checkValidName(String name) throws InvalidParamException {
		if (name.equals(""))
			throw new InvalidParamException();
	}

	private void checkValidEmail(String email) throws InvalidParamException {
		if (email.equals("") || !email.contains(".com") || !email.contains("@"))
			throw new InvalidParamException();
	}
	
	 public void checkValidPassword(String password) throws InvalidParamException {
		 if (password.length() < 7)
		 throw new InvalidParamException();
	}

	public void checkPasswordIsCorrect(String password) throws InvalidParamException {
		Encryptor.checkIfPasswordMatches(password, this.password);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidParamException {
		checkValidName(name);
		this.name = name;
	}

	public void setEmail(String email) throws InvalidParamException {
		checkValidEmail(email);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
