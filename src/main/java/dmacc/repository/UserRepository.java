package dmacc.repository;

import dmacc.beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByEmail(String email);
}
