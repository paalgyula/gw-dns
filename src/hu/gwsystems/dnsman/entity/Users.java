package hu.gwsystems.dnsman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Users {
	@Id
	@SequenceGenerator(name="USERS_SEQ", sequenceName="USERS_SEQ", allocationSize=1, initialValue=0)
	@GeneratedValue(generator="USERS_SEQ", strategy=GenerationType.SEQUENCE)
	@Column
	private Long user_id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	public Long getId() {
		return user_id;
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
}
