package advanced_java2_deitel.chapter7_security.jaas;

import javax.swing.*;
import javax.security.auth.*;
import javax.security.auth.login.*;

public class AuthenticateNT {

    public static void main(String[] args) {
        // authenticate user and perform PrivilegedAction
        try {
            // create LoginContext for AuthenticateNT context
            LoginContext loginContext = new LoginContext("AuthenticateNT");

            // perform login
            loginContext.login();

            // if login executes without exceptions, login was successful
            System.out.println("Login Successful");

            // get Subject now associated with LoginContext
            Subject subject = loginContext.getSubject();

            // display Subject details
            System.out.println(subject);

            // perform the WriteFileAction as current Subject
            Subject.doAs(subject, new WriteFileAction());

            // log out current Subject
            loginContext.logout();

            System.exit(0);

        } catch (LoginException loginException) {
            loginException.printStackTrace();
            System.exit(-1);
        }
    }

}