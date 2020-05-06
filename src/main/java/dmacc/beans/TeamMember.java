package dmacc.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team_member")
public class TeamMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberId")
	private long memberId;
	@Column(name = "firstName")
	@NotNull
    @NotEmpty
	private String firstName;
	@Column(name = "lastName")
	@NotNull
    @NotEmpty
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_user_id")
	private Users user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "manager_id")
	private TeamManager teamManager;
	
	public TeamMember() {
	}
	
	public TeamMember(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public TeamMember(long memberId, String firstName, String lastName) {
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
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
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public TeamManager getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(TeamManager teamManager) {
		this.teamManager = teamManager;
	}

	@Override
	public String toString() {
		return "TeamMember [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
