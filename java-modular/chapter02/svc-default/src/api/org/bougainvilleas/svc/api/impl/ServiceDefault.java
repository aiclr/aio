package org.bougainvilleas.svc.api.impl;

import org.bougainvilleas.svc.api.Api;

public class ServiceDefault implements Api
{
    @Override
    public void exec()
    {
        System.err.println("接口模块 提供默认服务实现类 ServiceDefault");
    }
}
