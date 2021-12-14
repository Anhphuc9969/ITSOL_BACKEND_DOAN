package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.config.AccountActivationConfig;
import com.itsol.recruit_managerment.constant.ConstantDateTime;
import com.itsol.recruit_managerment.dto.RoleDTO;
import com.itsol.recruit_managerment.dto.UserSignupDTO;
import com.itsol.recruit_managerment.email.EmailServiceImpl;
import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.model.Role;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.repositories.RoleRepo;
import com.itsol.recruit_managerment.service.UserService;
import com.itsol.recruit_managerment.service.UserServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    @Autowired
    AccountActivationConfig accountActivationConfig;

    @Autowired
    UserServiceimpl userServiceimpl;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupDTO userSignupDTO) {

        Role role = roleRepo.findByName("USER_ROLE");

        User user = userServiceimpl.createUser(userSignupDTO);
        if (ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantDateTime.YYYYMMDD_FOMART);
        try {
            user.setBirthDay(sdf.parse(userSignupDTO.getBirthDay()));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        user.setActive(false);
        user.setIsDelete(0); //  0 => deleted = false
        userService.saveUser(user);
        OTP otp = userService.generateOTP(user);

        String linkActive = accountActivationConfig.getActivateUrl() + user.getId();
        emailService.sendSimpleMessage(user.getEmail(),
                "Link active account",
                "<a href=\" " + linkActive + "\">Click vào đây để kích hoạt tài khoản</a>");
        return ResponseEntity.ok().body("check email for OTP");
    }
//        User user = new User();
//        Role role =roleRepo.findByName("ROLE_USER");
//        user.setFullName(userSignupDTO.getFullName());
//        user.setEmail(userSignupDTO.getEmail());
//        user.setPhoneNumber(userSignupDTO.getPhoneNumber());
//        user.setHomeTown(userSignupDTO.getHomeTown());
//        user.setGender(userSignupDTO.getGender());
//        user.setUserName(userSignupDTO.getUserName());
//        user.setPassword(passwordEncoder.encode(userSignupDTO.getPassword()));
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        user.setRoles(roles);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            user.setBirthDay(sdf.parse(userSignupDTO.getBirthDay()));
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//        user.setActive(false);
//        user.setIsDelete(0); //  0 => deleted = false
//        userService.saveUser(user);
//        OTP otp = userService.generateOTP(user);
//        String linkActive = "http://localhost:8080/active/" + user.getId();
//        emailService.sendSimpleMessage(user.getEmail(),
//                "Link active account",
//                 "<a href=\" "+ linkActive +"\">Click vào đây để kích hoạt tài khoản</a>");
//        return ResponseEntity.ok().body("check email for OTP");



    @GetMapping("/active/{id}")
    public ResponseEntity<String> activeAccount(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user.isActive()) {
                return ResponseEntity.ok().body("Your account is already activated");
            }
            userService.activeAccount(user);
            return ResponseEntity.ok().body("Active successfull");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
