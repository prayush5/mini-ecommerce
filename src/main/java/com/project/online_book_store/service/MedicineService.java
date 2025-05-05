package com.project.online_book_store.service;

import com.project.online_book_store.dto.PurchasedMedicineDTO;
import com.project.online_book_store.entity.Medicine;
import com.project.online_book_store.mapper.PurchasedMedicineMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MedicineService {

    Medicine saveMedicine(Medicine medicine);
    Page<Medicine> findMedicine(Pageable pageable);
    PurchasedMedicineDTO getMedicineByName(String medicineName);
    void save(MultipartFile file) throws IOException;
    List<Medicine> getAllMedicines();
}
