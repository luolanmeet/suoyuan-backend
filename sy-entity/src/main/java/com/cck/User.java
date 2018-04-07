package com.cck;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author cck
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private String avator;
    private String signature;
    private Integer isOpen;            // 0:不公开    1:公开
    private Integer dirayCount;
    private String lastDirayDate;
    
    private Double nowEmotion;         // 当前情感值,当前情感会随时间变化而变化
    private Double lastDirayEmotion;   // 上次写日记的情感值
    private String lastLoginDate;      // 上次登录的时间
}
