package org.bougainvilleas.svc_b.impl;

import org.bougainvilleas.svc.api.Api;

/**
 * @author renqiankun
 * 2022-04-26 22:02:46 星期二
 */
public class CServiceImpl implements Api
{
    @Override
    public void exec()
    {
        System.err.println("CServiceImpl 由 服务提供者类 ServiceProvider.provider() 创建");
    }
}
