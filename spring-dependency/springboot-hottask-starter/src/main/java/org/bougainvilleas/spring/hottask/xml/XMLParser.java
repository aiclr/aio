package org.bougainvilleas.spring.hottask.xml;

import org.bougainvilleas.spring.hottask.config.HotTaskProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLParser
{

    private static final Logger log = LoggerFactory.getLogger(XMLParser.class);

    public static <T> Object getHotTask(String file, Class<T> clazz) throws JAXBException
    {
        if (StringUtils.hasText(file))
        {
            File f= new File(file);
            log.info("{}定时任务配置文件路径：{}", HotTaskProperties.HOT4TASK,f.getAbsolutePath());
            if(f.exists())
            {
                return JAXBContext
                        .newInstance(clazz)
                        .createUnmarshaller()
                        .unmarshal(f);
            }
        }
        return null;
    }

    public synchronized static <T> void setHotTask(String file, T t) throws JAXBException, IOException
    {
        if (StringUtils.hasText(file))
        {
            File f= new File(file);
            log.info("{}定时任务配置文件路径：{}", HotTaskProperties.HOT4TASK,f.getAbsolutePath());
            if(f.exists())
            {
                try (FileOutputStream outputStream = new FileOutputStream(f))
                {
                    Marshaller marshaller = JAXBContext.newInstance(t.getClass()).createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                    marshaller.marshal(t, outputStream);
                }
            }

        }
    }
}
