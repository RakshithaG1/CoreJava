# Day 12 – Reflection API & ClassLoader

## One-Page (Slightly Detailed) Interview Cheat Sheet

---

## 🔹 Reflection – Core Idea

**Reflection** allows Java programs to **inspect and manipulate classes, fields, methods, constructors, and annotations at runtime**,
even when their names are not known at compile time.

**Why it exists:**

* Breaks compile-time coupling
* Enables frameworks to work generically

**Used in:** Spring, Hibernate/JPA, Jackson, JUnit, Mockito

**Key Risk:** Slower, breaks encapsulation, runtime errors

---

## 🔹 ClassLoader – Purpose

A **ClassLoader** loads `.class` bytecode into JVM memory and creates `Class<?>` objects.

> One class loaded by two different ClassLoaders = **two different classes** in JVM.

---

## 🔹 ClassLoader Types (Very Important)

### Java 8 and Earlier

```
Bootstrap → Extension → Application
```

### Java 9+

```
Bootstrap → Platform → Application
```

### 1️⃣ Bootstrap ClassLoader

* Loads core JDK classes (`java.lang`, `java.util`)
* Implemented in native code (C/C++)
* `getClassLoader()` → `null`

### 2️⃣ Platform ClassLoader (Java 9+)

* Replaces Extension ClassLoader
* Loads Java SE platform modules (`java.sql`, `java.xml`)

### 3️⃣ Application (System) ClassLoader

* Loads application & user classes from classpath/module path
* Most commonly used

---

## 🔹 ClassLoader Delegation Model

**Parent-first delegation:**

1. Application ClassLoader
2. Platform / Extension ClassLoader
3. Bootstrap ClassLoader

**Why delegation exists:**

* Security (cannot override core classes)
* Avoids duplicate class loading
* Ensures consistency

---

## 🔹 Getting `Class` Objects (4 Ways)

```java
String.class
Class.forName("java.lang.String")
obj.getClass()
ClassLoader.loadClass("java.lang.String")
```

✔ All return the same `Class` object

---

## 🔹 forName() vs loadClass() (Interview Favorite)

| Feature                     | Class.forName() | loadClass() |
| --------------------------- | --------------- | ----------- |
| Loads class                 | ✅               | ✅           |
| Initializes (static blocks) | ✅               | ❌           |

> Initialization = static fields + static blocks

---

## 🔹 Inspecting Class Metadata

Common APIs:

* `getName()` → fully qualified name
* `getSimpleName()` → class name only
* `getSuperclass()`
* `getInterfaces()`
* `getModifiers()`
* `isInterface()`, `isEnum()`, `isPrimitive()`

**Rule:**

* `getDeclaredXxx()` → includes private members
* `getXxx()` → public only (including inherited)

---

## 🔹 Inspecting & Modifying Fields

```java
Field f = clazz.getDeclaredField("name");
f.setAccessible(true);
f.get(obj);
f.set(obj, value);
```

⚠ `setAccessible(true)`:

* Bypasses access checks
* Restricted in Java 17+ (strong encapsulation)

---

## 🔹 Inspecting & Invoking Methods

```java
Method m = clazz.getDeclaredMethod("add", int.class, int.class);
m.invoke(obj, 5, 3);
```

* Exceptions wrapped in `InvocationTargetException`
* Method resolution happens at runtime

---

## 🔹 Constructors via Reflection

```java
Constructor<?> c = clazz.getDeclaredConstructor(String.class, int.class);
c.newInstance("A", 10);
```

❌ `Class.newInstance()` → deprecated

---

## 🔹 Annotations + Reflection

**Mandatory for reflection:**

```java
@Retention(RetentionPolicy.RUNTIME)
```

Used heavily by:

* Spring (`@Autowired`, `@Component`)
* JPA (`@Entity`, `@Column`)

---

## 🔹 Real-World Pattern: ORM

Reflection + Annotations → map:

```
Class ↔ Table
Field ↔ Column
Object ↔ Row
```

Hibernate internally:

* Scans annotations
* Accesses private fields
* Generates SQL dynamically

---

## 🔹 Performance Considerations

* Reflection is **~10–100x slower** than direct calls
* Causes:

    * Runtime method lookup
    * Security checks

**Optimizations:**

* Cache `Method`, `Field`, `Constructor`
* Avoid in tight loops
* Prefer MethodHandles for performance

---

## 🔹 Reflection-based DI (Mini Spring)

* Scan fields
* Detect `@Inject`
* Create dependencies dynamically

➡ This is how **Spring DI container** works internally (simplified)

---

## 🔥 Must-Answer Interview Questions

**Q: Why is reflection slower?**
Runtime lookup, access checks, no compile-time binding

**Q: How does Spring reduce reflection cost?**
Startup-time scanning, caching, proxies, bytecode generation

**Q: What is ClassLoader delegation?**
Parent-first loading to ensure security and consistency

**Q: Can reflection break encapsulation?**
Yes, by accessing private members

**Q: Circular dependency possible?**
Yes → may cause `ClassCircularityError` during initialization

---

## 🧠 One-Line Memory Hook

> **Reflection = Runtime Introspection | ClassLoader = Bytecode Loader | Delegation = Security Net**

---

✅ Use this sheet for **last-day revision + interviews**