package com.cck;

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
public class Tag {
    
    private Integer id;
    private Integer createrId;
    private String name;
    private Integer topicNum;
    
}
