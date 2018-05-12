package com.cck;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公开日记用户信息
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenDirayUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String avator;
}
