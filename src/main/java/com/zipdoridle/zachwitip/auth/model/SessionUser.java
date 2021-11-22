package com.zipdoridle.zachwitip.auth.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long userId;
    private String nickName;

    public SessionUser(Long userId, String nickName) {
        this.userId = userId;
        this.nickName = nickName;
    }
}
