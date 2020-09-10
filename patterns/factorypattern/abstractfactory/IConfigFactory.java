package factorypattern.abstractfactory;

import factorypattern.abstractfactory.nginx.INginxConfigParser;
import factorypattern.abstractfactory.redis.IRedisConfigParser;

/**
 * 场景：
 *      在简单工厂和工厂方法模式中，解析器只根据配置文件格式来分类，但是，如果类有两种分类方式，比如，我们既可以按照配置文件格式来分类，也可以按照解析的对象来分类。
 *      针对这种特殊的场景，如果还是继续用工厂方法来实现的话，我们需要针对每个 parser 都编写一个工厂类。如果未来还需要增加针对业务配置的解析器，那就要再对应地增加工厂类。
 *      过多的类会让系统难维护
 *
 * 抽象工厂模式，我们可以让一个工厂负责创建多个不同类型的对象。
 */
public interface IConfigFactory {
    // 这里扩展不同类型的创建类
    INginxConfigParser createNginxParser();
    IRedisConfigParser createRedisParser();
}
