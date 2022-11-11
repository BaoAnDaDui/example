package com.github.ba.createcode;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;

/**
 * @author wang xiao
 * date 2022/11/11
 */
public class JavaCompileCreateCode {

    public static void main(String[] args) {
        try {
            // 定义一个方法名为test的方法
            MethodSpec test = MethodSpec.methodBuilder("test1")
                    // 方法的修饰符
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    // 方法的返回值类型
                    .returns(void.class)
                    // 方法的参数
                    .addParameter(Integer.class, "loop")
                    // 方法body内容
                    .addCode(""
                            + "int total = 0;\n"
                            + "for (int i = 0; i < loop; i++) {\n"
                            + "  total += i;\n"
                            + "}\n"
                            + "System.out.println(\"total value: \" + total);\n")
                    .build();

            // 定义一个类，名字为TestCode
            TypeSpec testCode = TypeSpec.classBuilder("TestCode")
                    // 类修饰符
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    // 添加方法
                    .addMethod(test)
                    .build();

            // 定义一个java文件，指定package和类定义
            JavaFile javaFile = JavaFile.builder("com.javatest.javapoet", testCode)
                    .build();

            // 将java文件内容写入文件中
            File file = new File("./javapoet");
            javaFile.writeTo(file);
        } catch (Exception e) {
            //
        }
    }

}


