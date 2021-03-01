package es.unex.pi.model;

import java.util.List;

public class User {

	private long id;
	private String username;
	private String email;
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean existsEmail(List<User> userList, String email) {
		boolean enc= false;
		if (!userList.isEmpty()) {
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getEmail().equals(email)) {
					System.out.println("User:" + email);
					enc = true;
				}
			}
		}
		return enc;
	}

}
