package com.object.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 我的首页  用户最近的日志
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirayResp {

    String time;
    String content;
}
