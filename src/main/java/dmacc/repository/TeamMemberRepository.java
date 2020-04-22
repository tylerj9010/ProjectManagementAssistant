package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}
