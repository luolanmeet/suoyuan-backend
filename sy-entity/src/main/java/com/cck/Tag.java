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
public class Tag implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer createrId;
    private String name;
    private Integer topicNum;
}
