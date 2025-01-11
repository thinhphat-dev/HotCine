package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.LichChieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LichChieuRepository extends JpaRepository<LichChieu,Integer> {
}
