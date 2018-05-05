package com.object.resp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMsgAndDiray {

    private Integer id;
    private String email;
    private String nickname;
    private String avator;
    private String signature;
    private Integer dirayCount;
    private String lastDirayDate;
    private List<DirayResp> dirays;
}
