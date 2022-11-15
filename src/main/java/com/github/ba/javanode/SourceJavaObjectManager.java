package com.github.ba.javanode;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;


/**
 * @author wang xiao
 * date 2022/11/15
 */
public class SourceJavaObjectManager extends ForwardingJavaFileManager<JavaFileManager> {

    private SourceJavaObj javaObj;

    public SourceJavaObjectManager(JavaFileManager fileManager, SourceJavaObj javaObj) {
        super(fileManager);
        this.javaObj = javaObj;
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        javaObj= new SourceJavaObj (className,className);
        return javaObj;
    }
}
