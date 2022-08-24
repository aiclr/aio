# [constant_pool_info](ClassFile.md)

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