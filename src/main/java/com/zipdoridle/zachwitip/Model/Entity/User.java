package com.zipdoridle.zachwitip.Model.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true, nullable = false)
    private String oAuth2Id;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Builder
    public User(Long userId, String oAuth2Id, String nickName) {
        this.userId = userId;
        this.oAuth2Id = oAuth2Id;
        this.nickName = nickName;
    }
}
