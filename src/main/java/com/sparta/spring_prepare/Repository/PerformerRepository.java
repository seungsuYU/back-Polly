package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
