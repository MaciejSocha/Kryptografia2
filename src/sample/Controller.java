package sample;

import algorithm.Algorithm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;

public class Controller {
    private File file;
    private Algorithm algorithm;

    @FXML
    public TextArea privateKey, publicKey, selectedFile, messages;
    @FXML
    public Button browse, verifyFile, generateSignature;

    public Controller() {
        /*algorithm = new Algorithm();
        saveToFile = new SaveToFile();
        generateKey = new GenerateKey();*/
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
            boolean isVerified = algorithm.verifyFile(publicKey.getText(), file);
            if (isVerified) {
                messages.setText("File is correct.");
            } else {
                messages.setText("File is incorrect.");
            }
        }
    }

    public void pressGenerateSignature() {
        if (file != null && privateKey.getText().length() > 0) {
            publicKey.setText(algorithm.generateKey(privateKey.getText(), file));
            messages.setText("Public key has been generated.");
        }
    }
}
