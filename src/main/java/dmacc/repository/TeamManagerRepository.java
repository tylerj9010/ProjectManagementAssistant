package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dmacc.beans.TeamManager;
import dmacc.beans.Users;

public interface TeamManagerRepository extends JpaRepository<TeamManager, Long> {
	TeamManager findByUser(Users user);
}