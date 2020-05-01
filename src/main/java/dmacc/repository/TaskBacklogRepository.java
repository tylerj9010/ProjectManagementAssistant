package dmacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import dmacc.beans.TaskBacklog;

public interface TaskBacklogRepository extends JpaRepository<TaskBacklog, Long> {
//	List<Task> findByProjectId(Long projectId);
	
}
