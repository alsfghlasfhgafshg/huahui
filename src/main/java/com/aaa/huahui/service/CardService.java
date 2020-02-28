package com.aaa.huahui.service;

import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    Settlement_newService settlement_newService;

    @Autowired
    CardRepository cardRepository;



}
