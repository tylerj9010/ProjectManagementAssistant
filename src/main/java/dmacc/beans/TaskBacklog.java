//package dmacc.beans;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//@Entity
//@Table(name = "taskbacklog")
//public class TaskBacklog {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "taskId")
//	private long taskId;
//	@Column(name = "taskDescription")
//	private String taskDescription;
//	@Column(name = "Status")
//	private String status; //TODO CHANGE TO STRING IN DB
//	@Column(name = "priority")
//	private String priority;
//	@Column(name = "targetDate")
//	private Date targetDate;
//	@Column(name = "completedDate")
//	private Date completedDate;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "storyId", nullable = true)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private UserStory storyId;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "memberId", nullable = true)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private TeamMember memberId;
//
//	public TaskBacklog() {
//	}
//
//	public TaskBacklog(String taskDescription, String status, String priority, Date targetDate, Date completedDate,
//			UserStory storyId, TeamMember memberId) {
//		this.taskDescription = taskDescription;
//		this.status = status;
//		this.priority = priority;
//		this.targetDate = targetDate;
//		this.completedDate = completedDate;
//		this.storyId = storyId;
//		this.memberId = memberId;
//	}
//
//	public TaskBacklog(long taskId, String taskDescription, String status, String priority, Date targetDate,
//			Date completedDate, UserStory storyId, TeamMember memberId) {
//		this.taskId = taskId;
//		this.taskDescription = taskDescription;
//		this.status = status;
//		this.priority = priority;
//		this.targetDate = targetDate;
//		this.completedDate = completedDate;
//		this.storyId = storyId;
//		this.memberId = memberId;
//	}
//
//	public long getTaskId() {
//		return taskId;
//	}
//
//	public void setTaskId(long taskId) {
//		this.taskId = taskId;
//	}
//
//	public String getTaskDescription() {
//		return taskDescription;
//	}
//
//	public void setTaskDescription(String taskDescription) {
//		this.taskDescription = taskDescription;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getPriority() {
//		return priority;
//	}
//
//	public void setPriority(String priority) {
//		this.priority = priority;
//	}
//
//	public Date getTargetDate() {
//		return targetDate;
//	}
//
//	public void setTargetDate(Date targetDate) {
//		this.targetDate = targetDate;
//	}
//
//	public Date getCompletedDate() {
//		return completedDate;
//	}
//
//	public void setCompletedDate(Date completedDate) {
//		this.completedDate = completedDate;
//	}
//
//	public UserStory getStoryId() {
//		return storyId;
//	}
//
//	public void setStoryId(UserStory storyId) {
//		this.storyId = storyId;
//	}
//
//	public TeamMember getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(TeamMember memberId) {
//		this.memberId = memberId;
//	}
//
//	@Override
//	public String toString() {
//		return "TaskBacklog [taskId=" + taskId + ", taskDescription=" + taskDescription + ", Status=" + status
//				+ ", Priority=" + priority + ", targetDate=" + targetDate + ", completedDate=" + completedDate
//				+ ", storyId=" + storyId + ", memberId=" + memberId + "]";
//	}
//}
