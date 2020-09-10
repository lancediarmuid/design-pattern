package factorypattern.abstractfactory.impl;

import factorypattern.abstractfactory.IConfigFactory;
import factorypattern.abstractfactory.nginx.INginxConfigParser;
import factorypattern.abstractfactory.nginx.impl.NacosNginxConfigParser;
import factorypattern.abstractfactory.redis.IRedisConfigParser;
import factorypattern.abstractfactory.redis.impl.NacosRedisConfigParser;

public class NacosConfigFactory implements IConfigFactory {
    @Override
    public INginxConfigParser createNginxParser() {
        return new NacosNginxConfigParser();
    }

    @Override
    public IRedisConfigParser createRedisParser() {
        return new NacosRedisConfigParser();
    }
}
