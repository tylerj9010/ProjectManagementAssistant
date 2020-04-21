package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
