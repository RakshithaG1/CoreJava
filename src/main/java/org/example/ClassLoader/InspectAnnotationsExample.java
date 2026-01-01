package org.example.ClassLoader;

import java.lang.annotation.*;
import java.lang.reflect.*;

// Define custom annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Deprecated2 {
    String reason() default "";
    String since() default "";
}

public class InspectAnnotationsExample {

    @Deprecated2(reason = "Use newMethod instead", since = "1.5")
    public void oldMethod() {
    }

    @Override
    public String toString() {
        return "Example";
    }

    public static void main(String[] args) {
        Class<?> exampleClass = InspectAnnotationsExample.class;

        // Get all annotations
        Annotation[] annotations = exampleClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("Annotation: " + annotation);
        }

        // Get specific annotation
        try {
            Method oldMethod = exampleClass.getDeclaredMethod("oldMethod");
            Deprecated2 deprecated = oldMethod.getAnnotation(Deprecated2.class);

            if (deprecated != null) {
                System.out.println("Reason: " + deprecated.reason());
                System.out.println("Since: " + deprecated.since());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
