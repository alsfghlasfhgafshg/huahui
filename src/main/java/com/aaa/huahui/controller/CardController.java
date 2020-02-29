package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.Card;
import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.CardRepository;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.Settlement_newService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.utils.PageInfoGen;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class CardController {


    @Autowired
    CardRepository cardRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    BrandService brandService;

    @Autowired
    Settlement_newService settlement_newService;

//    @GetMapping("/card/findcardbynameortel")
//    public JSONObject findcardbynameortel(UsernamePasswordAuthenticationToken token,
//                                          @RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum) {
//        User user = (User) token.getPrincipal();
//        PageHelper.startPage(pagenum, 10);
//        PageInfo<Card> pageInfo = new PageInfo<Card>(cardRepository.selectCardByNameOrTel(nameortel, brandService.getBrandidByShopOrStaff(user)));
//
//        JSONObject response = ResponseGenerate.genSuccessResponse(pageInfo.getList());
//        response.put("pageinfo", PageInfoGen.gen(pageInfo));
//
//        return response;
//    }

    @GetMapping("/card/list")
    public JSONObject list(UsernamePasswordAuthenticationToken token,
                           @RequestParam(value = "nameortel", required = false, defaultValue = "") String nameortel,
                           @RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum) {
        User user = (User) token.getPrincipal();
        PageHelper.startPage(pagenum, 10);
        PageInfo<Card> pageInfo;
        if(nameortel!=null && !nameortel.equals("")){
            pageInfo = new PageInfo<Card>(cardRepository.selectCardByNameOrTel(nameortel, brandService.getBrandidByShopOrStaff(user)));
        }else {
            pageInfo = new PageInfo<Card>(cardRepository.list(brandService.getBrandidByShopOrStaff(user)));
        }
        JSONObject response = ResponseGenerate.genSuccessResponse(pageInfo.getList());
        response.put("pageinfo", PageInfoGen.gen(pageInfo));
        return response;
    }


    /**
     * 添加现金卡
     *
     * @param token
     * @param customer
     * @param telephone
     * @param money
     * @param beautician
     * @return
     */
    @PostMapping("/card/addcashcard")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject addCashCard(UsernamePasswordAuthenticationToken token,
                                  @RequestParam("customer") String customer,
                                  @RequestParam("telephone") String telephone,
                                  @RequestParam("money") Double money,
                                  @RequestParam("beautician") int beautician,
                                  @RequestParam("consultant") String consultant,
                                  @RequestParam("consumptionpattern") String consumptionpattern) {

        User user = (User) token.getPrincipal();
        Card card = new Card();
        card.setMoneyremaining(money);
        card.setName(customer);
        card.setTel(telephone);
        card.setType(2);
        card.setBrandid(brandService.getBrandidByShopOrStaff(user.getId()));
        int insertResult = cardRepository.insertCard(card);
        JSONObject responseJson = null;
        if (insertResult == 1) {
            responseJson = ResponseGenerate.genSuccessResponse("添加成功");
        } else {
            responseJson = ResponseGenerate.genFailResponse(1, "添加失败");
        }
        return responseJson;
    }

    @PostMapping("/card/increasemoney")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject shengka(UsernamePasswordAuthenticationToken token,
                              @RequestParam("cardnum") int cardnum,
                              @RequestParam("money") Double money) {
        User user = (User) token.getPrincipal();
        Card card = cardRepository.selectCard(cardnum, brandService.getBrandidByShopOrStaff(user.getId()));
        JSONObject responseJson = null;
        if (card == null) {
            responseJson = ResponseGenerate.genFailResponse(1, "未找到此卡");
            return responseJson;
        } else if (card.getType() == 2) {
            cardRepository.changeMoney(card.getId(), card.getMoneyremaining() + money, brandService.getBrandidByShopOrStaff(user));
            responseJson = ResponseGenerate.genSuccessResponse("升卡成功");
            return responseJson;
        } else if (card.getType() == 1) {
            responseJson = ResponseGenerate.genFailResponse(1,"此卡位卡扣卡");
            return responseJson;
        }
        return ResponseGenerate.genFailResponse(1, "未知错误");
    }

    /**
     * 核销
     * @param token
     * @param cardnum
     * @return
     */
    @Deprecated
    @PostMapping("/decreasetimes")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject hexiao(UsernamePasswordAuthenticationToken token,
                             @RequestParam("beautician1") Integer beautician1,
                             @RequestParam("beautician2") Integer beautician2,
                             @RequestParam("beautician3") Integer beautician3,
                             @RequestParam("beautician4") Integer beautician4,
                             @RequestParam(value = "allocate", required = false) String allocate,

                             @RequestParam(value = "checker", required = false) String checker,

                             @RequestParam("cardnum") int cardnum) {
        User user = (User) token.getPrincipal();
        int brandid = brandService.getBrandidByShopOrStaff(user);
        Card card = cardRepository.selectCard(cardnum, user.getId());
        JSONObject responseJson = null;
        if (card == null) {
            responseJson = ResponseGenerate.genFailResponse(1, "未找到此卡");
            return responseJson;
        } else if (card.getType() == 2) {
            responseJson = ResponseGenerate.genFailResponse(1, "此卡位现金卡");
            return responseJson;
        } else if(card.getType()==1){
            cardRepository.changeTimes(card.getId(),brandid);
            Settlement_new settlement_new=new Settlement_new();
            settlement_new.setBeautician1(beautician1);
            settlement_new.setBeautician2(beautician2);
            settlement_new.setBeautician3(beautician3);
            settlement_new.setBeautician4(beautician4);
            settlement_new.setShopid(staffService.findShopidByStaffId(user.getId()));
            settlement_new.setChecker(checker);
            settlement_new.setAllocate(allocate);
            settlement_new.setCustomer(card.getName());
            settlement_new.setTelephone(card.getTel());



            responseJson = ResponseGenerate.genSuccessResponse("升卡成功");
            return responseJson;
        }
        return ResponseGenerate.genFailResponse(1, "未知错误");
    }


}
