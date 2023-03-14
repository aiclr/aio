package org.bougainvilleas.spring.entity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisStringData extends RedisOperateData{
    private String key;
    private String value;
    private long timeout;
    private TimeUnit timeUnit;
    private long start;
    private long end;
    private Collection<String> keys;
    private long offset;
    private boolean bValue;
    private Map<String,String> maps;
    private long incrementL;
    private double incrementD;

}