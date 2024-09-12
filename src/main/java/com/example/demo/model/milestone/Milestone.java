package com.example.demo.model.milestone;

import com.example.demo.constant.ProgressStatus;
import com.example.demo.dto.milestone.request.MileStoneUpdateRequestDto;
import com.example.demo.dto.milestone.request.MilestoneUpdateContentRequestDto;
import com.example.demo.dto.milestone.request.MilestoneUpdateDateRequestDto;
import com.example.demo.global.common.BaseTimeEntity;
import com.example.demo.model.project.Project;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 프로젝트 마일스톤 엔티티
@Entity
@Table(name = "milestone")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Milestone extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "milestone_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "milestone_content")
    private String content;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private ProgressStatus progressStatus;

    @Builder
    public Milestone(
            Project project,
            String content,
            LocalDate startDate,
            LocalDate endDate,
            ProgressStatus progressStatus) {
        this.project = project;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progressStatus = progressStatus;
    }

    public void update(MileStoneUpdateRequestDto dto) {
        this.content = dto.getContent();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }

    public void updateContent(MilestoneUpdateContentRequestDto dto) {
        this.content = dto.getContent();
    }

    public void updateDate(MilestoneUpdateDateRequestDto dto) {
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }
}
