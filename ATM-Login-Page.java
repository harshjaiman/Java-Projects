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
            InvalidExceptionNew inv = new InvalidExceptionNew("❌ Bhai dobara try kar le!");
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
                        System.out.println("🚫 Bhaiya aapka khata yaha nahi hai shayad :)");
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
