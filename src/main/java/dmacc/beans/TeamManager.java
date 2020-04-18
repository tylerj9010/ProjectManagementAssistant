package dmacc.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teammanager")
public class TeamManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long managerId;
	private String firstName;
	private String lastName;
	//TODO CHECK WHERE USERNAME AND PASSWORD FIT INTO PESISTENCE BEANS
	private String userName;
	private String password;
	
	public TeamManager() {
	}
	
	public TeamManager(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public TeamManager(long managerId, String firstName, String lastName) {
		this.managerId = managerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

//	public TeamManager(long managerId, String firstName, String lastName, String userName, String password) {
//		this.managerId = managerId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.userName = userName;
//		this.password = password;
//	}
	
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	@Override
	public String toString() {
		return "TeamManager [managerId=" + managerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}

	
}
