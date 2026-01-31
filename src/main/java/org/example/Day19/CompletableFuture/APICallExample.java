package org.example.Day19.CompletableFuture;

import java.util.concurrent.CompletableFuture;
//Key Method:
//thenCompose() - Chain async operations (flatMap equivalent)
public class APICallExample {

    static class User {
        String id;
        String name;
        String departmentId;

        User(String id, String name, String departmentId) {
            this.id = id;
            this.name = name;
            this.departmentId = departmentId;
        }

        @Override
        public String toString() {
            return "User{" + "id='" + id + "', name='" + name +
                    "', departmentId='" + departmentId + "'}";
        }
    }

    static class Department {
        String id;
        String name;
        String location;

        Department(String id, String name, String location) {
            this.id = id;
            this.name = name;
            this.location = location;
        }

        @Override
        public String toString() {
            return "Department{" + "id='" + id + "', name='" + name +
                    "', location='" + location + "'}";
        }
    }

    // Simulated API calls
    static CompletableFuture<User> fetchUser(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            System.out.println("Fetched user");
            return new User("1", "Alice", "dept-1");
        });
    }

    static CompletableFuture<Department> fetchDepartment(String deptId) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(300); } catch (InterruptedException e) {}
            System.out.println("Fetched department");
            return new Department("dept-1", "Engineering", "Building A");
        });
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<User> userFuture = fetchUser("user-1");

        CompletableFuture<String> result = userFuture.thenCompose(user ->
                // Use user to fetch department
                fetchDepartment(user.departmentId).thenApply(dept ->
                        user.name + " works in " + dept.name + " at " + dept.location
                )
        );

        System.out.println(result.get());

        long duration = System.currentTimeMillis() - start;
        System.out.println("Time: " + duration + "ms");  // ~800ms (parallel)
    }
}
