package dmacc.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	private String projectName;
	private Date dateCreated;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerId", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TeamManager managerId;
	
	public Project() {}
	
	public Project(long projectId, String projectName, Date dateCreated) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.dateCreated = dateCreated;
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
