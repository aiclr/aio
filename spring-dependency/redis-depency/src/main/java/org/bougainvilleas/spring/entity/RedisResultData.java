package org.bougainvilleas.spring.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Redis 操作返回结果
 * code: true ok/false error
 * @author caddy
 */
@Data
@Builder
public class RedisResultData {
    private boolean code;
    private Object data;
}
