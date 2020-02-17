package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.Card;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.CardRepository;
import com.aaa.huahui.service.Settlement_newService;
import com.aaa.huahui.utils.PageInfoGen;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {


    @Autowired
    CardRepository cardRepository;

    @Autowired
    Settlement_newService settlement_newService;

    @GetMapping("/card/findcardbynameortel")
    public JSONObject findcardbynameortel(@RequestParam(value = "nameortel", required = false, defaultValue = "") String nameortel,
                                          @RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum) {
        PageHelper.startPage(pagenum, 10);
        PageInfo<Card> pageInfo = new PageInfo<Card>(cardRepository.selectCardByNameOrTel(nameortel));

        JSONObject response = ResponseGenerate.genSuccessResponse(pageInfo.getList());
        response.put("pageinfo", PageInfoGen.gen(pageInfo));

        return response;
    }
}
