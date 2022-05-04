package org.bougainvilleas.resource.bundle.first;

import java.util.ServiceLoader;
import java.util.spi.ResourceBundleProvider;

public interface SourceProvider extends ResourceBundleProvider
{
    void exec();

    static ServiceLoader<SourceProvider> getSources()
    {
        return ServiceLoader.load(SourceProvider.class);
    }

}
