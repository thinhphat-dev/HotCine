package com.phat.hotcine.Repository;

import com.phat.hotcine.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Integer> {

    NguoiDung findByEmailAndMatKhau(String email, String matkhau);
    NguoiDung findByEmail(String email);
}
