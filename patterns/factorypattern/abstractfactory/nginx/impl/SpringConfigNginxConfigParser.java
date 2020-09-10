package factorypattern.abstractfactory.nginx.impl;

import factorypattern.abstractfactory.nginx.INginxConfigParser;

public class SpringConfigNginxConfigParser implements INginxConfigParser {
    @Override
    public String parseNginx() {
        return "spring config 实现 nginx 配置";
    }
}
