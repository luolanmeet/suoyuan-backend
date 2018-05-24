package com.cck;

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
public class Notify implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer userId;
    private String msg;
    private Integer topicId;
    private Integer isRead;
}
