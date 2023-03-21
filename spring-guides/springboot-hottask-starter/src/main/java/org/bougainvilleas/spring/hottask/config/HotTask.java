package org.bougainvilleas.spring.hottask.config;

import org.bougainvilleas.spring.hottask.utils.HotTaskUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

public class HotTask implements Runnable
{
    private static final Logger log = LoggerFactory.getLogger(HotTaskRegistrar.class);

    protected String beanName;
    protected String methodName;
    protected String params;

    public HotTask()
    {
    }
    public HotTask(String beanName, String methodName)
    {
        this.beanName = beanName;
        this.methodName = methodName;
    }

    public HotTask(String beanName, String methodName, String params)
    {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotTask that = (HotTask) o;
        if (StringUtils.hasText(params))
            return beanName.equals(that.beanName) && methodName.equals(that.methodName) && params.equals(that.params);
        return beanName.equals(that.beanName) && methodName.equals(that.methodName) && that.params == null;
    }

    @Override
    public int hashCode()
    {
        if (StringUtils.hasText(params))
            return Objects.hash(beanName, methodName, params);
        return Objects.hash(beanName, methodName);

    }

    @Override
    public String toString()
    {
        if (StringUtils.hasText(params))
            return "{\"beanName\"=\"" + beanName + ", \"methodName\"=\"" + methodName + ",\"params\"=\"" + params + "\"}";
        return "{\"beanName\"=\"" + beanName + ", \"methodName\"=\"" + methodName + "\"}";

    }

    @Override
    public void run()
    {
        log.debug("{}{}.{}定时任务开始执行,方法参数={}", HotTaskProperties.HOT4TASK, beanName, methodName, params);
        long startTime = System.currentTimeMillis();
        try
        {
            Object target = HotTaskUtils.getBean(beanName);
            Method method;
            if (StringUtils.hasText(params))
            {
                method = target.getClass().getDeclaredMethod(methodName, String.class);
            }
            else
            {
                method = target.getClass().getDeclaredMethod(methodName);
            }
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.hasText(params))
            {
                method.invoke(target, params);
            }
            else
            {
                method.invoke(target);
            }

        }
        catch (Exception e)
        {
            log.error("{}{}.{}定时任务发生异常:{}", HotTaskProperties.HOT4TASK, beanName, methodName, e.getMessage(), e);
        }
        log.debug("{}{}.{}定时任务执行结束,用时{}ms", HotTaskProperties.HOT4TASK, beanName, methodName, System.currentTimeMillis() - startTime);
    }
}


