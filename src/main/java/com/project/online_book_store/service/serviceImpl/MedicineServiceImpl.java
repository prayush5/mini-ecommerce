package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.repository.MedicineRepository;
import com.project.online_book_store.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
}
