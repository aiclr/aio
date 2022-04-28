package org.bougainvilleas.svc.cli;

import org.bougainvilleas.svc.api.Api;
import org.bougainvilleas.svc.api.SVC;

import java.util.ServiceLoader;
import java.util.function.Predicate;

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
                .filter(SVCFilter)
                .forEach(api -> api.get().exec());
    }

    public static Predicate<ServiceLoader.Provider<Api>> SVCFilter = apiProvider->
    {
        // Provider 的 type 方法 可以获取服务实现的 java.lang.Class
        // 仅仅是拥有 Class 对象 但是 不一定可以实例化该对象
        Class<? extends Api> clazz = apiProvider.type();
        //当尝试通过如下反射获取一个实例时，如果该类没有导出 那么将会获得 IllegalAccessError
        //拥有一个Class对象 并不一定意味着可以在自己的模块中实例化它，模块系统的所有访问检查仍然适用
//        apiProvider.type().newInstance();

        return clazz.isAnnotationPresent(SVC.class)
                && (
//                    "AServiceImpl".equals(clazz.getAnnotation(SVC.class).value())
//                    ||
                    "BServiceImpl".equals(clazz.getAnnotation(SVC.class).value())
                    ||
                    "CServiceImpl".equals(clazz.getAnnotation(SVC.class).value())
                );

    };

}
