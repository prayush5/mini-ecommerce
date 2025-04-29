package com.project.online_book_store.controller;

import com.project.online_book_store.dto.PurchasedMedicineDTO;
import com.project.online_book_store.entity.Book;
import com.project.online_book_store.entity.Medicine;
import com.project.online_book_store.exception.ResourceNotFoundException;
import com.project.online_book_store.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine){
        Medicine savedMedicine = medicineService.saveMedicine(medicine);
        return ResponseEntity.ok(savedMedicine);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Medicine>> getMedicines(){
        return ResponseEntity.ok(medicineService.findMedicine());
    }

    @GetMapping("/view/{medicineName}")
    public ResponseEntity<PurchasedMedicineDTO> getByName(@PathVariable String medicineName) throws ResourceNotFoundException{
        return ResponseEntity.ok(medicineService.getMedicineByName(medicineName));
    }
}
