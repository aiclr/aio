<div style="text-align: center;font-size: 40px;">zip & unzip</div>

## gunzip

- 解压 ```gunzip -c xxx.log.gz > ./xxx.log```
- 压缩 ```gzip -c xxx.log > ./xxx.log.gz```

## zip

- 解压 ```unzip xxx.zip -d ./unzip/```
- 解压 windows 中文乱码 ```unzip -O CP936 xxx.zip -d ./unzip/```
- 压缩 ```zip -r xxx.zip ./xxx```