package org.bougainvilleas.svc.api;

import java.util.ServiceLoader;

public interface Api
{
    void exec();
    static ServiceLoader<Api> getApiImpl()
    {
        return ServiceLoader.load(Api.class);
    }
}
