package com.object.resp;

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
public class BaseResp {

    private int code;
    private String cause;
    private Object data;
}
