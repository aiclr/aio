package org.bougainvilleas.ilj.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class JavaClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(className.startsWith("org/bougainvilleas/java/"))
        {
            byte[] decode=new byte[classfileBuffer.length];
            System.err.println(className);

            for (int i = 0; i < classfileBuffer.length; i++)
            {
                System.err.print(Integer.toString(Byte.toUnsignedInt(classfileBuffer[i]),16));
                decode[i]= (byte) (classfileBuffer[i]^1);
                System.err.print(Integer.toString(Byte.toUnsignedInt(decode[i]),16));
            }
            return decode;
        }else
        {
            return null;
        }
    }
}
