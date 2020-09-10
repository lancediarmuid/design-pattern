package factorypattern.abstractfactory.nginx.impl;

import factorypattern.abstractfactory.nginx.INginxConfigParser;

public class NacosNginxConfigParser implements INginxConfigParser {
    @Override
    public String parseNginx() {
        return "nacos 实现 nginx 配置";
    }
}
