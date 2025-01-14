package com.example.demo.dto.work.request;

import com.example.demo.constant.ProjectMemberAuth;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkCompleteRequestDto {
    private Long workId;
    private ProjectMemberAuth authMap;
}
