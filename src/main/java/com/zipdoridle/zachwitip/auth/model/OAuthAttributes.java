package com.zipdoridle.zachwitip.auth.model;

import com.zipdoridle.zachwitip.auth.model.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;

    private String oauthId;
    private String name;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,  String oauthId, String name, String picture) {
        this.attributes = attributes;
        this.oauthId = oauthId;
        this.name = name;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String oAuthId, Map<String, Object> attributes){
        if("kakao".equals(registrationId)){
            return ofKakao(oAuthId, attributes);
        }
        return OAuthAttributes.builder().build();
    }
    private static OAuthAttributes ofKakao(String oauthId, Map<String, Object> attributes){
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .oauthId(oauthId)
                .name((String)kakaoProfile.get("nickname"))
                .attributes(attributes)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .nickName(this.name)
                .oauthId(this.oauthId)
                .build();
    }
}
