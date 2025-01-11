package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TheLoaiRepository extends JpaRepository<TheLoai,Integer> {
}
