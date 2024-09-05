package com.example.demo.model.board;

import com.example.demo.dto.board.request.BoardUpdateRequestDto;
import com.example.demo.global.common.BaseTimeEntity;
import com.example.demo.global.exception.customexception.BoardCustomException;
import com.example.demo.model.project.Project;
import com.example.demo.model.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTimeEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ColumnDefault("0")
    private int pageView;

    @ColumnDefault("false")
    private boolean recruitmentStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String contact;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BoardPosition> positions = new ArrayList<>();

    @Builder
    private Board(
            String title,
            String content,
            Project project,
            int pageView,
            boolean recruitmentStatus,
            User user,
            String contact) {
        this.title = title;
        this.content = content;
        this.project = project;
        this.pageView = pageView;
        this.recruitmentStatus = recruitmentStatus;
        this.user = user;
        this.contact = contact;
    }

    // 게시글 작성자 검증
    public void validationUser(User user) {
        if (!this.user.equals(user)) {
            throw BoardCustomException.NO_PERMISSION_TO_EDIT_OR_DELETE;
        }
    }

    public void updateProjectBoard(String title, String content, String contact, boolean recruitmentStatus) {
        this.title = title;
        this.content = content;
        this.contact = contact;
        this.recruitmentStatus = recruitmentStatus;
    }

    public void updateProjectBoardUser(User user){
        this.user = user;
    }

    public void setPositions(List<BoardPosition> list) {
        this.positions.clear();
        this.positions.addAll(list);
    }

    public void updatePageView() {
        this.pageView++;
    }

    public void updateRecruitmentStatus(boolean completed) {
        this.recruitmentStatus = completed;
    }
}
