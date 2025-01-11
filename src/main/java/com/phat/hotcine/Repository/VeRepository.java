package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.Ve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VeRepository extends JpaRepository<Ve,Integer> {
}
