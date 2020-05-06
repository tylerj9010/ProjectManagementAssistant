package dmacc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auth_user_group")
public class AuthGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="auth_user_group_id")
	private long id;
	@Column(name="email")
	private String email;
	@Column(name="auth_group")
	private String authGroup;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthGroup() {
		return authGroup;
	}
	
	public void setAuthGroup(String authGroup) {
		this.authGroup = authGroup;
	}
}
