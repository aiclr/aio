package org.bougainvilleas.svc_a.impl;

import org.bougainvilleas.svc.api.Api;

/**
 * @author renqiankun
 * 2022-04-26 22:01:42 星期二
 */
public class AServiceImpl implements Api
{
    @Override
    public void exec()
    {
        System.err.println("默认调用 无参构造器 创建 AServiceImpl");
    }
}
