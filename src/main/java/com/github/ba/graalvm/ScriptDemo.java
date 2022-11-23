package com.github.ba.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;

/**
 * @author wang xiao
 * date 2022/11/23
 */
public class ScriptDemo {

    public static class Name {
         public String name = "hello";


        public String getName() {
            return name;
        }
    }






    public static void main(String[] args) {
        Name n = new Name();
        Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .allowIO(true)
                .allowPolyglotAccess(PolyglotAccess.ALL)
                .build();
        context.getPolyglotBindings().putMember("name", n);
        Value js = context.eval("js", "var name = Polyglot.import('name');");

        System.out.println(js);
        context.eval("js", "console.log(name.getName())");


    }
}
