package dmacc.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "taskbacklog")
public class TaskBacklog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	private String taskDescription;
	private String Status; //TODO CHANGE TO STRING IN DB
	private String Priority;
	private Date targetDate;
	private Date completedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storyId", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserStory storyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TeamMember memberId;

	public TaskBacklog() {
	}

	public TaskBacklog(String taskDescription, String status, String priority, Date targetDate, Date completedDate,
			UserStory storyId, TeamMember memberId) {
		this.taskDescription = taskDescription;
		Status = status;
		Priority = priority;
		this.targetDate = targetDate;
		this.completedDate = completedDate;
		this.storyId = storyId;
		this.memberId = memberId;
	}

	public TaskBacklog(long taskId, String taskDescription, String status, String priority, Date targetDate,
			Date completedDate, UserStory storyId, TeamMember memberId) {
		this.taskId = taskId;
		this.taskDescription = taskDescription;
		Status = status;
		Priority = priority;
		this.targetDate = targetDate;
		this.completedDate = completedDate;
		this.storyId = storyId;
		this.memberId = memberId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public UserStory getStoryId() {
		return storyId;
	}

	public void setStoryId(UserStory storyId) {
		this.storyId = storyId;
	}

	public TeamMember getMemberId() {
		return memberId;
	}

	public void setMemberId(TeamMember memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "TaskBacklog [taskId=" + taskId + ", taskDescription=" + taskDescription + ", Status=" + Status
				+ ", Priority=" + Priority + ", targetDate=" + targetDate + ", completedDate=" + completedDate
				+ ", storyId=" + storyId + ", memberId=" + memberId + "]";
	}
}
