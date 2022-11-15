package com.github.ba.javanode;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.List;

/**
 * @author wang xiao
 * date 2022/11/15
 */
public class JavaNodeTest {

    public static void main(String[] args) {

        String compilerCode = "public class De{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}\n";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileObject javaFileObject = new SourceJavaObj("De",compilerCode);
        Boolean result = compiler.getTask(null, null, null, null, null, List.of(javaFileObject)).call();
    }

}
