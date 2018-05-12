package com.cck;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer topicId;      // 哪个话题下的回复
    private Integer fromUserId;   // 回复人id
    private String nickname;
    private String avator;
    private Integer toUserId;     // 被@的人的id
    private String toNickname;    // 被@的人的昵称
    private Integer toReplyId;    // 回复了那条评论id
    private String content;       // 回复内容
    private Date writeTime;
    private String path;
}
