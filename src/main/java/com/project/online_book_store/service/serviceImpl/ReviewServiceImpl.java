package com.project.online_book_store.service.serviceImpl;

import com.project.online_book_store.repository.ReviewRepository;
import com.project.online_book_store.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
}
