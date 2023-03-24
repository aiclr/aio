# C

|C 语言|
|:---|
|[C Primer Plus 第6版 中文版](cprimerplus/README.md#c-primer-plus)|
|[Linux程序设计 第4版](linuxprogramming/README.md#Beginning-Linux-Programming)|
|[CMake](cmake/README.md)|

## gcc

1. -D 等于xxx.c 定义的#define PI=3.14
gcc -DPI=3.14 xxx.c -o xxx
2. -v 输出预处理，汇编，编译，链接的详细信息
gcc -v xxx.c -o xxx
3. -Wall 输出警告信息
gcc -Wall xxx.c -o xxx
4. -Werror 产生警告时停止编译
gcc -Werror xxx.c -o xxx
5. -std 指定语言标准
gcc -std=c90 xxx.c -o xxx
6. -O 代码优化选项，值在0～3，default=1,0表示不优化，3表示最高优化代码运行速度最快，但是编译时间和代码体积会受到影响
gcc -O2 xxx.c -o xxx
7. -E 预处理
gcc -E xxx.c -o xxx.i
8. -S 汇编
gcc -S xxx.i -o xxx.s
9. -c 编译
gcc -c xxx.s -o xxx.o
10. 链接
gcc xxx.o -o xxx
11. -l 指定头文件包含目录: b.c的头文件b.h
gcc -l /home/caddy/c/cpp/h xxx.c /home/caddy/c/cpp/s/b.c -o xxx
12. -L 指定库文件目录，假定在/home/caddy/c/lib 目录下有动态库 libadd.so 静态库 libadd.a gcc默认动态库优先，-static 选项表示动态库静态库同时存在，优先使用静态库。此外：linux下的库文件命名有一个约定，库文件以lib开头，因为所有的库文件都遵循这个约定，所以使用-l选项指定链接的库文件名时可以省去lib三个字母。
gcc -L /home/caddy/c/lib -static -ladd xxx.c -o xxx
13. ar 归档工具创建库文件,ar 创建一个归档文件libfoo.a,并将目标文件xx.o xx.o添加进去。ar工具将若干个单独的文件归并到一个大的文件中以创建归档文件或集合。ar可以创建任何类型文件的归档文件，是一个通用工具
ar crv ../lib/static/libfoo.a xx.o xx.o
14. ranlib xxx.a 为函数库生成内容表
ranlib libfoo.a
15. ar tv 列出归档的内容
ar tv libfoo.a
16. ar x 解压所有归档目标文件
ar x libfoo.a
17. ar dv 删除归档内的某一目标文件
ar dv libfoo.a xx.o

## 链接库时搜索路径顺序

### 静态库

1. ld会先搜索GCC 命令中-L指定的目录
2. 再搜索GCC的环境变量：LIBRARY_PATH
3. 再搜索目录/lib、/usr/lib、/usr/local/lib

### 动态库

1. 编译目标代码时-L指定的目录
2. 环境变量 ：LD_LIBRARY_PATH
3. 配置文件 /etc/ld.so.conf 中指定的动态库搜索路径
4. 默认的动态库路径 /lib、/usr/lib

## 格式字符串和参数列表

- %a %A 浮点数、十六进制数和p记数法（C99/C11）
- %c 单个字符
- %d %i 有符号十进制整数
- %e %E 浮点数，e记数法
- %f 浮点数，十进制记数法
- %g %G 根据值的不同，自动选择%f或%e.%e格式用于指数小于-4或者大于或者等于精度时
- %o 无符号八进制整数
- %p 指针
- %s 字符串
- %u 无符号十进制整数
- %x 小写x,无符号十六进制整数，使用十六进制数0f
- %X 大写X,无符号十六进制整数，使用十六进制数0F
- %% 打印一个百分号

### 格式字符和修饰符

- 标记：-、+、空格、#、0
- 数字：最小字段宽度  %2d
- .数字: 精度 %.2f
- h: 和整型转换说明一起使用，表示short int或unsigned short int类型
- hh: 和整型转换说明一起使用，表示short char或unsigned char类型的值
- j: 和整型转换说明一起使用，表示intmax_t或uintmax_t类型的值，这些类型定义在stdint.h中
- **-** 负号修饰符：待打印项左对齐。即，从字段的左侧开始打印该项
- **+** 正号修饰符：有符号值 若为正数，则在值前面显示正号，若为负数，则在值前面显示负号
- 空格：有符号值若为正，则在值前面显示一个前导空格（不显示任何符号），若为负，则在值前面显示负号并覆盖一个前导空格
- #：把结果转换为另一种形式八进制十六进制
  - %#o以0开头
	- %#x 以0x开头
	- %#X 以0X开头
	- 对于浮点数，#保证即使后面没有任何数字，也打印一个小数点字符。
	- %g和%G 使用# %#g %#G #防止结果后面的0被删除
- 0：零对于数值格式，前导0代替空格填充字段宽度，对于整数格式，如果出现-标记或指定精度，忽略0标记
- 例子：
	- %2d  100 = '100'、1 = ' 1' 右对齐输出宽度为2的字符，不足的位置使用空格填充，字段宽度2可以自动扩大以符合要输出的整数长度
	- %02d 100 = '100'、1 = '01' 右对齐输出宽度为2的字符，不足的位置使用填充,字段宽度自动扩大
	- %-2d 100 = '100'、1 = '1 ' 左对齐输出宽度为2的字符，不足的位置使用空格填充，字段宽度自动扩大
    - % d  100 = ' 100'、-100 = '-100'
	- %x  31 = 1f
	- %X  31 = 1F
	- %#x 31 = 0x1f
	- %#X 31 = 0X1F
	- %4.2f 3852.99 = 3852.99
	- %3.1f 3852.99 = 3853.0 四舍五入 整数位自动扩大

## Makefile

### 书写规则

```shell
targets:prerequisites
	command
targets:prerequisites;command
	command
```

- targets: 目标可以是Object File(中间文件，由gcc -c 编译产生)，也可以是可执行文件，还可以是一个标签
- prerequisites: 依赖文件，生成 targets 需要的文件或者目标，可以是多个也可以没有
- command: make 需要执行的命令（任意的 shell 命令）可以是多条命令，每一条命令占一行
- 注意：targets 和 prerequisites 中间使用英文冒号分隔，command开始前一定要使用Tab键

### 工作流程

- 通常使用的文件名： GNUmakefile、Makefile、makefile
- make 执行时会按顺序寻找makefile，推荐使用 Makefile
- 文件不存在报错信息```make:*** No rule to make target 'hellomake'. Stop.```

### 通配符

- `*` 匹配0个或任意个字符
- ？ 匹配任意一个字符
- [] 指定匹配的字符放在中括号内
- % 匹配任意个字符 %.o 把所有.o文件组合成一个列表，从列表中挨个去除每一个文件，%表示取出来文件的文件名，不包含后缀，然后找文件中和%名称相同的.c文件，然后执行command,知道列表中的文件全部被取出来

### 主要内容

- 如果生成一个或多个目标文件，这由makefile书写者明显之处，要生成的文件，文件的依赖文件，生成的命令

#### 隐晦规则

- make 明明有自动推导功能，所以隐晦的规则可以简略地书写 makefile 由make命令支持

#### 变量的定义

- 类似 c 语言的宏，makefile 被执行时，其中的变量会被扩展到相应的引用位置上
- 变量名=值列表
- 没有数据类型
- 变量名可以由大小写字母、下划线、阿拉伯数字构成
- 等号左右空白符可有可无，执行make的时候空白符会被自动删除
- 值列表：零项，一项，多项

##### 变量的基本赋值

1. 简单赋值 := 只对当前语句的变量有效
2. 递归赋值 = 赋值语句可能影响多个变量，所以目标变量相关的其他变量都受影响
3. 条件赋值 ？= 如果变量未定义，则使用符号中的值定义，如果该变量已经赋值则该赋值语句无效
4. 追加赋值 += 原变量用空格隔开的方式追加一个新值

```shell
x1:=foo
y1:=$(x1)b
x1:=new
test1:
	@echo "y1=$(y1),x1=$(x1)"
#y1=foob,x1=new
x2=foo
y2=$(x2)b
x2=new
test2:
	@echo "y2=$(y2),x2=$(x2)"
#y2=newb,x2=new
x3?=foo
y3?=$(x3)b
x3?=new
test3:
	@echo "y3=$(y3),x3=$(x3)"
#y3=foob,x3=foo
x4:=foo
y4:=$(x4)b
x4+=$(y4)
test4:
	@echo "y4=$(y4),x4=$(x4)"
#y4=foob,x4=foo foob
```

##### 预置变量

- makefile 定义好的变量
- 在规则模式中，规则的目标和依赖的文件名代表一类文件，规则的命令是对所有这一类文件的描述，描述规则时，依赖文件和目标文件是变动的，显然在命令中不能出现具体的文件名称，否则模式规则将失去意义
- make 会自动识别命令中的自动化变量，并且实现自动化变量中的值的替换，类似于编译 c 语言文件时候的预处理 gcc -E
- $@ 表示规则的目标文件名，如果目标是一个静态库文件，那么它代表这个文档的文件名;在多目标模式规则中，它代表的是触发规则被执行的文件名
- $% 当目标文件是一个静态库文件名，如果是一个目标文件使用隐含的规则来重建，则它代表由隐含规则加入的第一个依赖文件
- $< 规则的第一个依赖的文件名，如果是一个目标文件使用隐含规则来重建，则它代表由隐含规则加入的第一个依赖文件
- $? 所有比目标文件更新的依赖文件列表，空格分隔。如果目标文件是静态库文件，代表的是库文件 .o文件
- $^ 代表所有依赖文件列表，使用空格分隔；如果目标是静态库文件，它所代表的只能是所有的库成员（.o文件)名。一个文件可重复的出现在目标的依赖中，变量$^只记录它的第一次引用情况，会去掉重复的依赖文件
- $+ 和 $^ 类似，但是它保留了依赖中重复出现的文件，主要用在程序连接时库的交叉引用场合
- $* 在模式规则和静态模式规则中，代表”茎“，”茎“是目标模式中 % 所代表的部分（当文件名中存在目录时，“茎”也包含目录部分）
- $(@D) 表示文件的目录部分不包含斜杠。如 $@ 代表 dir/foo.o $(@D) 代表 dir；当$@ 代表foo.o $(@D) 代表 . 当前目录 
- $(@F) 表示文件除目录部分外的部分（实际的文件名）。如 $@ 代表 dir/foo.o $(@F) 代表 foo.o
- `$(*D) $(*F)` 分别代表“茎”中的目录部分和文件名部分
- `$(%D) $(%F)` 以archive形式静态库为目标时，分别表示库文件成员member名中的目录部分和文件名部分
- `$(<D) $(<F)` 表示第一个依赖的目录和文件名部分
- `$(^D) $(^F)` 表示第一个依赖的目录和文件名部分
- `$(+D) $(+F)` 表示第一个依赖的目录和文件名部分
- `$(?D) $(?F)` 表示第一个依赖的目录和文件名部分

##### 文件指示

1. 在一个makefile引用另一个makefile,类是c的include
2. 根据某些情况指定makefile中的有效部分，想c语言中的预编译#if 一样
3. 定义多行命令
4. 只有行注释 '#' 如果使用'#' 使用反斜杠转义'\#'

## 结构和结构指针的选择

```text
指针作为参数有两个优点：
    无论是以前还是现在的C实现都能使用这种方法，而且执行起来很快，只需要传递一个地址，缺点是无法保护数据。
被调函数中的某些操作可能会意外影响原来结构中的数据。ANSI C新增的const 限定符解决了这个问题，编译器会捕获这个错误。

结构作为参数传递的优点是，函数处理的是原始数据的副本，这保护了原始数据。代码风格更清楚

struct vector {double x;double y;};
struct vector ans,a,b;//ans 存储a b的和
struct vector sum_vect(struct vector a,struct vector b);
ans=sum_vect(a,b);

指针版
struct vector ans,a,b;
void sum_vect(struct vector *,struct vector *,struct vector*);
sum_vect(&ans,&a,&b);

传递结构的两个缺点：
    较老版本的实现可能无法处理这样的代码，而且传递结构浪费时间和存储空间
尤其是把大型结构传递给函数，而它只使用结构中的一两个成员时特别浪费。
这种情况下传递指针或只传递函数所需的成员更合理

为了追求效率使用结构指针作为函数参数，如需要防止原始数据被意外修改，使用const限定符
按值传递结构是处理小型结构最常用的方法

```

## 结构中的字符数组和字符指针

```text

struct names
{
	char first[20];
	char last[20];
};

struct names
{
	char * first;
	char * last;
};

使用char指针代替字符数组时，需要考虑字符串被存储在何处。
字符数组，初始化结构时，会分配相应大小的内存空间来存储字符数组
char指针，初始化结构时，只会分配两个字符指针地址的内存空间，结构只存储指针地址，不会为字符串分配任何存储空间
即使用的字符串是储存在别处的字符串(如字符串常量或数组中的字符串)

如果使用char指针代替字符数组，指针应该只用来在程序中管理那些已分配和在别处分配好的字符串。
误用会导致严重错误，但是不一定会每次都出现错误，程序中存在极大的隐患
```

## union 联合

> union是一种数据类型，能在同一个内存空间中储存不同的数据类型（不是同时储存）\
> 典型用法是：设计一种表以储存无规律、不知道顺序的混合类型 \
> 使用联合类型数组，其中的联合大小相等，每个联合可以储存各种数据类型


```
//创建联合和创建结构的方式相同，需要一个联合模板和联合变量
//可以用一个步骤定义联合，也可以用联合标记分两步定义
union hold
{
  int digit;
  double bigfl;
  char letter;
};
//根据以上形式声明的联合只能储存一个int类型 或 一个double类型 或 一个char类型
union hold fit;//hold 类型的联合变量，编译器分配足够的内存空间以便它能储存联合声明中占用最大字节的类型，本例分配sizeof(double)=8byte
union hold save[10]; //内含10个联合变量的数组,同上每个元素都分配8byte
union hold *pu;      //指向hold类型联合变量的指针,分配储存hold类型联合变量的地址
//初始化联合，联合只能储存一个值，与结构不同
union hold valA;
valA.letter='R';

union hold valB=valA;//用另一个union来初始化
union hold valC={99};//初始化联合的digit成员
union hold valD={.bigfl=119.2};//指定初始化器

//使用联合
fit.digit=23;//把23储存在fit。  2byte
fit.bigfl=2.0;//清除23,储存2.0。8byte
fit.letter='h';//清除2.0,储存'h'。1byte
//点运算符表示正在使用哪种数据类型，编码时注意当前储存在union中的数据类型
//访问 union，和用指针访问结构使用-> 运算符一样
pu=&fit;
x=pu->digit;//相当于x=fit.digit
//错误示例
fit.letter='A';
flnum=3.02*fit.bigfl;//fit 储存的是char类型，却按double类型来使用
//在结构中储存与其成员有从属关系的信息
struct owner 
{
  char socsecurity[12];
  ...
};

struct leasecompany 
{
  char name[40];
  char headquarters[40];
  ...
};

union data 
{
  struct owner owncar;
  struct leasecompany leasecar;
};

struct car_data 
{
  char make[15];
  int status; /* 私有为0，租赁为1 */
  union data ownerinfo;
  ...
};

//假设flits是car_data类型的结构变量，如果flits.status为0，程序将使用flits.ownerinfo.owncar.socsecurity，如果flits.status为1，程序则使用flits.ownerinfo.leasecar.name。

// 匿名联合：匿名联合和匿名结构工作原理相同,即匿名联合是一个结构或联合的无名联合成员
struct owner {
  char socsecurity[12];
  ...
};

struct leasecompany {
  char name[40];
  char headquarters[40];
  ...
};

struct car_data {
  char make[15];
  int status; /* 私有为0，租赁为1 */
  union {
    struct owner owncar;
    struct leasecompany leasecar;
  };
  .
};

//flits 是car_data类型的结构变量，可以用flits.owncar.socsecurity代替flits.ownerinfo.owncar.socsecurity

//成员运算符 .
struct 
{
  int code;
  float cost;
} item;
item.code = 1265;
//间接成员运算符 ->
struct 
{
  int code;
  float cost;
} item, * ptrst;
ptrst = &item;
//下面三个表达式等价，都是八一个int类型的值赋给item的code成员
ptrst->code = 3451;
item.code=3451;
(*ptrst).code=3451;
```

## typedef

> 高级数据特性，利用typedef可以为某一类型自定义名称，这方面与#define类似，但是有三处不同
> > typedef 创建的符号名只受限于类型，不能用于值 \
> > typedef 由编译器解释，不是预处理器 \
> > 在受限范围内，typedef 比 #define 更灵活
>
> 该定义的作用域取决于typedef定义所在的位置
> > 如果定义在函数中，具有局部作用域，受限于定义所在的函数 \
> > 如果定义在函数外面，就具有文件作用域
>
> 通常typedef定义中用大写字母表示被定义的名称，以提醒用户这个类型名实际上是一个符号缩写，可以用小写 \
> 为现有类型创建一个名称，typedef 提高程序可移植性 \
> sizeof运算符返回类型 size_t类型，time()函数返回类型 time_t类型 \
> C标准规定sizeof和time()返回整数类型，但是让实现来决定具体是什么整数类型 \
> 原因是，C标准委员会认为没有哪个类型对于所有的计算机平台都是最优选择 \
> 所以标准委员会建立一个新的类型名 size_t和time_t并让实现使用typedef来设置它具体的类型
>
> C标准提供以下通用原型
> > time_t time(time_t *); \
> > time_t 在一个系统中是unsigned long 在另一个系统中可以是 unsigned long long 只要包含 time.h头文件，程序就能访问合适的定义
> 
> typedef 和#define功能重合
> > #define BYTE unsigned char \
> > 使用预处理器用 BYTE代替 unsigned char
>
> typedef 特有功能
> > `typedef char * STRING;` \
> > 没有typedef 编译器将STRING识别为一个指向char的指针变量 \
> > 有了typedef 编译器把STRING解释成一个类型的标识符，该类型是指向char的指针 \
> > `STRING name,sign;` 相当于 `char *name,*sign;` \
> > 如果`#define STRING char *` \
> > `STRING name,sign;` 相当于 `char *name,sign` 只有name才是指针 \
> > typedef 用于结构 `typedef struct complex{float real;float imag;} COMPLEX;` \
> > 用COMPLEX类型代替complex结构来表示复数
>
> 使用typedef原因
> > 1. 为经常出现的类型创建一个方便、易识别的类型名 \
> > 2. 给复杂类型命名
> > > typedef char(*FRPTC()) [5]; 把FRPTC声明为一个函数类型，该函数返回一个指针，该指针指向内含5个char类型元素的数组
> 
> typedef 并没有创建任何新类型，它只是为某个已存在的类型增加了一个方便使用的标签 \
> 通过struct union typedef C提供了有效处理数据的工具和处理可移植数据的工具

### 复杂声明

|符号|含义|
|:---|:---|
|*|表示一个指针|
|()|表示一个函数|
|[]|表示一个数组|

```
int board[8][8]; //声明一个内含int数组的数组
int **ptr; //声明一个指向int的指针的指针
int *risks[10]; //声明一个内含10个元素的数组，元素是指向int的指针，指针数组
int (*rusks)[10]; //声明一个指向数组的指针，被指向的数组内含10个int类型的值，数组指针
int *oof[3][4]; //声明一个3X4的二维数组，每个元素都是指向int的指针，二维指针数组
int (*uuf)[3][4]; //声明一个指向3X4二维数组的指针，被指向的二维数组内含有int类型值
int (*uof[3])[4]; //声明一个内含3个指针元素的数组(指针数组)，其中每个指针都指向一个内含4个int类型元素的数组

char *fump(int);//返回字符指针的函数
char (*frump)(int);//指向函数的指针，该函数的返回类型为char
char (*flump[3])(int);//内含3个指针的数组，每个指针都指向返回类型为char的函数

//使用typedef建立一系列相关类型
typedef int arr5[5];
typedef arr5 * p_arr5;
typedef p_arr5 arrp10[10];
arr5 togs;//int togs[5];
p_arr5 p2;//int *p2=togs;
arrp10 ap;//int *ap[10][5];
```

> 1. 数组名后面的[]和函数名后面的()具有相同的优先级 比*优先级高。所以int * risks[10];是一个存放指针的数组，不是指向数组的指针 \
> 2. [] () 优先级相同，从左往右结合。int (*rusks)[10];*先与rusks结合，rusks是一个指针，指向一个内含10个int类型元素的数组 \
> 3. int goods[12][50]; 内含12个元素的数组，每个元素是含有50个，int元素的数组 \
> 4. int *oof[3][4]; 内含3个元素的数组，每个元素是含有4个元素的数组，* 说明这4个元素是指针，int表明这4个元素是指向int的指针 \
> > oof是一个内含3个元素的数组，每个元素是有4个指向int的指针组成的数组 \
> > oof是一个3x4的二维数组，每个元素都是指向int的指针，编译器要为12个指针预留内存空间
> 5. int(*uuf)[3][4];圆括号是的*先与uuf集合，说明uuf是个指针，所以uuf是一个指向3x4的int类型二维数组的指针，编译器为一个指针预留内存空间

### example

```
//定义 用BYTE 表示1byte的数组
typedef unsigned char BYTE;
//使用BYTE定义变量
BYTE x,y[10],*z;
```

## 宏和函数选择

```
使用宏比普通函数复杂一些，一些编译器规定宏只能定义成一行，即使编译器没有这个限制也应该这么做
宏和函数的选择实际上是时间和空间的权衡
宏生成内联代码，即在程序中生成语句，如果调用20次宏，即在程序中插入20行代码
如果调用函数20次，程序中只有一份函数语句的副本，所以节省空间
另一方面，程序的控制必须跳转至函数内，随后再返回主调程序，这显然比内联代码花费更多的时间

宏的优点 不用担心变量类型，因为宏处理的是字符串，不是实际值。因此只要能用int 或float类型都可以使用SQUARE(x) 宏

注意

宏名不允许有空格，但是在替换字符串中可以有空格，ANSI C允许在参数列表中使用空格
用圆括号把宏的参数和整个替换体括起来，这样能确保被括起来的部分能正确地展开,例如有运算符时不括起来，运算优先级导致结果与预期不符
用大写字母表示宏函数的名称。该惯例不如用大写字母表示宏常量应用广泛，但是大写字母可以提醒程序员注意宏可能产生副作用

如果打算使用宏来加快程序的运行速度，那么首先要确定使用宏和使用函数是否会导致较大差异
在程序中只能使用一次的宏无法明显减少程序的运行时间
在嵌套循环中使用宏更有助于提高效率，许多系统提供程序分析器以帮助程序员压缩程序中最耗时的部分

```

## C99 内联函数

```
函数调用过程包括：建立调用、传递参数、跳转至函数代码并返回

使用宏使代码内联，可以避免上述开销

C99提供另一种方法：内联函数 inline function(把函数编程内联函数，建议尽可能快地调用该函数，其具体效果由实现定义)
把函数变成内联函数，编译器可能会用内联代码替换函数调用，并执行一些其他的优化，但是也可能不起作用

标准规定具有内部链接的函数可以称为内联函数，还规定内联函数的定义与调用该函数的代码必须在同一个文件中
最简单的使用函数说明符inline和存储类别说明符static
通常，内联函数应定义在首次使用他的文件中，所以内联函数也相当于函数原型

#include <stdio.h>
inline static void eatline()//内联函数定义/原型
{
	while(getchar()!='\n')
		continue;
}
int main(void)
{
	...
	eatline();//函数调用
	...
}
编译器查看内联函数的定义（也是原型）可能会用函数体中的代码替换eatline()函数调用
效果相当于在函数调用的位置输入函数体中的代码
int main(void)
{
	...
	//替换函数调用
	while(getchar()!='\n')
		continue;
	...
}

由于未给内联函数预留单独的代码块，所以无法获取内联函数的地址（实际上可以获得地址，不过这样做之后，编译器会生成一个非内联函数）
内联函数无法在调试器中显示

内联函数应该比较短小，较长函数变成内联函数并未节约多少时间，因为执行函数体的时间比调用函数的时间长得多

编译器优化内联函数必须知道该函数定义的内容
内联函数定义与函数调用必须在同一个文件中
一般情况内联函数都具有内部链接
如果程序有多个文件都要使用某个内联函数，那么这些文件中都必须包含该内联函数的定义
把内敛函数定义放入头文件，并在使用该内联函数的文件中包含该头文件即可

//eatline.h
#ifndef EATLINE_H_
#define EATLINE_H_
inline static void eatline()
{
	while(getchar()!='\n')
		continue;
}
#endif
一般都不在头文件中放置可执行代码，内联函数是个特例，因为内联函数具有内部链接，所以在多个文件中定义同一个内联函数不会产生问题
与C++不同，C还允许混合使用内联函数定义和外部函数定义（具有外部链接的函数定义)

//file1.c
inline static double square(double);//inline static定义 内联函数
double square(double x){return x*x;}
int main(){double q=square(1.3);}//编译器有可能优化代码，也许会内联该函数

//file2.c
double square(double x){return (int)(x*x);}//普通函数定义，具有外部链接
void spam(double v){double kv=square(v);}//square()定义具有外部链接，其他文件也可见

//file3.c
inline double square(double x){return (int)(x*x+0.5);}//inline定义，省略static
void masp(double w){double kw=square(w);}//编译器即可以使用该文件中square()函数的内联定义，也可以使用file2.c文件中的外部链接定义，
file3.c省略inline定义中的static 那么该inline定义被视为可替换的外部定义

GCC在C99之前就使用一些不同的规则实现了内联函数，所以GCC可以根据当前编译器的标记来解释inline
```

## Noreturn 函数C11

```
C99新增inline关键字，它是唯一的函数说明符（关键字extern和static是存储类别说明符，可以应用于数据对象和函数）
C11新增第二个函数说明符 _Noreturn 表明调用完后函数不返回主调函数
exit()函数是_Noreturn函数的一个示例，一旦调用exit() 它不会再返回主调函数
注意这与void返回类型不同，void类型的函数在执行完毕后返回主调函数，只是它不提供返回值
_Noreturn 的目的是告诉用户和编译器，这个特殊的函数不会把控制返回主调程序。告诉用户以免滥用该函数，通知编译器可优化一些代码
```
## C库

```
最初并没有官方的C库，后来基于UNIX的C实现成为了标准，ANSI C委员会主要以这个标准为基础，开发了一个官方的标准库
在意识到C语言的应用范围不断扩大后，该委员会重新定义了这个库，使之可以应用于其他系统

如何访问C库取决于实现，因此需要了解当前系统的一般情况，首先可以在多个不同位置找到库函数
	如 getchar()函数通常作为宏定义在 stdio.h头文件中，strlen()通常在库文件中
不同的系统搜索这些函数的方法不同
```

### 操作系统搜索函数的方法

```
1.自动访问
	一些系统，只需要编译程序就可以使用一些常用的库函数
	在使用函数之前必须先声明函数的类型，通过包含合适的头文件即可完成
	在描述库函数的用户手册中会指出使用某函数时应包含哪个头文件
	一些旧系统，可能必须自己输入函数声明
	用户手册中指明了函数类型
	不同的实现使用的头文件名不同，ANSI C标准把库函数分为多个系列，每个系列的函数原型都放在一个特定的头文件中
2.文件包含
	如果函数被定义为宏，那么可以通过#include指令包含定义宏函数的文件，通常类似的宏都放在合适名称的头文件中
	许多操作系统（包括ANSI C系统）都有ctype.h文件，该文件中包含了一些确定字符性质（大写、数组等）的宏
3.库包含
	在编译或链接程序的某个阶段，可能需要指定库选项，即使在自动检查标准库的系统中，也会有不常用的函数库
	必须通过编译时选项显示指定这些库
	注意，上述过程与包含头文件不同。头文件提供函数声明或原型，而库选项告诉系统到哪里查找函数代码。
```

### 使用库描述

```
了解函数文档
	系统的在线手册、集成开发环境的在线帮助、C实现供应商提供描述库函数的纸质版用户手册等
阅读文档的关键是看懂函数头
许多内容随时间变化而变化
```

### 数学库 math.h

> 数学库中包含许多有用的数学函数
>
> math.h头文件提供这些函数的原型
>
>注意：
> > 下表函数中设计的角度都以弧度为单位，1弧度=180/兀=57.296度 \
> > UNIX系统要求使用-lm 标记指示链接器搜索数学库,注意-lm 在命令行的末尾\
> > 因为链接器在编译器编译C文件后才开始处理. `cc xxx.c -lm`\
> > Linux写法 `gcc xxx.c -lm` 分步编译链接\
> > > `gcc -E xxx.c -o xxx.i`
> > > `gcc -S xxx.i -o xxx.s`
> > > `gcc -c xxx.s -o xxx.o`
> > > `gcc xxx.o -lm -o xxx`

|原型|描述|
|:---|:---|
|double acos(double x)|返回余弦值为x的角度（0～兀弧度）|
|double asin(double x)|返回正弦值为x的角度（-兀/2～兀/2弧度）|
|double atan(double x)|返回正切值为x的角度（-兀/2～兀/2弧度）|
|double atan2(double y,double x)|返回正弦值为y/x的角度（-兀～兀弧度）|
|double cos(double x)|返回x的余弦值，x的单位为弧度|
|double sin(double x)|返回x的正弦值，x的单位为弧度|
|double tan(double x)|返回x的正切值，x的单位为弧度|
|double exp(double x)|返回x的指数函数的值（e^x）|
|double log(double x)|返回x的自然对数值|
|double log10(double x)|返回x的以10为底的对数值|
|double pow(double x,double y)|返回x的y次幂|
|double sqrt(double x)|返回x的平方值|
|double cbrt(double x)|返回x的立方值|
|double ceil(double x)|返回不小于x的最小整数值|
|double fabs(double x)|返回x的绝对值|
|double floor(double x)|返回不大于x的最大整数值|

### tgmath.h库

> C99标准提供tgmath.h头文件中定义了泛型类型宏效果与1615xxx.c类似\
> 如果在math.h中为一个函数定义了三种类型(float,double,long double)的版本\
> 那么tgmath.h文件就创建一个泛型类型宏,与原来的double版本的函数名同名\
> 例如根据提供的参数类型定义 sqrt()宏.展开为 sqrtf() sqrt() sqrtl()函数.与1615xx.c中的SQRT()宏类似.\
> 如果编译器支持复数运算,就会支持complex.h头文件.其中声明了与复数运算相关的函数\
> 例如声明有csqrtf() csqrt() csqrtl() 这些函数分别返回 float complex,double complex,long double complex类型的复数平方根\
> 如果提供这些支持,那么tgmath.h 中的sqrt() 宏也能展开为相应的复数平方根函数.\
> 如果包含了tgmath.h 要调用sqrt()函数 而不是 sqrt()宏,可以使用圆括号吧被调用的函数名括起来\
> 类函数宏的名称必须用圆括号括起来.\
> 圆括号只会影响操作顺序,不会影响括起来的表达式,所以这样做得到的仍然是函数调用的结果\
> 实际上在讨论函数指针时,由于C语言奇怪而矛盾的函数指针规则,还可以使用(*sqrt)()的形式来调用sqrt() 函数\
> 不借助c标准以外的机制,C11新增的 _Generic 表达式是实现 tgmath.h 最简单的方式.

```
#include <tgmath.h>
...
float x=44.0;
double y;
y=sqrt(x);//调用宏,所以调用的是函数sqrtf(x)
y=(sqrt)(x);//调用函数 sqrt()
y=(*sqrt)(X);// 调用函数 sqrt()
```

## 链表和数组

| 数据形式 | 优点 | 缺点 |
|:---|:---|:---|
| 数组 | C直接支持,提供随机访问 | 编译时确定大小,插入和删除元素很费时 |
| 链表 | 运行时确定大小,快速插入和删除元素 | 不能随机访问,用户必须提供编程支持 |
| 二叉查找树 | 插入,删除,查找都比较快 | 编程更加复杂 |