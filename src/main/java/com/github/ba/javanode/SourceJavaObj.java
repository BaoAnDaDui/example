package com.github.ba.javanode;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

/**
 * @author wang xiao
 * date 2022/11/15
 */
public class SourceJavaObj extends SimpleJavaFileObject {

    private final String sourceJavaCode;

    public SourceJavaObj(String name,String sourceJavaCode) {
        super(URI.create("string:///" + name + Kind.SOURCE.extension),Kind.SOURCE);
        this.sourceJavaCode = sourceJavaCode;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        if (Objects.isNull(sourceJavaCode)){
            throw new IllegalArgumentException("sourceJavaCode is null");
        }
        return sourceJavaCode;
    }
}
