# constant_pool_info

> The constant_pool is a table of structures (§4.4) \
representing various string constants, \
class and interface names, field names, \
and other constants that are referred to within the ClassFile structure and its substructures.\
The format of each constant_pool table entry is indicated by its ***first "tag" byte***.

> The constant_pool table is indexed from ***1 to constant_pool_count - 1***.

> CONSTANT_Utf8_info
> > u1 0x01 \
> > u2 length \
> > u1 bytes[length]
> 
> CONSTANT_Integer_info
> > u1 0x03 \
> > u4 bytes
> 
> CONSTANT_Float_info
> > u1 0x04 \
> > u4 bytes
> 
> CONSTANT_Long_info
> > u1 0x05 \
> > u4 high_bytes \
> > u4 low_bytes
> 
> CONSTANT_Double_info
> > u1 0x06 \
> > u4 high_bytes \
> > u4 low_bytes
> 
> CONSTANT_Class_info
> > u1 0x07 \
> > u2 name_index
> 
> CONSTANT_String_info
> > u1 0x08 \
> > u2 string_index
> 
> CONSTANT_Fieldref_info
> > u1 0x09 \
> > u2 class_index \
> > u2 name_and_type_index
> 
> CONSTANT_Methodref_info
> > u1 0x0A \
> > u2 class_index \
> > u2 name_and_type_index
> 
> CONSTANT_InterfaceMethodref_info
> > u1 0x0B \
> > u2 class_index \
> > u2 name_and_type_index
> 
> CONSTANT_NameAndType_info
> > u1 0x0C \
> > u2 name_index \
> > u2 descriptor_index
> 
> CONSTANT_MethodHandle_info
> > u1 0x0F \
> > u1 reference_kind \
> > u2 reference_index
> 
> CONSTANT_MethodType_info
> > u1 0x10 \
> > u2 descriptor_index
> 
> CONSTANT_InvokeDynamic_info
> > u1 0x12 \
> > u2 bootstrap_method_attr_index \
> > u2 name_and_type_index

[:anchor:](ClassFile.md)