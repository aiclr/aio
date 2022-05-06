package org.bougainvilleas.ilj.cli;

import org.bougainvilleas.ilj.api.Api;
import org.bougainvilleas.ilj.api.SVC;

import java.util.ServiceLoader;


public class Client
{
    public static void main(String[] args)
    {
        ServiceLoader<Api> apis = ServiceLoader.load(Api.class);
        apis.stream()
                .filter(svc -> checkSVC(svc.type()))
                .forEach(api -> api.get().exec());
    }

    public static boolean checkSVC(Class<?> clazz)
    {
        return clazz.isAnnotationPresent(SVC.class)
                && (
                "A".equals(clazz.getAnnotation(SVC.class).value())
                ||
                "B".equals(clazz.getAnnotation(SVC.class).value())
                ||
                "C".equals(clazz.getAnnotation(SVC.class).value())
        );
    }
}
