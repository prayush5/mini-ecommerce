package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.dto.PurchasedMedicineDTO;
import com.project.online_book_store.entity.Medicine;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.helper.Helper;
import com.project.online_book_store.mapper.PurchasedMedicineMapper;
import com.project.online_book_store.repository.MedicineRepository;
import com.project.online_book_store.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PurchasedMedicineMapper purchasedMedicineMapper;

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Page<Medicine> findMedicine(Pageable pageable) {
        return medicineRepository.findAll(pageable);
    }

    @Override
    public PurchasedMedicineDTO getMedicineByName(String medicineName) {
        Medicine medicine = medicineRepository.findByName(medicineName);
        if (medicine==null){
            throw new ResourceNotFoundException("Medicine not found with name: " + medicineName);
        }
        return purchasedMedicineMapper.toMedicineDTO(medicine);

    }

    @Override
    public void save(MultipartFile file) throws IOException {
        try {
            List<Medicine> medicines = Helper.convertExcelToListOfMedicine(file.getInputStream());
            this.medicineRepository.saveAll(medicines);
        } catch (IOException e) {
            throw new IOException("Failed to save books: " + e.getMessage());
        }
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return this.medicineRepository.findAll();
    }
}
