package factorypattern.abstractfactory.redis.impl;

import factorypattern.abstractfactory.redis.IRedisConfigParser;

public class SpringConfigRedisParser implements IRedisConfigParser {
    @Override
    public String parseRedis() {
        return "spring config 实现 redis 配置";
    }
}
