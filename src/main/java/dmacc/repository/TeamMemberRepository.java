package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.TeamManager;

public interface TeamMemberRepository extends JpaRepository<TeamManager, Long> {

}
