package com.yypt.common.properties;

/**
 * @创建人 zhk
 * @创建时间 2019-03-01
 * @描述
 */
public class YyptConstant {

    public static  final String EncryStr = "e10adc3949ba59abbe56e057f20f883e";

    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "yypt.cache.token:";


    // token缓存前缀
    public static final String CODE_CACHE_PREFIX = "yypt.cache.code:";

    //用户缓存前缀
    public static final String USER_CACHE_PREFIX = "yypt.cache.user:";


    //缓存通过token获取用户
    public static final String USER_TOKEN_CACHE_PREFIX = "yypt.cache.user.token:";

    //角色缓存前缀
    public static final String USER_ROLE_CACHE_PREFIX = "yypt.cache.user.role:";

    //用户权限缓存前缀
    public static final String USER_PERMISSION_CACHE_PREFIX = "yypt.cache.user.permission:";


    // user个性化配置前缀
    public static final String USER_CONFIG_CACHE_PREFIX = "yypt.cache.user.config:";


    // 存储在线用户的 zset前缀
    public static final String ACTIVE_USERS_ZSET_PREFIX = "fastweb.user.active";


    // 排序规则： descend 降序
    public static final String ORDER_DESC = "desc";
    // 排序规则： ascend 升序
    public static final String ORDER_ASC = "asc";



}
