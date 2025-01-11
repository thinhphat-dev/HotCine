package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.DoAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DoAnRepository extends JpaRepository<DoAn,Integer> {
}
