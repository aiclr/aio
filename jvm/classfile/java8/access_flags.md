# [access_flags](ClassFile.md)

| flag name      | value  | interpretation                                                                                                       |
|:---------------|:-------|:---------------------------------------------------------------------------------------------------------------------|
| ACC_PUBLIC     | 0x0001 | Declared public; may be accessed from outside its package.                                                           |
| ACC_FINAL      | 0x0010 | Declared final; no subclasses allowed.                                                                               |
| ACC_SUPER      | 0x0020 | Treat *(处理)* superclass methods specially *(专门地)* when invoked *(调用)* by the ***invokespecial*** instruction *(指令)*. |
| ACC_INTERFACE  | 0x0200 | Is an interface, not a class.                                                                                        |
| ACC_ABSTRACT   | 0x0400 | Declared abstract; must not be instantiated *(实例化)*.                                                                 |
| ACC_SYNTHETIC  | 0x1000 | Declared synthetic; not present *(存在)* in the source code.                                                           |
| ACC_ANNOTATION | 0x2000 | Declared as an annotation type.                                                                                      |
| ACC_ENUM       | 0x4000 | Declared as an enum type.                                                                                            |


> An interface is distinguished by the ACC_INTERFACE flag being set.
> > If the ACC_INTERFACE flag is not set
> > > this class file defines a class, not an interface.\
> > > any of the other flags in Table access_flags may be set except ACC_ANNOTATION\
> > > However, such a class file must not have both its ACC_FINAL and ACC_ABSTRACT flags set (JLS §8.1.1.2).
> >
> > If the ACC_INTERFACE flag is set
> > > the ACC_ABSTRACT flag must also be set \
> > > the ACC_FINAL, ACC_SUPER, and ACC_ENUM flags set must not be set.

> The ACC_SUPER flag indicates *(表明)* which of two alternative *(可供替代的)* semantics *(语义)* is to be expressed *(表达)* by the **invokespecial** instruction *(指令)* ([§invokespecial](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokespecial)) if it appears in this class or interface.\
> Compilers *(编译器)* to the instruction *(指令)* set *(集合)* of the Java Virtual Machine should set the ACC_SUPER flag.\
> In Java SE 8 and above *(以上)*,\
> the Java Virtual Machine considers *(为做出决定仔细考虑)* the ACC_SUPER flag to be set in every class file,\
> regardless *(不管)* of the actual value of the flag in the class file and the version of the class file.

> The ACC_SUPER flag exists for backward *(向后的)* compatibility *(兼容性)* with code compiled by older compilers for the Java programming language.\
> In JDK releases prior *(较早的)* to 1.0.2, the compiler generated *(产生)* access_flags in which the flag now representing *(代表)* ACC_SUPER had no assigned *(指定)* meaning,\
> and Oracle's Java Virtual Machine implementation ignored the flag if it was set.

> The ACC_SYNTHETIC flag indicates *(标示)* that this class or interface was generated *(生成)* by a compiler *(编译器)* and does not appear in source code.

> An annotation type must have its ACC_ANNOTATION flag set.\
> If the ACC_ANNOTATION flag is set, the ACC_INTERFACE flag must also be set.

> The ACC_ENUM flag indicates that this class or its superclass is declared as an enumerated type.

> All bits of the access_flags item not assigned *(指定)* in Table access_flags are reserved *(保留)* for future use.\
> They should be set to zero in generated *(生成)* class files and should be ignored by Java Virtual Machine implementations.