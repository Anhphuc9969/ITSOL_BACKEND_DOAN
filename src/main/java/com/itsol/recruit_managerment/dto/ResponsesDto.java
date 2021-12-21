package com.itsol.recruit_managerment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsesDto <T> {
    long totalRecord;
    List<T> data;
}
