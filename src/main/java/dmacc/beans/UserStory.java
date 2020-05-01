//package dmacc.beans;
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
//@Table(name = "userstory")
//public class UserStory {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "storyId")
//	private long storyId;
//	@Column(name = "typeOfUser")
//	private String typeOfUser;
//	@Column(name = "task")
//	private String task;
//	@Column(name = "priority")
//	private String priority; //TODO CHANGE IN DB TO "priority" TO MATCH TASK TABLE
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "projectId", nullable = true)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private Project projectId;
//	
//	public UserStory() {
//	}
//	
//	public UserStory(String typeOfUser, String task, String priority, Project projectId) {
//		this.typeOfUser = typeOfUser;
//		this.task = task;
//		this.priority = priority;
//		this.projectId = projectId;
//	}
//	
//	public UserStory(long storyId, String typeOfUser, String task, String priority, Project projectId) {
//		this.storyId = storyId;
//		this.typeOfUser = typeOfUser;
//		this.task = task;
//		this.priority = priority;
//		this.projectId = projectId;
//	}
//
//	public long getStoryId() {
//		return storyId;
//	}
//
//	public void setStoryId(long storyId) {
//		this.storyId = storyId;
//	}
//
//	public String getTypeOfUser() {
//		return typeOfUser;
//	}
//
//	public void setTypeOfUser(String typeOfUser) {
//		this.typeOfUser = typeOfUser;
//	}
//
//	public String getTask() {
//		return task;
//	}
//
//	public void setTask(String task) {
//		this.task = task;
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
//	public Project getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(Project projectId) {
//		this.projectId = projectId;
//	}
//	
//	
//	
//	
//	
//	
//}
