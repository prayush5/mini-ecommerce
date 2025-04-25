package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.repository.CompanyRepository;
import com.project.online_book_store.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
}
