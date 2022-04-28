package org.bougainvilleas.svc.impl;

import org.bougainvilleas.svc.api.Api;
import org.bougainvilleas.svc.api.SVC;

/**
 * @author renqiankun
 * 2022-04-26 22:02:46 星期二
 */
@SVC("CServiceImpl")
public class CServiceImpl implements Api
{
    @Override
    public void exec()
    {
        System.err.println("CServiceImpl 由 服务提供者类 ServiceProvider.provider() 创建");
    }
}
