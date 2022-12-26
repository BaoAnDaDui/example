package com.github.ba.spi;

/**
 * SPI 服务接口
 * @author wang xiao
 * date 2022/12/26
 */
public interface DataStorage {


    /**
     * 查找
     * @param key
     * @return
     */
    String search(String key);
}
