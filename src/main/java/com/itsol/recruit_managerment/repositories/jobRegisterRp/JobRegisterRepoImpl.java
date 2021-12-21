package com.itsol.recruit_managerment.repositories.jobRegisterRp;

import com.itsol.recruit_managerment.dto.JobsRegisterDTO;
import com.itsol.recruit_managerment.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class JobRegisterRepoImpl extends JobRegisterRepoBase implements JobRegisterRepo {
    public List<JobsRegisterDTO> search(String searchField, String values) {
        JobsRegisterDTO jobsRegisterDTO = new JobsRegisterDTO();
        List<JobsRegisterDTO> jobsRegisterDTOList = null;

        try {
            StringBuilder stringBuilder = new StringBuilder();
            Map<String, Object> map = new HashMap<>();
            stringBuilder.append("select * from jobs_register jr, users u, job_status js, jobs j, profiles p\n");
            stringBuilder.append("WHERE jr.user_id = u.id and jr.profiles_id = p.id and jr.job_status_id = js.id and jr.jobs_id = j.id");
            if (jobsRegisterDTO.getUser().getFullName().equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(values)) {
                stringBuilder.append(" and u.full_name = :fullName");
                map.put("fullName", values);
            }
            if (jobsRegisterDTO.getJobs().getJobPosition().equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(values)) {
                stringBuilder.append(" and j.job_position = :jobPosition");
                map.put("jobPosition", values);
            }
            if (jobsRegisterDTO.getJobStatus().getStatusName().equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(values)) {
                stringBuilder.append(" and js.status_name = :statusName");
                map.put("statusName", values);
            }
            if (jobsRegisterDTO.getApplicationTime().toString().equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(values)) {
                stringBuilder.append(" and jr.application_time = :applicationTime");
                map.put("applicationTime", values);
            }
            jobsRegisterDTOList = getNamedParameterJdbcTemplate().query(stringBuilder.toString(), map, new BeanPropertyRowMapper<>(JobsRegisterDTO.class));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return jobsRegisterDTOList;
    }
}
