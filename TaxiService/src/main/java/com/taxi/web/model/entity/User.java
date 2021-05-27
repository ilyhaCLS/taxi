package com.taxi.web.model.entity;

public class User {
	private int id;
	private String login;
	private String password;
	private String role;
	private byte[] salt;

	
	private User(int id, String login, String password, String role, byte[] salt) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.salt = salt;
	}

	
	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	
	
	public byte[] getSalt() {
		return salt;
	}
	
	
	public static class UserBuilder {
		private int id;
		private String login;
		private String password;
		private String role;
		private byte[] salt;
		
		public UserBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder setLogin(String login) {
			this.login = login;
			return this;
		}
		
		public UserBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder setRole(String role) {
			this.role = role;
			return this;
		}
		
		public UserBuilder setSalt(byte[] salt) {
			this.salt = salt;
			return this;
		}
		
		public User build() {
			return new User(id, login, password, role, salt);
		}
		
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		
		User other = (User) obj;
		if(!login.equals(other.getLogin())) {
			return false;
		}else if (!password.equals(other.getPassword())){
			return false;
		}else if(!role.equals(other.getRole())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}