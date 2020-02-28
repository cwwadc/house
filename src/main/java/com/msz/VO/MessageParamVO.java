package com.msz.VO;

import lombok.Data;

/**
 * @Description: 消息实体类
 * @author: cww
 * @date: 2019/7/12 20:49
 */
@Data
public class MessageParamVO {

    private int UnprocessedNum;//未读
    private int processedNum;//已读
}
