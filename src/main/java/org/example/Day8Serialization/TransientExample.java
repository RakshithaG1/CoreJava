package org.example.Day8Serialization;

import java.io.*;

class SecureUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    // NOT serialized (sensitive data)
    private transient String password;

    // NOT serialized (derived/temporary)
    private transient boolean loggedIn;

    public SecureUser(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.loggedIn = false;
    }

    public String toString() {
        return "SecureUser{" + "name='" + name + '\'' +
                ", age=" + age + ", password='" +
                (password == null ? "null" : "***") + '\'' + '}';
    }
}

public class TransientExample {
    public static void main(String[] args) throws Exception {
        SecureUser user = new SecureUser("Bob", 30, "secret123");
        System.out.println("Original: " + user);  // password=***

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("secure_user.dat"))) {
            oos.writeObject(user);
        }

        // Deserialize
        SecureUser loaded;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("secure_user.dat"))) {
            loaded = (SecureUser) ois.readObject();
        }

        System.out.println("Loaded: " + loaded);
        // password=null (transient field not restored!)
    }
}