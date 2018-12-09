package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import methods.Conversions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private File file;

    @FXML
    public TextArea privateKey, publicKey, selectedFile, messages;
    @FXML
    public Button browse, verifySignature, generateSignature;

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

    public void pressVerifySignature(ActionEvent event) {
        if(file != null && publicKey.getText().length() > 0) {
            /*encode.setDisable(true);
            encoded = algorithm.encode3DES(getBytesFromFile(), key.getText());
            saveToFile.save("encode" + end, encoded, 0);
            messages.setText("File has been encoded.");
            encode.setDisable(false);*/
        }
    }

    public void pressGenerateSignature(ActionEvent event) {
        if(file != null && privateKey.getText().length() > 0) {
            /*decode.setDisable(true);
            decoded = algorithm.decode3DES(getBytesFromFile(), key.getText());
            saveToFile.save("decode" + end, decoded, 0);
            messages.setText("File has been successfully decoded.");
            decode.setDisable(false);*/
        }
    }

    private List<Byte> getBytesFromFile() {
        List<Byte> list = new ArrayList<>();
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            for(Byte b : bytes) {
                List<Byte> pom = Conversions.numberTo8Byte(b);
                list.addAll(pom);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
