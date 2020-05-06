package dmacc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "project")
public class Project extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId")
	private long projectId;
	@Column(name = "projectName")
	private String projectName;
	@Column(name = "description")
	private String description;
	@Column(name = "priority")
	private String priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerId", nullable = true)
	private TeamManager teamManager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = true)
	private TeamMember teamMember;
	
	public Project() {}

	public Project(long projectId, String projectName, String description, String priority, TeamManager teamManager, TeamMember teamMember) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.priority = priority;
		this.teamMember = teamMember;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public TeamManager getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(TeamManager teamManager) {
		this.teamManager = teamManager;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", description=" + description
				+ ", priority=" + priority + ", teamManager=" + teamManager + ", teamMember=" + teamMember + "]";
	}
}
