package factorypattern.abstractfactory.impl;

import factorypattern.abstractfactory.IConfigFactory;
import factorypattern.abstractfactory.nginx.INginxConfigParser;
import factorypattern.abstractfactory.nginx.impl.SpringConfigNginxConfigParser;
import factorypattern.abstractfactory.redis.IRedisConfigParser;
import factorypattern.abstractfactory.redis.impl.SpringConfigRedisParser;

public class SpringCongifConfigFactory implements IConfigFactory {
    @Override
    public INginxConfigParser createNginxParser() {
        return new SpringConfigNginxConfigParser();
    }

    @Override
    public IRedisConfigParser createRedisParser() {
        return new SpringConfigRedisParser();
    }
}
