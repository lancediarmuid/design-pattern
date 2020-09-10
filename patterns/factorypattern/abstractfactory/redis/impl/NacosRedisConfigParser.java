package factorypattern.abstractfactory.redis.impl;

import factorypattern.abstractfactory.redis.IRedisConfigParser;

public class NacosRedisConfigParser implements IRedisConfigParser {
    @Override
    public String parseRedis() {
        return "nacos 实现 nginx 配置";
    }
}
