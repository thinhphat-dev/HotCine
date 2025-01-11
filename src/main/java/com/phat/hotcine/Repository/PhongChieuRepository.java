package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.PhongChieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PhongChieuRepository extends JpaRepository<PhongChieu,Integer> {
}
