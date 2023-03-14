package org.bougainvilleas.spring.hottask.api;


import org.bougainvilleas.spring.hottask.xml.XMLRoot;

public interface HotTaskSVC
{
    XMLRoot getAll() ;
    void setHotTask(XMLRoot xmlRoot);
}
