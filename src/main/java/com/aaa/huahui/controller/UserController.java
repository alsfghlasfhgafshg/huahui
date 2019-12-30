package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.repository.UserRoleRepository;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.UserService;
import com.aaa.huahui.service.WxService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class UserController {
    @Autowired
    WxService wxService;

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BrandService brandService;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    public @ResponseBody
    JSONObject me(UsernamePasswordAuthenticationToken token) {
        User user = (User) token.getPrincipal();
        JSONObject re = new JSONObject();
        if (user.hasRole("ROLE_SHOP")) {
            String brandname = brandService.getBrandName(shopService.selectOneShop(user.getId()).getBrandid());
            re.put("name", brandname + "-" + user.getName());
            re.put("user", ((User) token.getPrincipal()));
            return ResponseGenerate.genSuccessResponse(re);
        } else {
            re.put("name", user.getName());
            re.put("user", ((User) token.getPrincipal()));
            return ResponseGenerate.genSuccessResponse(re);
        }
    }

    @GetMapping("/role")
    public @ResponseBody
    String getRole(UsernamePasswordAuthenticationToken token) {
        if (token == null) {
            return "null";
        }
        Collection<GrantedAuthority> authorities = token.getAuthorities();
        Iterator<GrantedAuthority> iterator = authorities.iterator();
        String roles = "";
        while (iterator.hasNext()) {
            GrantedAuthority next = iterator.next();
            roles = roles + next.getAuthority() + ", ";
        }
        return roles;
    }

    @GetMapping("/info")
    public @ResponseBody
    JSONObject getInfo(UsernamePasswordAuthenticationToken token) {
        if (token == null) {
            ResponseGenerate.genFailResponse(1, "未登录");
        }
        User user = (User) token.getPrincipal();


        String userName = user.getName();
        Collection<GrantedAuthority> authorities = token.getAuthorities();
        Iterator<GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority next = iterator.next();
        String authority = next.getAuthority();

        JSONObject responsejson = null;
        JSONObject data = new JSONObject();
        data.put("username", userName);
        data.put("userid", user.getId());

        if (user.hasRole(ROLE.SHOP)) {
            data.put("geo", shopService.selectOneShop(user.getId()).getGeo());
        }

        return ResponseGenerate.genSuccessResponse(data);
    }

    //设置
    @GetMapping("/setting")
    public String settings(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
                           Model model) {
        if (usernamePasswordAuthenticationToken == null) {
            return "redirect:/";
        }
        User u = (User) usernamePasswordAuthenticationToken.getPrincipal();
//        model.addAttribute("description", u.getDescription());
//        model.addAttribute("avatar", u.getAvatar());


        return "setting";
    }

    //设置
    @PostMapping("/setting")
    public String settingpost(
            @RequestParam("description") String description,
            UsernamePasswordAuthenticationToken token,
            Model model) {
        User user = (User) token.getPrincipal();
//        user.setDescription(description);
        userRepository.updateDescription(user);

        return "redirect:/home";
    }


    //注册
    @GetMapping("register")
    public String register() {
        return "register";
    }

    //注册
    @PostMapping("register")
    public String registerpost(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                               Model model) {

        try {
            userService.newUser(username, password, repeatpassword, ROLE.ADMIN);
            return "redirect:/login";
        } catch (NewUserFailException e) {
            model.addAttribute("errmsg", e.getErrors());
            return "register";
        }
    }


    //修改密码
    @PostMapping("/user/changepssword")
    public @ResponseBody
    JSONObject changePassword(@RequestParam("passwd") String passwd,
                              @RequestParam("repeatpasswd") String repeatpasswd,
                              UsernamePasswordAuthenticationToken token) {
        JSONObject responsejson = new JSONObject();

        User user = (User) token.getPrincipal();
        if (!passwd.equals(repeatpasswd)) {
            responsejson = ResponseGenerate.genFailResponse(1, "密码不一致");
            return responsejson;
        }

        if (passwd.length() < 6 || passwd.length() > 16) {
            responsejson = ResponseGenerate.genFailResponse(1, "密码需要在6-16之间");
            return responsejson;
        }

        if (user == null) {
            responsejson = ResponseGenerate.genFailResponse(1, "无权限");
            return responsejson;
        }

        int userid = user.getId();
        boolean result = userService.changePasswordByUserid(userid, passwd);

        responsejson = ResponseGenerate.genSuccessResponse("修改成功");
        return responsejson;
    }

    //清除jessionid
    @GetMapping("/clearjessionid")
    public @ResponseBody
    String clearJESSIONID(HttpServletResponse response) {

//        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("key", "anonymous",
//                AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));

//        Cookie cookie = new Cookie("JSESSIONID", null);
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);
        return "clear cookies JSESSIONID";
    }

    @GetMapping("/wxlogin")
    public @ResponseBody
    JSONObject wxLogin(HttpServletRequest request,
                       @RequestParam("code") String code, @RequestParam("state") String state,
                       UsernamePasswordAuthenticationToken token) {

        String openid = wxService.code2Openid(code);
        User principal = null;
        if (openid != null) {
            principal = wxService.openid2User(openid);
        } else if (token != null) {
            principal = ((User) token.getPrincipal());
        }

        JSONObject jsonObject = new JSONObject();

        //如果用户从未登录
        if (principal == null) {
            HttpSession session = request.getSession();
            session.setAttribute("openid", openid);

            jsonObject.put("needlogin", true);

            //如果用户已经微信登陆过
        } else {
            Object credentials = principal.getPassword();
            Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
            Authentication newAuth = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);

            SecurityContextHolder.getContext().setAuthentication(newAuth);

            jsonObject.put("needlogin", false);
        }
        logger.info("wx login result" + jsonObject.toJSONString());
        return ResponseGenerate.genSuccessResponse("ok", jsonObject);

    }

}
