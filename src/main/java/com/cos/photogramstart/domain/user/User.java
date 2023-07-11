package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website;
    private String bio;

    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사진

    private String role;

    // 연관관계 주인이 아님, 테이블 컬럼 안만듦
    // Lazy = User를 select 할 때 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages()함수 호출될 때 가져옴
    // Eager = User를 select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져옴
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images; // 양방향 맵핑

    private LocalDateTime createDate;

    @PrePersist // 디비에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }


}
