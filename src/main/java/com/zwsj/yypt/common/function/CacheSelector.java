package com.zwsj.yypt.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
