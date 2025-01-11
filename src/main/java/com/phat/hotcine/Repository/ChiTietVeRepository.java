package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.ChiTietVe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietVeRepository extends JpaRepository<ChiTietVe ,Integer> {
}
