package com.object.resp.community;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论和回复共用一个表，因此实体类也共用
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer replyId;   // 这条评论的id
    private Integer userId;    // 这条评论属于哪个用户
    private String content;
    private String nickname;
    private String avator;
    private String time;
    private Integer toUserId;
    private String toNickname;
    private Integer no;        // 几楼
}
