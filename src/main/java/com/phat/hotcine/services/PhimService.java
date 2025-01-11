package com.phat.hotcine.services;

import com.phat.hotcine.DTO.PhimDTO;
import com.phat.hotcine.Entity.Phim;
import com.phat.hotcine.Entity.TheLoai;
import com.phat.hotcine.Repository.PhimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhimService {
    @Autowired
    PhimRepository phimRepository;

    public List<PhimDTO> getAllPhim(){
        return listEntityToDTO(phimRepository.findAll());
    }
    public PhimDTO getPhimByID(int id){
        return entityToDTO(phimRepository.findById(id).get());
    }

    public List<PhimDTO> getTop6Phim(){
        List<Phim> list = phimRepository.findTop6ByOrderByLichChieusDesc();
        List<PhimDTO> dtoList = new ArrayList<>();
        for(Phim p : list){
            dtoList.add(entityToDTO(p));
        }
        return dtoList;
    }

    public List<PhimDTO> getAllPhimByKeywords(String keywords){
        List<Phim> list = phimRepository.findAllByTenPhimLike(keywords);
        List<PhimDTO> dtoList = new ArrayList<>();
        for(Phim p : list){
            dtoList.add(entityToDTO(p));
        }
        return dtoList;
    }
    public void deletePhim(PhimDTO phimDTO){
        Phim phim = dtoToEntity(phimDTO);
        phimRepository.deleteById(phim.getMaPhim());
    }
    public PhimDTO resetPhim(){
        PhimDTO phimDTO = new PhimDTO();
        return phimDTO;
    }
    private Phim dtoToEntity(PhimDTO phimDTO) {
        Phim phim = new Phim();
        phim.setMaPhim(phimDTO.getMaPhim());
        phim.setTenPhim(phimDTO.getTenPhim());
        phim.setDaoDien(phimDTO.getDaoDien());
        phim.setDienVien(phimDTO.getDienVien());
        phim.setThoiLuong(phimDTO.getThoiLuong());
        phim.setPoster(phimDTO.getPoster());
        phim.setTheloai(new TheLoai(phimDTO.getMaTheLoai(),phimDTO.getTheLoai()));
        phim.setTrailer(phimDTO.getTrailer());
        phim.setNgayRaMat(phimDTO.getNgayRaMat());
        phim.setQuocGia(phimDTO.getQuocGia());
        phim.setGioiThieu(phimDTO.getGioiThieu());
        return phim;
    }
    public Phim updatePhim(PhimDTO phimDTO, int id){
        Optional<Phim> optionalPhim = phimRepository.findById(id);
        if (optionalPhim.isPresent()) {
            Phim existingPhim = optionalPhim.get();
            // Cập nhật các trường cần thiết
            existingPhim.setTenPhim(phimDTO.getTenPhim());
            existingPhim.setDaoDien(phimDTO.getDaoDien());
            existingPhim.setDienVien(phimDTO.getDienVien());
            existingPhim.setThoiLuong(phimDTO.getThoiLuong());
            existingPhim.setPoster(phimDTO.getPoster());
            existingPhim.setTheloai(new TheLoai(phimDTO.getMaTheLoai(),phimDTO.getTheLoai()));
            existingPhim.setTrailer(phimDTO.getTrailer());
            existingPhim.setNgayRaMat(phimDTO.getNgayRaMat());
            existingPhim.setQuocGia(phimDTO.getQuocGia());
            existingPhim.setGioiThieu(phimDTO.getGioiThieu());


            return phimRepository.save(existingPhim);

        } else {
            throw new RuntimeException("Không tìm thấy phim với mã phim: " + id);
        }

    }

    public String createPhim(PhimDTO phimDTO){
        Phim phim = dtoToEntity(phimDTO);
        phimRepository.save(phim);

        return "/admin/add-item";
    }
    private List<PhimDTO> listEntityToDTO(List<Phim> list){
        List<PhimDTO> dtoList = new ArrayList<>();
        for(Phim p : list){
            dtoList.add(entityToDTO(p));
        }
        return dtoList;
    }
    private PhimDTO entityToDTO(Phim phim){
        PhimDTO phimDTO =new PhimDTO();
        phimDTO.setTenPhim(phim.getTenPhim());
        phimDTO.setMaPhim(phim.getMaPhim());
        phimDTO.setPoster(phim.getPoster());
        phimDTO.setTrailer(phim.getTrailer());
        phimDTO.setDaoDien(phim.getDaoDien());
        phimDTO.setDienVien(phim.getDienVien());
        phimDTO.setThoiLuong(phim.getThoiLuong());
        phimDTO.setTheLoai(phim.getTheloai().getTenTheLoai());
        phimDTO.setNgayRaMat(phim.getNgayRaMat());
        phimDTO.setQuocGia(phim.getQuocGia());
        phimDTO.setGioiThieu(phim.getGioiThieu());
        return phimDTO;
    }
}
