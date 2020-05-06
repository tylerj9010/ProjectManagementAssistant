package dmacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.TeamManager;
import dmacc.beans.TeamMember;
import dmacc.beans.Users;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	TeamMember findByUser(Users user);
	List<TeamMember> findByTeamManager(TeamManager teamManager);
}
