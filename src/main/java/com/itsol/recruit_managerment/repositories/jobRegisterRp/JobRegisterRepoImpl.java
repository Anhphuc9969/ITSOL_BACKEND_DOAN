package com.itsol.recruit_managerment.repositories.jobRegisterRp;

import com.itsol.recruit_managerment.vm.JobRegisterSearchVm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class JobRegisterRepoImpl extends JobRegisterRepoBase implements JobRegisterRepo {
    public List<JobRegisterSearchVm> search(JobRegisterSearchVm jobRegisterSearchVm) {
        List<JobRegisterSearchVm> jobsRegisterDTOList = null;

        try {
            StringBuilder stringBuilder = new StringBuilder();
            Map<String, Object> map = new HashMap<>();
            stringBuilder.append("select jr.application_time, u.full_name, j.job_position, js.status_name from jobs_register jr, users u, job_status js, jobs j, profiles p\n");
            stringBuilder.append("WHERE jr.user_id = u.id and jr.profiles_id = p.id and jr.job_status_id = js.id and jr.jobs_id = j.id");
            if (jobRegisterSearchVm.getApplicantName() != null) {
                stringBuilder.append(" and u.full_name = :fullName");
                map.put("fullName", jobRegisterSearchVm.getApplicantName());
            }
            if (jobRegisterSearchVm.getPositionName() != null) {
                stringBuilder.append(" and j.job_position = :jobPosition");
                map.put("jobPosition", jobRegisterSearchVm.getPositionName());
            }
            if (jobRegisterSearchVm.getJobRegisterStatus() != null) {
                stringBuilder.append(" and js.status_name = :statusName");
                map.put("statusName", jobRegisterSearchVm.getJobRegisterStatus());
            }
            if (jobRegisterSearchVm.getApplicationTimeFrom() != null && jobRegisterSearchVm.getApplicationTimeTo() != null) {
                stringBuilder.append(" and jr.application_time between to_date(:applicationTimeFrom, 'YYYYMMDD') and to_date(:applicationTimeTo, 'YYYYMMDD')");
                map.put("applicationTimeFrom", jobRegisterSearchVm.getApplicationTimeFrom());
                map.put("applicationTimeTo", jobRegisterSearchVm.getApplicationTimeTo());
            }
            jobsRegisterDTOList = getNamedParameterJdbcTemplate().query(stringBuilder.toString(), map, new BeanPropertyRowMapper<>(JobRegisterSearchVm.class));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return jobsRegisterDTOList;
    }
}
