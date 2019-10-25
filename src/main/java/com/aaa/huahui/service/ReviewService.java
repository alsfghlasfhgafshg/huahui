package com.aaa.huahui.service;

import com.aaa.huahui.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public boolean isReviewExist(String createtime,int shopid){
        Optional<Integer> optional = reviewRepository.findReviewidByTimeAndShopid(createtime,shopid);
        return optional.isPresent();
    }

    public boolean addReview(int shopid,String createtime){
        return reviewRepository.addReview(shopid,createtime)==1;
    }
}
