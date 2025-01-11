package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.HoiVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HoiVienRepository extends JpaRepository<HoiVien,Integer> {
}
