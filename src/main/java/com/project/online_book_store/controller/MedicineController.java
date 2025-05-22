package com.project.online_book_store.controller;

import com.project.online_book_store.dto.PurchasedBookDTO;
import com.project.online_book_store.dto.PurchasedMedicineDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.entity.Medicine;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.mapper.PurchasedMedicineMapper;
import com.project.online_book_store.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PurchasedMedicineMapper purchasedMedicineMapper;

    @PostMapping("/add")
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine){
        Medicine savedMedicine = medicineService.saveMedicine(medicine);
        return ResponseEntity.ok(savedMedicine);
    }

    @GetMapping("/view")
    public ResponseEntity<Page<PurchasedMedicineDTO>> getMedicines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Medicine> medicinePage = medicineService.findMedicine(pageable);

        Page<PurchasedMedicineDTO> dtoPage = medicinePage.map(purchasedMedicineMapper::toMedicineDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/view/{medicineName}")
    public ResponseEntity<PurchasedMedicineDTO> getByName(@PathVariable String medicineName) throws ResourceNotFoundException{
        return ResponseEntity.ok(medicineService.getMedicineByName(medicineName));
    }

    @GetMapping
    public ResponseEntity<List<PurchasedMedicineDTO>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<PurchasedMedicineDTO> dtos = medicines.stream()
                .map(purchasedMedicineMapper::toMedicineDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
