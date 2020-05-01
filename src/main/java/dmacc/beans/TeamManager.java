package dmacc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TeamManager")
public class TeamManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "managerId")
	private long managerId;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	
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

	@Override
	public String toString() {
		return "TeamManager [managerId=" + managerId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}