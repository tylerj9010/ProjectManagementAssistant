package dmacc.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId")
	private long projectId;
	@Column(name = "projectName")
	private String projectName;
	@Column(name = "dateCreated")
	private Date dateCreated;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TeamManager managerId;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE 
			})
	@JoinTable(name = "projectmemberbridge", 
			joinColumns = { @JoinColumn(name = "projectId")},
			inverseJoinColumns = { @JoinColumn(name = "memberId")})
	private Set<TeamMember> teamMembers = new HashSet<>();
	
	public Project() {}
	
	public Project(String projectName, Date dateCreated, TeamManager managerId) {
		this.projectName = projectName;
		this.dateCreated = dateCreated;
		this.managerId = managerId;
	}
	
	public Project(long projectId, String projectName, Date dateCreated, TeamManager managerId) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.dateCreated = dateCreated;
		this.managerId = managerId;
	}
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public TeamManager getManagerId() {
		return managerId;
	}

	public void setManagerId(TeamManager managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", dateCreated=" + dateCreated
				+ ", managerId=" + managerId + "]";
	}
	
}
