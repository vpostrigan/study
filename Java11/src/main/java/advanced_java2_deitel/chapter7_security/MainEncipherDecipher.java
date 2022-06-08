package advanced_java2_deitel.chapter7_security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Security;
import java.util.Vector;

public class MainEncipherDecipher extends JFrame {

    // salt for password-based encryption-decryption algorithm
    private static final byte[] salt = {
            (byte) 0xf5, (byte) 0x33, (byte) 0x01, (byte) 0x2a,
            (byte) 0xb2, (byte) 0xcc, (byte) 0xe4, (byte) 0x7f
    };

    private int iterationCount = 100;

    private JTextField passwordTextField;
    private JTextField fileNameTextField;
    private JEditorPane fileContentsEditorPane;

    public MainEncipherDecipher() {
        // set security provider
        // Security.addProvider(new com.sun.crypto.provider.SunJCE());

        // initialize main frame
        setSize(new Dimension(400, 400));
        setTitle("Encryption and Decryption Example");

        JPanel topPanel = new JPanel();
        {
            // panel where password and file name labels will be placed
            JPanel labelsPanel = new JPanel();
            labelsPanel.setLayout(new GridLayout(2, 1));
            labelsPanel.add(new JLabel(" File Name: "));
            labelsPanel.add(new JLabel(" Password: "));

            passwordTextField = new JPasswordField();
            fileNameTextField = new JTextField();

            // panel where password and file name textfields will be placed
            JPanel textFieldsPanel = new JPanel();
            textFieldsPanel.setLayout(new GridLayout(2, 1));
            textFieldsPanel.add(fileNameTextField);
            textFieldsPanel.add(passwordTextField);

            topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            topPanel.setLayout(new BorderLayout());
            topPanel.add(labelsPanel, BorderLayout.WEST);
            topPanel.add(textFieldsPanel, BorderLayout.CENTER);
        }

        // construct middle panel
        JPanel middlePanel = new JPanel();
        {
            // construct and place title label for contents pane
            JLabel fileContentsLabel = new JLabel();
            fileContentsLabel.setText(" File Contents");

            // initialize and place editor pane within scroll panel
            fileContentsEditorPane = new JEditorPane();

            middlePanel.setLayout(new BorderLayout());
            middlePanel.add(fileContentsLabel, BorderLayout.NORTH);
            middlePanel.add(new JScrollPane(fileContentsEditorPane), BorderLayout.CENTER);
        }

        // construct bottom panel
        JPanel bottomPanel = new JPanel();
        {
            // create encrypt button
            JButton encryptButton = new JButton("Encrypt and Write to File");
            encryptButton.addActionListener(event -> encryptAndWriteToFile());
            // create decrypt button
            JButton decryptButton = new JButton("Read from File and Decrypt");
            decryptButton.addActionListener(event -> readFromFileAndDecrypt());

            bottomPanel.add(encryptButton);
            bottomPanel.add(decryptButton);
        }

        // initialize main frame window
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(middlePanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    // obtain contents from editor pane and encrypt
    private void encryptAndWriteToFile() {
        // obtain user input
        String originalText = fileContentsEditorPane.getText();
        String password = passwordTextField.getText();
        String fileName = fileNameTextField.getText();

        // create secret key and get cipher instance
        Cipher cipher = cipher(Cipher.ENCRYPT_MODE, password);

        byte[] outputArray = originalText.getBytes(StandardCharsets.UTF_8);

        File file = new File(fileName);
        // write contents to file and close
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             CipherOutputStream out = new CipherOutputStream(fileOutputStream, cipher);) {
            out.write(outputArray);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        String encryptedText = null;
        try {
            encryptedText = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        // update Editor Pane contents
        fileContentsEditorPane.setText(encryptedText);
    }

    // obtain contents from file and decrypt
    private void readFromFileAndDecrypt() {

        // used to rebuild byte list
        Vector fileBytes = new Vector();

        String password = passwordTextField.getText();
        String fileName = fileNameTextField.getText();

        // create secret key
        Cipher cipher = cipher(Cipher.DECRYPT_MODE, password);

        // read and decrypt contents from file
        File file = new File(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             CipherInputStream in = new CipherInputStream(fileInputStream, cipher);) {
            // read bytes from stream.
            byte contents = (byte) in.read();

            while (contents != -1) {
                fileBytes.add(Byte.valueOf(contents));
                contents = (byte) in.read();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        // create byte array from contents in Vector fileBytes
        byte[] decryptedText = new byte[fileBytes.size()];
        for (int i = 0; i < fileBytes.size(); i++) {
            decryptedText[i] = ((Byte) fileBytes.elementAt(i)).byteValue();
        }

        fileContentsEditorPane.setText(new String(decryptedText, StandardCharsets.UTF_8));
    }

    private Cipher cipher(int opmode, String password) {
        // create secret key
        Cipher cipher = null;
        try {
            // create password based encryption key object
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

            // obtain instance for secret key factory
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

            // generate secret key for encryption
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            // specifies parameters used with password based encryption
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterationCount);

            // obtain cipher instance reference.
            cipher = Cipher.getInstance("PBEWithMD5AndDES");

            cipher.init(opmode, secretKey, parameterSpec);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        return cipher;
    }

    public static void main(String[] args) {
        System.out.println(new String(salt, StandardCharsets.UTF_8));
        System.out.println(new String(salt, StandardCharsets.ISO_8859_1));
        System.out.println(new String(salt, StandardCharsets.US_ASCII));
        System.out.println(new String(salt, StandardCharsets.UTF_16));
        System.out.println(new String(salt, StandardCharsets.UTF_16BE));
        System.out.println(new String(salt, StandardCharsets.UTF_16LE));
        System.out.println("");

        for (Object provider : Security.getProviders()) {
            System.out.println(provider);
        }

        MainEncipherDecipher crypto = new MainEncipherDecipher();
        crypto.validate();
        crypto.setVisible(true);
    }

}