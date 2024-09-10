package com.example.demo.dto.projectmember.response;

import com.example.demo.constant.ProjectMemberAuth;
import com.example.demo.constant.ProjectMemberStatus;
import com.example.demo.dto.position.response.PositionResponseDto;
import com.example.demo.dto.projectmember.ProjectMemberAuthDto;
import com.example.demo.dto.user.response.UserProjectDetailResponseDto;
import com.example.demo.global.util.LocalDateTimeFormatSerializer;
import com.example.demo.model.project.ProjectMember;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectMemberDetailResponseDto {
    private Long projectMemberId;
    private UserProjectDetailResponseDto user;
    private ProjectMemberAuthDto<ProjectMemberAuth> projectMemberAuth;
    private PositionResponseDto position;
    private ProjectMemberStatus status;

    @JsonSerialize(using = LocalDateTimeFormatSerializer.class)
    private LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeFormatSerializer.class)
    private LocalDateTime updateDate;

    public static ProjectMemberDetailResponseDto of(
            ProjectMember projectMember,
            UserProjectDetailResponseDto user,
            ProjectMemberAuthDto<ProjectMemberAuth> projectMemberAuth,
            PositionResponseDto position) {
        return ProjectMemberDetailResponseDto.builder()
                .projectMemberId(projectMember.getId())
                .user(user)
                .projectMemberAuth(projectMemberAuth)
                .position(position)
                .status(projectMember.getStatus())
                .createDate(projectMember.getCreateDate())
                .updateDate(projectMember.getUpdateDate())
                .build();
    }
}
