package com.itsol.recruit_managerment.serviceimpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itsol.recruit_managerment.dto.PasswordDTO;
import com.itsol.recruit_managerment.dto.UserSignupDTO;
import com.itsol.recruit_managerment.email.EmailServiceImpl;
import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.model.Role;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.repositories.IUserRespository;
import com.itsol.recruit_managerment.repositories.JeRepo.JeRepoBase;
import com.itsol.recruit_managerment.repositories.OTPRepo;
import com.itsol.recruit_managerment.repositories.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import com.itsol.recruit_managerment.service.UserService;

import com.itsol.recruit_managerment.utils.CommonConst;
import com.itsol.recruit_managerment.utils.SqlReader;
import com.itsol.recruit_managerment.vm.UserSearchVM;
import com.itsol.recruit_managerment.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.auth0.jwt.JWTVerifier;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.stream.XMLStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@Slf4j
public class UserServiceimpl extends JeRepoBase implements UserService {

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    IUserRespository userRepo;
    @Autowired
    OTPRepo otpRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    IUserRespository iUserRespository;

    public UserServiceimpl(PasswordEncoder passwordEncoder, EmailServiceImpl emailService) {
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<User> getData() {
        return iUserRespository.findAll();
    }

    public int add(UserVM userVM) {
        User newUser = new User();
        try {
            boolean check = validateUser(userVM);
            if (!check) {
                System.out.println("........");
                return 0;
            }
            newUser.setGender(userVM.getGender());
            newUser.setEmail(userVM.getEmail());
            newUser.setHomeTown(userVM.getHomeTown());
            newUser.setPhoneNumber(userVM.getPhoneNumber());
            newUser.setFullName(userVM.getFullName());
            newUser.setUserName(userVM.getUserName());
            newUser.setPassword(passwordEncoder.encode(userVM.getPassword()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                newUser.setBirthDay(sdf.parse(userVM.getBirthDay()));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            iUserRespository.save(newUser);
            return CommonConst.SUCCESS;
        } catch (Exception e) {
            return CommonConst.ERROR;
        }
    }

    public boolean validateUser(UserVM userVM) {
        return true;
    }

    public int update(UserVM userVM, Long id) {
        User newUser = new User();
        try {
            boolean check = validateUser(userVM);
            if (!check) {
                System.out.println("........");
                return 0;
            }
            newUser.setId(id);
            newUser.setGender(userVM.getGender());
            newUser.setEmail(userVM.getEmail());
            newUser.setHomeTown(userVM.getHomeTown());
            newUser.setPhoneNumber(userVM.getPhoneNumber());
            newUser.setFullName(userVM.getFullName());
            newUser.setUserName(userVM.getUserName());
            newUser.setPassword(userVM.getPassword());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                newUser.setBirthDay(sdf.parse(userVM.getBirthDay()));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            iUserRespository.save(newUser);
            return CommonConst.SUCCESS;
        } catch (Exception e) {
            return CommonConst.ERROR;
        }
    }

    public int deleteById(Long deletepcId) {
        try {
            iUserRespository.deleteById(deletepcId);
            return CommonConst.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
            return CommonConst.ERROR;
        }
    }

    public List<User> searchName(String fullName) {
        List<User> list = new ArrayList<User>();
        if (fullName.isEmpty()) {
            list = userRepo.findAll();
        } else {
            list = userRepo.findByFullName(fullName);
        }
        return list;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public OTP saveOTP(OTP otp) {
        return otpRepo.save(otp);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUserName(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUserName(username);
    }

    @Override
    public User getUserById(Long id) {
        try {
            User user = userRepo.getUserById(id);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public OTP generateOTP(User user) {
        OTP otp = new OTP(user);
        return saveOTP(otp);
    }

    @Override
    public OTP getOTPByUser(User user) {
        return otpRepo.findByUser(user).orElse(null);
    }


    @Override
    public User createUser(UserSignupDTO userSignupDTO) {
       User userSearch =  userRepo.findByEmail(userSignupDTO.getEmail());
       if(userSearch !=null){
           return null;
       }
        return User.builder()
                .fullName(userSignupDTO.getFullName())
                .email(userSignupDTO.getEmail())
                .phoneNumber(userSignupDTO.getPhoneNumber())
                .homeTown(userSignupDTO.getHomeTown())
                .gender(userSignupDTO.getGender())
                .userName(userSignupDTO.getUserName())
                .password(passwordEncoder.encode(userSignupDTO.getPassword()))
                .build();

    }


    @Override
    public void verifyOTP(OTP otp, String otpCode) {
        if (!otp.getCode().equals(otpCode)) {
            throw new RuntimeException("Wrong opt code");
        } else if (otp.isExpired()) {
            throw new RuntimeException("OTP is expired");
        }
    }

    @Override
    public OTP retrieveNewOTP(User user) {
        OTP otp = getOTPByUser(user);
        if (otp == null) {
            otp = generateOTP(user);
            return otp;
        } else {
            OTP newOTP = new OTP();
            otp.setCode(newOTP.getCode());
            otp.setIssueAt(newOTP.getIssueAt());
            return otp;
        }
    }

    @Override
    public void activeAccount(User user) {
        user.setActive(true);
    }

    @Override
    public Object getAllJE() {
        return userRepo.getAllJE();
    }

    @Override
    public Object getAllUSER() {
        return userRepo.getAllUSER();
    }

    @Override
    public boolean verifyPassword(User user, PasswordDTO passwordDTO) {
        if (passwordDTO.getNewPassword() == null ||
                passwordDTO.getVerifyNewPassword() == null ||
                passwordDTO.getCurrentPassword() == null ||
                !passwordDTO.getNewPassword().equals(passwordDTO.getVerifyNewPassword()) ||
                !passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public void changePassword(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public User loadUserFromContext() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUser(username);
    }

    @Override

    public Page<User> getFullnameList(Pageable pageable, String userName, String fullName, String phoneNumber, String email) {

        return iUserRespository.getListFullnameByContaining(pageable, userName, fullName, phoneNumber, email);

    }





    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot find this user");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true, user.isActive(), authorities);
    }

    @Override
    public Object sendFogotPasswordMail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email không tồn tại trong hệ thống");
        }
        Random random = new Random();

        String password = random.ints(97, 123)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        emailService.sendSimpleMessage(user.getEmail(),
                "Link FogotPassword",
                "Mật khẩu mới của bạn là: " + password);
        return ResponseEntity.ok().body("check token in mail");
    }

    public Optional<User> findById(Long id) {
        return iUserRespository.findById(id);
    }

    public int deleteUser(Long id) {

        try {

            User newUser = iUserRespository.getUserById(id);

            newUser.setActive(false);

            iUserRespository.save(newUser);
            return CommonConst.SUCCESS;
        } catch (Exception e) {
            return CommonConst.ERROR;
        }
    }
    @Override
    public Object getUserInfo(HttpServletRequest request) {
        String authorHeader = request.getHeader("Authorization");
        String token = authorHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("roles").asArray(String.class);
    }
    @Override
    public List<User> searchJE(UserSearchVM searchJeVM, Integer pageIndex, Integer pageSize) {
        try {
            String query = SqlReader.getSqlQueryById(SqlReader.ADMIN_SEARCH_JE, "search_je");
            Map<String, Object> parameters = new HashMap<>();
            if (!ObjectUtils.isEmpty(searchJeVM.getFullName())) {
                String fullname = searchJeVM.getFullName().toUpperCase();
                query += " and UPPER( users.full_name)  like :p_name";
                parameters.put("p_name", "%"+fullname+"%");
            }
            if (!ObjectUtils.isEmpty(searchJeVM.getPhoneNumber())) {
                query += " and users.phone_number like :p_phone_number";
                parameters.put("p_phone_number", "%"+searchJeVM.getPhoneNumber()+"%");
            }
            if (!ObjectUtils.isEmpty(searchJeVM.getEmail())) {
                query += " and users.email like :p_email";
                parameters.put("p_email", "%"+searchJeVM.getEmail()+"%");
            }

            Integer p_startrow;
            Integer p_endrow;
            if(pageIndex==0)
            {
                p_startrow=pageSize*pageIndex;
                p_endrow=p_startrow+pageSize;
            }
            else {
                p_startrow=pageSize*pageIndex+1;
                p_endrow=p_startrow+pageSize-1;
            }

            query += " )tabWithRownum where tabWithRownum.ROWNR BETWEEN  :p_startrow and :p_endrow";
            parameters.put("p_startrow", p_startrow);
            parameters.put("p_endrow", p_endrow);

            return getNamedParameterJdbcTemplate().query(query, parameters, new UserServiceimpl.JeMapper());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
    class JeMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User dto = new User();
            dto.setId(rs.getLong("id"));
            dto.setFullName(rs.getString("full_name"));
            dto.setUserName(rs.getString("user_name"));
            dto.setBirthDay(rs.getDate("birth_day"));
            dto.setEmail(rs.getString("email"));
            dto.setGender(rs.getString("gender"));
            dto.setPhoneNumber(rs.getString("phone_number"));
            dto.setActive(rs.getBoolean("isactive"));
            return dto;
        }
    }
}
