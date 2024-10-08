package com.example.demo.model.user;

import com.example.demo.constant.Role;
import com.example.demo.constant.UserStatus;
import com.example.demo.global.common.BaseTimeEntity;
import com.example.demo.model.position.Position;
import com.example.demo.model.trust_score.TrustScore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String profileImgSrc;

    private String intro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<UserTechnologyStack> techStacks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trust_score_id")
    private TrustScore trustScore;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder
    private User(
            String email,
            String password,
            String nickname,
            String profileImgSrc,
            String intro,
            Position position,
            Role role,
            UserStatus status
            ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImgSrc = profileImgSrc;
        this.intro = intro;
        this.position = position;
        this.role = role;
        this.status = status;
    }

    // 기술스택 목록 등록
    public void setTechStacks(List<UserTechnologyStack> techStacks) {
        this.techStacks = techStacks;
    }

    // 기술스택 삭제
    public void removeTechStack(UserTechnologyStack userTechnologyStack) {
        this.techStacks.remove(userTechnologyStack);
    }

    // 기술스택 추가
    public void addTechStack(UserTechnologyStack userTechnologyStack) {
        this.techStacks.add(userTechnologyStack);
    }

    // 모든 기술스택 삭제
    public void removeAllTechStacks() { this.techStacks.clear(); }

    // 신뢰점수 등록
    public void setTrustScore(TrustScore trustScore) {
        this.trustScore = trustScore;
    }

    // 회원 수정
    public void update(String nickname, Position position, String intro) {
        this.nickname = nickname;
        this.position = position;
        this.intro = intro;
    }

    // 회원 프로필 수정
    public void updateProfileImgSrc(String profileImgSrc) {
        this.profileImgSrc = profileImgSrc;
    }

    // 회원 삭제
    public void deleteUser() {
        this.status = UserStatus.DELETED;
    }
}
