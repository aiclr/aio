package org.bougainvilleas.ilj.agent;

import java.lang.instrument.Instrumentation;
import java.util.Objects;

public class JavaAgent {

    public static void premain(String var0, Instrumentation var1) {
        System.err.println("TTAgent#premain(String,Instrumentation)");
        parseAgentArgs(var0);
        var1.addTransformer(new JavaClassFileTransformer());
    }

    public static void premain(String var0) {
        System.err.println("TTAgent#premain(String)");
        parseAgentArgs(var0);
    }

    public static void agentmain(String var0, Instrumentation var1) {
        System.err.println("TTAgent#agentmain(String,Instrumentation)");
        parseAgentArgs(var0);
    }

    public static void agentmain(String var0) {
        System.err.println("TTAgent#agentmain(String)");
        parseAgentArgs(var0);
    }

    private static boolean parseAgentArgs(String var0) {
        boolean var1 = false;
        if (Objects.nonNull(var0) && !var0.isEmpty()) {
            System.err.println("agentArgs is : " + var0);
            var1 = true;
        } else {
            System.err.println("has no agentArgs !");
        }

        return var1;
    }
}
