package org.bougainvilleas.resource.bundle.first;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.spi.AbstractResourceBundleProvider;

public class ZHSourceProvider extends AbstractResourceBundleProvider implements SourceProvider
{

    //source_zh.properties
//    private static Locale DUTCH = Locale.forLanguageTag("zh");
    private static Locale DUTCH = Locale.CHINESE;

    /**
     * 可以在此方法中指定自定义配置文件目录
     * @param baseName
     * @param locale
     * @return
     */
    @Override
    public String toBundleName(String baseName, Locale locale) {
        String bundleName = super.toBundleName(baseName, locale);
        String sourceDir="resources";
        if (DUTCH.equals(locale)) {
            int index = bundleName.lastIndexOf('.');
            System.err.println(bundleName.substring(0, index + 1) + sourceDir + bundleName.substring(index));
            return bundleName.substring(0, index + 1) + sourceDir + bundleName.substring(index);
        }
        return bundleName;
    }

    @Override
    public void exec()
    {
        //注意 getBundle("编译后的路径",DUTCH) toBundleName() 改变了路径
        //org.bougainvilleas.resource.bundle.first.source ---> org.bougainvilleas.resource.bundle.first.resources.source
        ResourceBundle bundle = getBundle("org.bougainvilleas.resource.bundle.first.source", DUTCH);
        System.err.println(bundle.getString("source"));
    }

    @Override
    public ResourceBundle getBundle(String baseName, Locale locale)
    {
        if (DUTCH.equals(locale)) {
            return super.getBundle(baseName, locale);
        }
        return null;
    }
}
