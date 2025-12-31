package org.example.Abstraction;
//QUESTION

//Design a notification framework where:
//
//Different notification types exist (Email, SMS, Push)
//
//Common behavior should be reusable
//
//Multiple capabilities should be supported
//
//Backward compatibility must be preserved
//
//System should resolve method conflicts cleanly
//
//Code should be extensible



//Why this interface is INTERVIEW-GRADE
//Feature	Why
//Constants	Interfaces can hold constants
//Abstract method	Core contract
//Default method	Backward compatibility
//Static method	Utility/helper
//No state	Interface rule enforced
interface Notification {

    // Constant (implicit public static final)
    int MAX_LENGTH = 256;

    // Abstract method (implicit public)
    void send(String message);

    // Default method (Java 8+)
    default boolean validate(String message) {
        return message != null && message.length() <= MAX_LENGTH;
    }

    // Static utility method
    static void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
interface Retryable {
    default int retryCount() {
        return 3;
    }
}

interface Schedulable {
    default void schedule() {
        System.out.println("Scheduling notification");
    }
}
interface A {
    default void execute() {
        System.out.println("A");
    }
}

interface B {
    default void execute() {
        System.out.println("B");
    }
}

class ConflictNotifier implements A, B {

    @Override
    public void execute() {
        A.super.execute();  // explicit choice
        B.super.execute();
    }
}

class EmailNotification implements Notification, Retryable {

    @Override
    public void send(String message) {
        if (!validate(message)) {
            throw new IllegalArgumentException("Invalid message");
        }
        System.out.println("Sending Email: " + message);
    }
}

class SmsNotification implements Notification, Retryable, Schedulable {

    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

public class NotificationTest {
    public static void main(String[] args) {

//        Notification n1 = new EmailNotification();
//        Notification n2 = new SmsNotification();
//
//        n1.send("Hello");
//        n2.send("Hi");

        MessageFormatter formatter = msg -> "[FORMATTED] " + msg;
        System.out.println(formatter.format("Hello"));

    }
}

//FUNCTIONAL INTERFACE
//“A functional interface has exactly one abstract method and supports lambda expressions.”
@FunctionalInterface
interface MessageFormatter {
    String format(String message);

    // default methods allowed
    default String upper(String msg) {
        return msg.toUpperCase();
    }
}

//PRIVATE METHODS IN INTERFACE (JAVA 9+)
//“Private methods reduce duplication inside default methods.”
interface Secure {

    default void encryptAndSend(String msg) {
        validate(msg);
        encrypt(msg);
    }

    private void encrypt(String msg) {
        System.out.println("Encrypting message");
    }

    private void validate(String msg) {
        if (msg == null) throw new RuntimeException();
    }
}

//☠️ DEADLY INTERFACE TRAPS (MUST REMEMBER)
//❌ Trap 1: Interface can have instance variables
//
//❌ WRONG
//✅ Only constants
//
//❌ Trap 2: Static methods can be overridden
//
//❌ WRONG
//✅ They belong to interface
//
//❌ Trap 3: Default methods remove need for abstract class
//
//❌ WRONG
//✅ No state, no constructor
//
//❌ Trap 4: Functional interface = single method only
//
//❌ WRONG
//✅ One abstract method, many default/static allowed
//
//❌ Trap 5: Interface can extend class
//
//❌ WRONG
//✅ Interface extends interface only
//
//🎯 30-SECOND INTERVIEW SUMMARY (MEMORIZE)
//
//“An interface defines a contract and supports multiple inheritance.
//Since Java 8, interfaces can have default and static methods for backward compatibility.
//Conflicting default methods must be resolved explicitly.
//Interfaces cannot hold state or constructors and are ideal for defining capabilities.”
