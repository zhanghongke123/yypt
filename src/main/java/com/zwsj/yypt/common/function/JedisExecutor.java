package com.zwsj.yypt.common.function;


import com.zwsj.yypt.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
