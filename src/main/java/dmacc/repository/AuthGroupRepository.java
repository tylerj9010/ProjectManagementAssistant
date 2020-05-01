package dmacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.AuthGroup;

public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
	List<AuthGroup> findByEmail(String email);
}
