package com.github.ba.createcode;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Properties;

/**
 * @author wang xiao
 * date 2022/11/11
 */
public class TemplateCreateCode {
    public static void main(String[] args) {
        initVelocity();
        String template = "domain.java.vm";
        VelocityContext context = new VelocityContext();
        context.internalPut("packageName","com.github.ba.createcode");
        context.internalPut("ClassName","TestCode");
        context.internalPut("Entity","BaseCode");
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "utf-8");
        tpl.merge(context, sw);
        System.out.println(sw);
    }

    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
