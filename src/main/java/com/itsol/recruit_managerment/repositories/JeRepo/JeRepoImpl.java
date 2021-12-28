//package com.itsol.recruit_managerment.repositories.JeRepo;
//
//import com.itsol.recruit_managerment.model.*;
//import com.itsol.recruit_managerment.repositories.IUserRespository;
//import com.itsol.recruit_managerment.repositories.JobStatusRepo;
//import com.itsol.recruit_managerment.repositories.JobsRepo;
//import com.itsol.recruit_managerment.repositories.ProfileRepo;
//import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
//import com.itsol.recruit_managerment.vm.UserVM;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//@Slf4j
//public class JeRepoImpl extends JeRepoBase implements jeRepo {
//    @Autowired
//    IUserRespository iUserRespository;
//
//    @Autowired
//    JobsRepo jobRepo;
//
//    @Autowired
//    JobStatusRepo jobStatusRepo;
//
//    @Autowired
//    ProfileRepo profileRepo;
//
//    public List<User> search(UserVM userVM) {
//        try {
//            StringBuilder stringBuilder = new StringBuilder();
//            Map<String, Object> map = new HashMap<>();
//            stringBuilder.append("select * from jobs_register jr, users u, job_status js, jobs j, profiles p\n");
//            stringBuilder.append(" WHERE jr.user_id = u.id and jr.profiles_id = p.id and jr.job_status_id = js.id and jr.jobs_id = j.id");
//            if (jobRegisterSearchVm.getApplicantName() != null && !jobRegisterSearchVm.getApplicantName().isEmpty()) {
//                stringBuilder.append(" and u.full_name = :fullName");
//                map.put("fullName", jobRegisterSearchVm.getApplicantName());
//            }
//            if (jobRegisterSearchVm.getPositionName() != null && !jobRegisterSearchVm.getPositionName().isEmpty()) {
//                stringBuilder.append(" and j.job_position = :jobPosition");
//                map.put("jobPosition", jobRegisterSearchVm.getPositionName());
//            }
//            if (jobRegisterSearchVm.getJobRegisterStatus() != null && !jobRegisterSearchVm.getJobRegisterStatus().isEmpty()) {
//                stringBuilder.append(" and js.status_name = :statusName");
//                map.put("statusName", jobRegisterSearchVm.getJobRegisterStatus());
//            }
//            if (jobRegisterSearchVm.getApplicationTimeFrom() != null && jobRegisterSearchVm.getApplicationTimeTo() != null && !jobRegisterSearchVm.getApplicationTimeFrom().isEmpty() && !jobRegisterSearchVm.getApplicationTimeTo().isEmpty()) {
//                stringBuilder.append(" and jr.application_time between to_date(:applicationTimeFrom, 'yyyy-MM-dd') and to_date(:applicationTimeTo, 'yyyy-MM-dd')");
//                map.put("applicationTimeFrom", jobRegisterSearchVm.getApplicationTimeFrom());
//                map.put("applicationTimeTo", jobRegisterSearchVm.getApplicationTimeTo());
//            }
//            return getNamedParameterJdbcTemplate().query(stringBuilder.toString(), map, new JobRegisterMapper());
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    class JobRegisterMapper implements RowMapper<User> {
//        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//            User dto = new User();
//            dto.setFullName(rs.getString("fullName"));
////            dto.getUserName(rs.getString("username"));
//            dto.setEmail(rs.getString("email"));
//            dto.setPhoneNumber(rs.getString("phoneNumber"));
//            return dto;
//        }
//    }
//}
