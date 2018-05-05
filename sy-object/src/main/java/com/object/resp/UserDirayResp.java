package com.object.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页 用户以往的日记
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDirayResp {

    String month;
    String day;
    String time;
    String content;
}
