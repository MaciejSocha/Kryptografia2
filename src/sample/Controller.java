package sample;

import algorithm.Algorithm;
import algorithm.DSA;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.math.BigInteger;

public class Controller {
    private File file;
    private Algorithm algorithm;

    @FXML
    public TextArea privateKey, publicKey, selectedFile, messages, sSignature, rSignature;
    @FXML
    public Button browse, verifyFile, generateSignature;

    public Controller() {
        algorithm = new DSA();
    }

    public void pressBrowse() {
        JFileChooser fc = new JFileChooser(new File("").getAbsolutePath());
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            selectedFile.setText("Selected File: " + file.getPath());
            messages.setText("File succesfuly loaded.");
        }
    }

    public void pressVerifyFile() {
        if (file != null && publicKey.getText().length() > 0) {
            BigInteger[] signatures = new BigInteger[]{new BigInteger(rSignature.getText()), new BigInteger(sSignature.getText())};
            boolean isVerified = algorithm.verifyFile(publicKey.getText(), signatures, file);
            if (isVerified) {
                messages.setText("File is correct.");
            } else {
                messages.setText("File is incorrect.");
            }
        }
    }

    public void pressGenerateSignature() {
        if (file != null && privateKey.getText().length() > 0) {
            String[] info = algorithm.generateKey(privateKey.getText(), file);
            rSignature.setText(info[0]);
            sSignature.setText(info[1]);
            publicKey.setText(info[2]);
            messages.setText("Public key has been generated.");
        }
    }
}
