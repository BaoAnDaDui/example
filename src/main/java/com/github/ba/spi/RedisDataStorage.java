package com.github.ba.spi;

/**
 * @author wang xiao
 * date 2022/12/26
 */
public class RedisDataStorage implements DataStorage{

    @Override
    public String search(String key) {
        return "redis";
    }
}