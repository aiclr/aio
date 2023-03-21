package org.bougainvilleas.spring.hottask.api;

import org.bougainvilleas.spring.hottask.config.HotTaskProperties;
import org.bougainvilleas.spring.hottask.xml.XMLParser;
import org.bougainvilleas.spring.hottask.xml.XMLRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class HotTaskSVCImpl implements HotTaskSVC
{
    private static final Logger log = LoggerFactory.getLogger(HotTaskSVCImpl.class);

    HotTaskProperties hotTaskProperties;

    public HotTaskSVCImpl(HotTaskProperties hotTaskProperties)
    {
        this.hotTaskProperties = hotTaskProperties;
    }

    @Override
    public XMLRoot getAll()
    {
        XMLRoot result = null;
        try
        {
            result = (XMLRoot) XMLParser.getHotTask(hotTaskProperties.getFile(), XMLRoot.class);
        }
        catch (JAXBException e)
        {
            log.error("{}读取定时任务发生异常{}", HotTaskProperties.HOT4TASK, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void setHotTask(XMLRoot xmlRoot)
    {

        try
        {
            XMLParser.setHotTask(hotTaskProperties.getFile(), xmlRoot);
        }
        catch (JAXBException | IOException e)
        {
            log.error("{}读取定时任务发生异常{}", HotTaskProperties.HOT4TASK, e.getMessage(), e);
        }
    }
}
