# :world_map:

- [ClassFile structure](#ClassFile-structure)
- [magic](#magic)
- [minor_version,major_version](#minor_versionmajor_version)
- [this_class](#this_class)
- [super_class](#super_class)

# ClassFile structure

| size                                | interpretation                        |
|:------------------------------------|:--------------------------------------|
| u4                                  | magic                                 |
| u2                                  | minor_version                         |
| u2                                  | major_version                         |
| u2                                  | constant_pool_count                   |
| [cp_info](constant_pool_info.md)    | constant_pool[constant_pool_count]    |
| u2                                  | [access_flags](class_access_flags.md) |
| u2                                  | this_class                            |
| u2                                  | super_class                           |
| u2                                  | interfaces_count                      |
| u2                                  | interfaces[interfaces_count]          |
| u2                                  | fields_count                          |
| [field_info](field_info.md)         | fields[fields_count]                  |
| u2                                  | methods_count                         |
| [method_info](method_info.md)       | methods[methods_count]                |
| u2                                  | attributes_count                      |
| [attribute_info](attribute_info.md) | attributes[attributes_count]          |

# magic

> The magic item supplies the magic number identifying the class file format;\
> it has the value **0xCAFEBABE** . [:back:](#world_map)

# minor_version,major_version

> all in one: **k ≥ 2, 1.K = 44 + K.0 = version**
> > 1.8 = 44 + 8.0 = 52.0\
1.6 = 44 + 6.0 = 50.0 [:back:](#world_map)

<details><summary>CLICK ME</summary>

> The values of the minor_version and major_version items are the minor and major version numbers of this class file.\
Together, a major and a minor version number determine the version of the class file format.\
If a class file has major version number M and minor version number m, we denote the version of its class file format as M.m.\
Thus, class file format versions may be ordered lexicographically, for example, 1.5 < 2.0 < 2.1.

> A Java Virtual Machine implementation can support a class file format of version v if and only if v lies in some contiguous range Mi.0 ≤ v ≤ Mj.m.\
The release level of the Java SE platform to which a Java Virtual Machine implementation conforms is responsible for determining the range.

> Oracle's Java Virtual Machine implementation in JDK release 1.0.2 supports class file format versions 45.0 through 45.3 inclusive.\
JDK releases 1.1.* support class file format versions in the range 45.0 through 45.65535 inclusive.\
**For k ≥ 2, JDK release 1.k supports class file format versions in the range 45.0 through 44+k.0 inclusive.**
</details>

# this_class

> The value of the ***this_class*** item must be a valid<sub>有效的</sub> index into the constant_pool table.\
The constant_pool entry at that index must be a **CONSTANT_Class_info** structure <sub>结构</sub> ([§constant_pool_info](constant_pool_info.md)) \
representing <sub>意味着</sub> the class or interface defined<sub>定义</sub>  by this class file. [:back:](#world_map)

# super_class

> either... or... <sub>不是...就是... , 或者... 或者... , 表示两者之一 连接句子中两个并列成分</sub>\
neither... nor... <sub>既不... 也不...</sub>

> For a class, the value of the super_class item either must be zero or must be a valid <sub>有效的</sub> index into the constant_pool table.\
If the value of the super_class item is nonzero,\
the constant_pool entry at that index must be a CONSTANT_Class_info structure <sub>结构</sub> representing <sub>意味着</sub> the direct <sub>直接的</sub> superclass of the class defined by this class file.\
Neither the direct superclass nor any of its superclasses may have the ACC_FINAL flag set <sub>设置</sub> in the access_flags item of its ClassFile structure.<sub>直接 superclass 及其它任何 superclasses 都不能在其类文件结构的access_flags 项中设置 ACC_FINAL 标示</sub>

> If the value of the super_class item is zero, then this class file must represent <sub>等于</sub> the class Object, the only class or interface without a direct <sub>直接的</sub> superclass.

> For an interface, the value of the super_class item must always be a valid <sub>有效的</sub> index into the constant_pool table.\
The constant_pool entry at that index must be a CONSTANT_Class_info structure representing <sub>代表</sub> the class Object. [:back:](#world_map)

[:anchor:](../../README.md)