# [imagemagick](https://imagemagick.org/index.php)

> [github](https://github.com/ImageMagick)
> [Usage](https://imagemagick.org/Usage/)

## [ImageMagick Commands](https://imagemagick.org/Usage/basics/#im_commands)

- `identify` -- Print the details of imagces, that IM sees
- `Convert` -- Convert and Modify Images
- `Mogrify` -- in-place batch processing

> 转换格式
> > 将`nikon.NEF`转换为`png`格式图片
> > > `convert -verbose nef/nikon.NEF jpg/nikon.png`
> >
> > 将`nef/KUN_34*.NEF`批量转换为`jpg`格式图片，保存到 `jpg` 目录下
> > > `mogrify -verbose -path jpg -format jpg nef/KUN_*.NEF`
> >
> > `-quality value       JPEG/MIFF/PNG compression level`
>
> 转换大小
> > 将`lock.PNG` 压缩为`1920x1080`大小的`lock1080.PNG`图片
> > > `magick -verbose lock.PNG -resize 1920x1080 lock1080.PNG`
> >
> > 批量压缩
> > > `mogrify -verbose -path jpg1080 -resize 1920x1080 jpg/KUN_35*.jpg`
> >
> > ico
> > > `convert -resize 128x -gravity center -crop 128x128+0+0 bougainvilleas.png -background transparent bougainvilleas.ico`