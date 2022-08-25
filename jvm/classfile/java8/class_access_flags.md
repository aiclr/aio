# class_access_flags

> Class access property modifiers

| flag name      | value  | interpretation                                                                                                                                   |
|:---------------|:-------|:-------------------------------------------------------------------------------------------------------------------------------------------------|
| ACC_PUBLIC     | 0x0001 | Declared public; may be accessed from outside its package.                                                                                       |
| ACC_FINAL      | 0x0010 | Declared final; no subclasses allowed.                                                                                                           |
| ACC_SUPER      | 0x0020 | Treat <sub>处理</sub> superclass methods specially <sub>专门地</sub> when invoked <sub>调用</sub> by the ***invokespecial*** instruction <sub>指令</sub>. |
| ACC_INTERFACE  | 0x0200 | Is an interface, not a class.                                                                                                                    |
| ACC_ABSTRACT   | 0x0400 | Declared abstract; must not be instantiated <sub>实例化</sub>.                                                                                      |
| ACC_SYNTHETIC  | 0x1000 | Declared synthetic; not present <sub>存在</sub> in the source code.                                                                                |
| ACC_ANNOTATION | 0x2000 | Declared as an annotation type.                                                                                                                  |
| ACC_ENUM       | 0x4000 | Declared as an enum type.                                                                                                                        |


> An interface is distinguished by the ACC_INTERFACE flag being set.
> > If the ACC_INTERFACE flag is not set
> > > this class file defines a class, not an interface.\
any of the other flags in Table access_flags may be set except ACC_ANNOTATION \
However, such a class file must not have both its ACC_FINAL and ACC_ABSTRACT flags set (JLS §8.1.1.2).
> >
> > If the ACC_INTERFACE flag is set
> > > the ACC_ABSTRACT flag must also be set \
the ACC_FINAL, ACC_SUPER, and ACC_ENUM flags set must not be set.

> The ACC_SUPER flag indicates <sub>表明</sub> which of two alternative <sub>可供替代的</sub> semantics <sub>语义</sub> is to be expressed <sub>表达</sub> by the **invokespecial** instruction <sub>指令</sub> ([§invokespecial](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokespecial)) if it appears in this class or interface.\
Compilers <sub>编译器</sub> to the instruction <sub>指令</sub> set <sub>集合</sub> of the Java Virtual Machine should set the ACC_SUPER flag.\
In Java SE 8 and above <sub>以上</sub>,\
the Java Virtual Machine considers <sub>为做出决定仔细考虑</sub> the ACC_SUPER flag to be set in every class file,\
regardless <sub>不管</sub> of the actual value of the flag in the class file and the version of the class file.

> The ACC_SUPER flag exists for backward <sub>向后的</sub> compatibility <sub>兼容性</sub> with code compiled by older compilers for the Java programming language.\
In JDK releases prior <sub>较早的</sub> to 1.0.2, the compiler generated <sub>产生</sub> access_flags in which the flag now representing <sub>代表</sub> ACC_SUPER had no assigned <sub>指定</sub> meaning,\
and Oracle's Java Virtual Machine implementation ignored the flag if it was set.

> The ACC_SYNTHETIC flag indicates <sub>标示</sub> that this class or interface was generated <sub>生成</sub> by a compiler <sub>编译器</sub> and does not appear in source code.

> An annotation type must have its ACC_ANNOTATION flag set.\
If the ACC_ANNOTATION flag is set, the ACC_INTERFACE flag must also be set.

> The ACC_ENUM flag indicates that this class or its superclass is declared as an enumerated type.

> All bits of the access_flags item not assigned <sub>指定</sub> in Table access_flags are reserved <sub>保留</sub> for future use.\
They should be set to zero in generated <sub>生成</sub> class files and should be ignored by Java Virtual Machine implementations.

[:back:](ClassFile.md)