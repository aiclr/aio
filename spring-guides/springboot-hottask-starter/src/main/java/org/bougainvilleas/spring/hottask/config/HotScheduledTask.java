package org.bougainvilleas.spring.hottask.config;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.scheduling.config.Task;

import java.util.concurrent.ScheduledFuture;

public final class HotScheduledTask
{
    private final Task task;

    @Nullable
    volatile ScheduledFuture<?> future;


    HotScheduledTask(Task task) {
        this.task = task;
    }


    /**
     * Return the underlying task (typically a {@link CronTask},
     * {@link FixedRateTask} or {@link FixedDelayTask}).
     * @since 5.0.2
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Trigger cancellation of this scheduled task.
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }

    @Override
    public String toString() {
        return this.task.toString();
    }
}
