package org.bougainvilleas.svc.api;

import org.bougainvilleas.svc.api.impl.ServiceDefault;

/**
 * @author renqiankun
 * 2022-04-26 22:13:43 星期二
 */
public class ServiceProvider
{
    public static ServiceDefault provider()
    {
        System.err.println("ServiceDefault 的 服务提供者类 ServiceProvider 拥有 方法 public static ServiceDefault provider()");
        return new ServiceDefault();
    }

}
