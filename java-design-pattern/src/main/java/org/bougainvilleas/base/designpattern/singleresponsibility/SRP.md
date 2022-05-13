# Single Responsibility Principle

> There should never be more than one reason for a class to change
> > 应该有且仅有一个原因引起类的变更
>
> 好处
> > 类的复杂性降低，实现什么职责都有清晰明确的定义
> > 可读性提高
> > 可维护性提高
>
> 名词
> >  BO
> > > Business Object 业务对象
> >
> >  Biz
> > > Business Logic 业务逻辑
> >
> >  VO
> > > Value Object 值对象
>
> 注意
> > 单一职责原则提出了一个编写程序的标准 \
> > 用“职责”或“变化原因”来衡量接口或类设计得是否优良 \
> > 但是“职责”和“变化原因”都是不可度量的，因项目而异，因环境而异 \
> > 接口一定要做到单一职责，类的设计尽量做到只有一个原因引起变化