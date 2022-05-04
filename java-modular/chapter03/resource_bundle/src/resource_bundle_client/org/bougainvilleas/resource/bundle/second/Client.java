package org.bougainvilleas.resource.bundle.second;

import org.bougainvilleas.resource.bundle.first.SourceProvider;

public class Client
{
    public static void main(String[] args)
    {
        SourceProvider.getSources().stream().forEach(provider-> provider.get().exec());
    }
}
