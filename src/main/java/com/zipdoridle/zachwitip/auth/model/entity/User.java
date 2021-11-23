package com.zipdoridle.zachwitip.auth.model.entity;

import com.zipdoridle.zachwitip.global.model.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String oauthId;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Builder
    public User(String oauthId, String nickName) {
        this.oauthId = oauthId;
        this.nickName = nickName;
    }
}
