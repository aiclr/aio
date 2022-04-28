package org.bougainvilleas.svc_b.impl;

import org.bougainvilleas.svc.api.Api;

/**
 * @author renqiankun
 * 2022-04-26 22:02:26 星期二
 */
public class BServiceImpl implements Api
{
    private BServiceImpl()
    {
        System.err.println("BServiceImpl 有 服务提供者方法 可以把构造器设为 private");
    }

    @Override
    public void exec()
    {
        System.err.println("BServiceImpl 由 服务提供者方法 创建");
    }

    public static BServiceImpl provider()
    {
        System.err.println("BServiceImpl 存在 服务提供者方法 public static BServiceImpl provider()");
        return new BServiceImpl();
    }

}
