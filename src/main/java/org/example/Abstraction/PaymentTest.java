package org.example.Abstraction;
//🔹 Why This Abstract Class Is POWERFUL (Interview Explanation)
//Feature	Why it exists
//Constructor	Initializes common state
//Protected fields	Accessible by subclasses
//Final method	Prevents breaking flow
//Abstract methods	Forces specialization
//Concrete method	Code reuse
//Static method	Utility logic
//Template pattern	Fixed algorithm structure

//Template Method Pattern
//Definition:
//Defines algorithm skeleton in base class and allows subclasses to fill specific steps.
//“Template method prevents subclasses from breaking business flow.”
abstract class Payment {

    protected final String paymentId;   // state
    protected double amount;

    // Constructor (used by child classes)
    protected Payment(String paymentId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.paymentId = paymentId;
        this.amount = amount;
    }

    // TEMPLATE METHOD (cannot be overridden)
    public final void processPayment() {
        validate();
        authenticate();
        executePayment();
        generateReceipt();
    }

    // Concrete method (shared logic)
    protected void validate() {
        System.out.println("Validating payment: " + paymentId);
    }

    // Abstract methods (forced to implement)
    protected abstract void authenticate();
    protected abstract void executePayment();

    // Optional override
    protected void generateReceipt() {
        System.out.println("Generating default receipt");
    }

    // Static utility method
    public static boolean isValidCurrency(String currency) {
        return currency.equals("INR") || currency.equals("USD");
    }
}

class CreditCardPayment extends Payment {

    private String cardNumber;

    public CreditCardPayment(String id, double amount, String cardNumber) {
        super(id, amount);  // constructor chaining
        this.cardNumber = cardNumber;
    }

    @Override
    protected void authenticate() {
        System.out.println("Authenticating credit card: " + cardNumber);
    }

    @Override
    protected void executePayment() {
        System.out.println("Processing credit card payment of " + amount);
    }

    @Override
    protected void generateReceipt() {
        System.out.println("Generating credit card receipt");
    }
}

class UpiPayment extends Payment {

    private String upiId;

    public UpiPayment(String id, double amount, String upiId) {
        super(id, amount);
        this.upiId = upiId;
    }

    @Override
    protected void authenticate() {
        System.out.println("Authenticating UPI: " + upiId);
    }

    @Override
    protected void executePayment() {
        System.out.println("Processing UPI payment of " + amount);
    }
}


//“Payment is an abstract class that holds common state and behavior.
//The final processPayment method defines the workflow, while subclasses provide specific implementations.
//This uses the Template Method pattern to enforce consistency and enable polymorphism.”
public class PaymentTest {
    public static void main(String[] args) {

        Payment p1 = new CreditCardPayment("P1001", 5000, "1234-5678");
        Payment p2 = new UpiPayment("P1002", 1500, "user@upi");

        p1.processPayment();
        System.out.println("-----");
        p2.processPayment();
    }
}

//What Interviewer Is Testing Here
//1️⃣ Can abstract class hold state?
//
//✔ YES
//
//2️⃣ Can it have constructor?
//
//✔ YES
//
//3️⃣ Can it enforce flow?
//
//✔ YES (final template method)
//
//4️⃣ Can it mix abstract & concrete methods?
//
//✔ YES
//
//5️⃣ Can child override some behavior?
//
//✔ YES (generateReceipt)
//
//6️⃣ Can static methods exist?
//
//✔ YES (utility)
//
//☠️ INTERVIEW TRAPS (VERY IMPORTANT)
//❌ Trap 1: Overriding final method
//@Override
//public void processPayment() {} // ❌ NOT ALLOWED
//
//❌ Trap 2: Forgetting super()
//public CreditCardPayment(...) {
//    // ❌ parent state not initialized
//}
//
//❌ Trap 3: Making fields public
//
//Breaks encapsulation → reject
//
//❌ Trap 4: Calling abstract method from constructor
//
//🚨 Dangerous — child not initialized yet
//
//🧠 Design Pattern Used (Interview GOLD)
//
//Template Method Pattern
//
//Definition:
//Defines algorithm skeleton in base class and allows subclasses to fill specific steps.
//
//🔥 Interview bonus line:
//
//“Template method prevents subclasses from breaking business flow.”
//
//
//“Payment is an abstract class that holds common state and behavior.
//The final processPayment method defines the workflow, while subclasses provide specific implementations.
//This uses the Template Method pattern to enforce consistency and enable polymorphism.”