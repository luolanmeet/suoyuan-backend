package com.email;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 
 * @author cck
 */
@Data
public class UserInfo {
    
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "address")
    private String mailAddress;
}
