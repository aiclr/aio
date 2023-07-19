<div style="text-align: center;font-size: 40px;">MultipartFile</div>

## spring 文件是否为空 `boolean isEmpty();`

> org.springframework.web.multipart.MultipartFile
> > org.springframework.web.multipart.support.StandardMultipartHttpServletRequest \
> > org.springframework.web.multipart.commons.CommonsMultipartFile \
> > org.springframework.mock.web.MockMultipartFile
> 
> MultipartFile 的 isEmpty() 方法是根据文件大小进行判断，不是判断对象是否为 null \
> 规范使用防止 NPE

## source code

```jshelllanguage
    //StandardMultipartHttpServletRequest
    @Override
    public boolean isEmpty()
    {
        return (this.part.getSize() == 0);
    }
    //CommonsMultipartFile
    @Override
    public boolean isEmpty()
    {
        return (this.size == 0);
    }
    //MockMultipartFile
    @Override
    public boolean isEmpty()
    {
        return (this.content.length == 0);
    }
```