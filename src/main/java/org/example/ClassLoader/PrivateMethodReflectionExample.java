package org.example.ClassLoader;
import java.lang.reflect.Method;
import java.util.Arrays;


class SecretService {

    private String getSecretCode(String user) {
        return "SECRET-" + user;
    }
}


public class PrivateMethodReflectionExample {

    public static void main(String[] args) throws Exception {

        SecretService service = new SecretService();

        // Step 1: Get Class object
        Class<?> clazz = service.getClass();

//        Method[] methods = clazz.getMethods();//only public
        Method[] methods = clazz.getDeclaredMethods();//including private

        for (Method method : methods) {
            System.out.println("Method Name==="+method);
            Class<?>[] paramTypes = method.getParameterTypes();
            System.out.println("Parameters: " + Arrays.toString(paramTypes));
        }
        // Step 2: Get private method
        Method method = clazz.getDeclaredMethod("getSecretCode", String.class);

        // Step 3: Bypass access checks
        //method.setAccessible(true);

        // Step 4: Invoke method
        Object result = method.invoke(service, "Alice");

        System.out.println(result);  // SECRET-Alice
    }
}
