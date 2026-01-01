package org.example.ClassLoader;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// Annotation for injection
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Inject {
}

// Services
interface UserService {
    void createUser(String name);
}

class UserServiceImpl implements UserService {
    @Override
    public void createUser(String name) {
        System.out.println("Creating user: " + name);
    }
}
class UserController {
    @Inject
    private UserService userService;

    public void handleRequest(String name) {
        if (userService != null) {
            userService.createUser(name);
        }
    }
}

// Simple DI Container using Reflection
public class DIContainer {

    public static <T> T create(Class<T> clazz) {
        try {
            // Create instance
            T instance = clazz.newInstance();
            System.out.println("Class To be created =="+instance.getClass().getName());

            // Inject dependencies
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    System.out.println("Objects To be set/injected to the class =="+field.getName());
                    System.out.println("Objects To be set/injected to the class Type =="+field.getType());

                    // Create and inject service
                    Object service = createService(field.getType());
                    field.set(instance, service);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object createService(Class<?> type) {
        if (type == UserService.class) {
            return new UserServiceImpl();
        }
        throw new RuntimeException("Unknown service: " + type);
    }

    public static void main(String[] args) {
        UserController controller = DIContainer.create(UserController.class);
        controller.handleRequest("Alice");  // Creating user: Alice
    }
}