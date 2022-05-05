package org.bougainvilleas.container.svc.b;

import org.bougainvilleas.container.api.ContainerApplication;
import org.bougainvilleas.container.svc.b.internal.Worker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AppB implements ContainerApplication
{

    ScheduledExecutorService executor;
    ScheduledFuture<?> scheduledFuture;

    public void startApp()
    {
        System.out.println("Starting App B");
        executor = Executors.newScheduledThreadPool(1);
        scheduledFuture =
                executor.scheduleAtFixedRate(new Worker(), 1L, 10L, TimeUnit.SECONDS);
    }

    public void stopApp()
    {
        scheduledFuture.cancel(false);
        executor = null;
        System.out.println("Stopping App B");
    }

}
