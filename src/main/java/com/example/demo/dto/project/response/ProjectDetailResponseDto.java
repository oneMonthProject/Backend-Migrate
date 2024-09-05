package com.example.demo.dto.project.response;

import com.example.demo.constant.ProjectStatus;
import com.example.demo.dto.technology_stack.response.TechnologyStackInfoResponseDto;
import com.example.demo.dto.trust_grade.response.TrustGradeResponseDto;
import com.example.demo.dto.user.response.UserProjectResponseDto;
import com.example.demo.global.util.LocalDateTimeFormatSerializer;
import com.example.demo.model.project.Project;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectDetailResponseDto {
    private Long projectId;
    private String name;
    private String subject;
    private TrustGradeResponseDto trustGrade;
    private UserProjectResponseDto user;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonSerialize(using = LocalDateTimeFormatSerializer.class)
    private LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeFormatSerializer.class)
    private LocalDateTime updateDate;

    private List<TechnologyStackInfoResponseDto> technologyStacks;

    public static ProjectDetailResponseDto of(
            Project project,
            TrustGradeResponseDto trustGradeDto,
            UserProjectResponseDto userProjectResponseDto,
            List<TechnologyStackInfoResponseDto> technologyStacks) {
        return ProjectDetailResponseDto.builder()
                .projectId(project.getId())
                .name(project.getName())
                .subject(project.getSubject())
                .trustGrade(trustGradeDto)
                .user(userProjectResponseDto)
                .status(project.getStatus())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createDate(project.getCreateDate())
                .updateDate(project.getUpdateDate())
                .technologyStacks(technologyStacks)
                .build();
    }
}
