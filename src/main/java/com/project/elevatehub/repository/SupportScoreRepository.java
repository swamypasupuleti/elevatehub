package com.project.elevatehub.repository;


import com.project.elevatehub.model.SupportScore;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SupportScoreRepository extends JpaRepository<SupportScore, Long> {

    List<SupportScore> findByEmail(String email);

}
