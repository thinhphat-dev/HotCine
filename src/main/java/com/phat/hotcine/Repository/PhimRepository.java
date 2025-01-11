package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface PhimRepository extends JpaRepository<Phim,Integer> {
    List<Phim> findAllByTenPhimLike(String keywords);

    List<Phim> findTop6ByOrderByLichChieusDesc();
}
