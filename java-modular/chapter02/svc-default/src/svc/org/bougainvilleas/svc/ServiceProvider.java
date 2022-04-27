package org.bougainvilleas.svc;

import org.bougainvilleas.svc.impl.CServiceImpl;

/**
 * @author renqiankun
 * 2022-04-26 22:13:43 星期二
 */
public class ServiceProvider
{
    public static CServiceImpl provider()
    {
        System.err.println("CServiceImpl 的 服务提供者类 ServiceProvider 拥有 方法 public static CServiceImpl provider()");
        return new CServiceImpl();
    }

}
