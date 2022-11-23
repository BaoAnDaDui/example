package com.github.ba.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;

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
        context.eval("js", "var name = Polyglot.import('name');");
        context.eval("js", "console.log(name.getName())");


    }
}
