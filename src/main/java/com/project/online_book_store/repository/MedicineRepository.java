package com.project.online_book_store.repository;

import com.project.online_book_store.entity.Book;
import com.project.online_book_store.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Medicine findByName(String medicineName);

}
