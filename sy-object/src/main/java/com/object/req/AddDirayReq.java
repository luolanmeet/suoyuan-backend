package com.object.req;

import java.io.Serializable;

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
public class AddDirayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String content;
}
