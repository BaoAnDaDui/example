package com.github.ba.spi;

import java.util.ServiceLoader;

/**
 * @author wang xiao
 * date 2022/12/26
 */
public class DataStorageServiceLoader {

    public static void main(String[] args) {
        ServiceLoader<DataStorage> serviceLoader = ServiceLoader.load(DataStorage.class);
        serviceLoader.forEach(dataStorage -> System.out.println(dataStorage.search("123")));
    }
}
