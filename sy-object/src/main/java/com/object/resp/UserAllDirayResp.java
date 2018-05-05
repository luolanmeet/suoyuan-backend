package com.object.resp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页 用户所有日志
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAllDirayResp {

    String year;
    List<UserDirayResp> userDirays;
}
