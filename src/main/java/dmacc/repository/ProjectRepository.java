package dmacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.Project;
import dmacc.beans.TeamManager;
import dmacc.beans.TeamMember;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByTeamMember(TeamMember teamMember);
	List<Project> findByTeamManager(TeamManager teamManager);
}
