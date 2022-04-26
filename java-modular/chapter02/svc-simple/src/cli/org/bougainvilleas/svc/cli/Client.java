package org.bougainvilleas.svc.cli;

import org.bougainvilleas.svc.api.Api;

import java.util.ServiceLoader;

/**
 * @author renqiankun
 * 2022-04-26 22:22:39 星期二
 */
public class Client
{
    public static void main(String[] args)
    {
        ServiceLoader<Api> apis = ServiceLoader.load(Api.class);
        apis.stream()
                .forEach(api -> api.get().exec());
    }
}
