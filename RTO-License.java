// # 🚗 Java RTO License Eligibility System
// This is a fun and educational mini project in Java where a user tries to apply for a driving license.  
// Eligibility is checked based on age and handled using custom exceptions.

// ## ✅ Features
// - 3 attempts to enter correct age
// - Custom Exceptions for:
//   - Underage (age < 18)
//   - Overage (age > 70)
// - Realistic console messages
// - Clean exception handling structure

// ## 🧠 Concepts Used
// - `try-catch` and nested exception handling
// - Custom exceptions in Java
// - Simple input validation
// - Clean console interaction

// ## ▶️ Run the project
// ```bash
// javac LaunchRTO.java
// java LaunchRTO


package ExceptionHandling;

import java.util.Scanner;

// ✅ Custom Exception for age < 18
class UnderageException extends Exception {
    public UnderageException(String msg) {
        super(msg);
    }
}

// ✅ Custom Exception for age > 70
class OverageException extends Exception {
    public OverageException(String msg) {
        super(msg);
    }
}

// ✅ Applicant class to take input and verify eligibility
class Applicant {
    int age;

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your age (Sach batao 😄): ");
        age = sc.nextInt();
    }

    public void verify() throws UnderageException, OverageException {
        if (age < 18) {
            UnderageException ue = new UnderageException("❌ This is not for below 18 ones.");
            System.out.println(ue.getMessage());
            throw ue;
        } else if (age > 70) {
            OverageException oe = new OverageException("❌ This is not for older ones 🙏");
            System.out.println(oe.getMessage());
            throw oe;
        } else {
            System.out.println("✅ You are welcome! You got a license🎉");
        }
    }
}

// ✅ Main Controller simulating an RTO office (with 3 attempts)
class RTO {
    public void initiate() {
        Applicant app = new Applicant();

        // 🔁 First attempt
        try {
            app.input();
            app.verify();
        } catch (UnderageException | OverageException e1) {
            // 🔁 Second attempt
            try {
                app.input();
                app.verify();
            } catch (UnderageException | OverageException e2) {
                // 🔁 Third attempt
                try {
                    app.input();
                    app.verify();
                } catch (UnderageException | OverageException e3) {
                    System.out.println("🚫 You have failed multiple times. First learn, then apply 😄");
                    System.exit(0); // exit after 3 failed attempts
                }
            }
        }
    }
}

// ✅ Main class to launch the simulation
public class LaunchRTO {
    public static void main(String[] args) {
        RTO rto = new RTO();
        rto.initiate();
    }
}
