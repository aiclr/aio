#include "stdio_rqk.h"

int main(int argc,char* argv[])
{
    FILE *fp=fopen(argv[1],"r");
#if 0
    注释的预处理器写法
    封装 错误处理宏 E_MSG
    帮助文档 man 3 errno
#endif
    if(fp==NULL)
        E_MSG("fopen",-1);
    printf("fopen success...\n");
    fclose(fp);
    return 0;
}
