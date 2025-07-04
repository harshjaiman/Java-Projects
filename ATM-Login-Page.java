// # 🏦 Java ATM Login System
// A mini project built in Java to simulate a simple ATM login flow with:
// - Custom Exceptions
// - BufferedReader and try-with-resources
// - Nested try-catch for retry logic (3 attempts max)
// - Clean and readable structure

// ## 💻 Features
// - 3 login attempts with failure messages
// - Graceful termination after failed attempts
// - Uses `BufferedReader` instead of `Scanner` for low-level control

// ## 🚀 Run the Project
// ```bash
// javac UpgradeBank.java
// java UpgradeBank


package ExceptionHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ✅ Custom Exception for Invalid Login
class InvalidExceptionNew extends Exception {
    public InvalidExceptionNew(String msg) {
        super(msg); // pass message to Exception class
    }
}

// ✅ ATM Logic with login validation
class ATM {

    private final int userID = 1234;
    private final int password = 1234;

    // Login method that takes BufferedReader as input (so it can be reused)
    public void login(BufferedReader br) throws IOException, InvalidExceptionNew {

        System.out.print("Enter the userID: ");
        int uid = Integer.parseInt(br.readLine());

        System.out.print("Enter the password: ");
        int passwd = Integer.parseInt(br.readLine());

        // ✅ Validation
        if (uid == userID && passwd == password) {
            System.out.println("✅ Congrats! Take your cash 💰");
        } else {
            InvalidExceptionNew inv = new InvalidExceptionNew("❌ Retry once!");
            System.out.println(inv.getMessage());
            throw inv;
        }
    }
}

// ✅ Controller class to manage 3 login attempts
class BankInternational {
    public void initiate() {
        ATM at = new ATM();

        // 🔐 Using try-with-resources to auto-close BufferedReader
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // 🔁 First Attempt
            try {
                at.login(br);
            } catch (InvalidExceptionNew | IOException e1) {
                System.out.println(e1.getMessage());

                // 🔁 Second Attempt
                try {
                    at.login(br);
                } catch (InvalidExceptionNew | IOException e2) {
                    System.out.println(e2.getMessage());

                    // 🔁 Third Attempt
                    try {
                        at.login(br);
                    } catch (InvalidExceptionNew | IOException e3) {
                        System.out.println(e3.getMessage());
                        System.out.println("🚫 Bro may be you don't have an account here :)");
                        System.exit(0); // exit after third failed attempt
                    }
                }
            }

        } catch (IOException ioe) {
            // ⚠️ Handles failure in creating/using BufferedReader
            System.out.println("⚠️ System-level error: " + ioe.getMessage());
        }
    }
}

// ✅ Main class to start the application
public class UpgradeBank {
    public static void main(String[] args) {
        BankInternational bn = new BankInternational();
        bn.initiate();
    }
}
