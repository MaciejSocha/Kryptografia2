package sample;

//import algorithm.Algorithm;
//import data.SaveToFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
//import keys.GenerateKey;
import methods.Conversions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    private List<Byte> encoded;
    private List<Byte> decoded;
   // private Algorithm algorithm;
    private File file;
   // private SaveToFile saveToFile;
   // private GenerateKey generateKey;
    private String end;

    @FXML
    public TextArea key, messages, selectedFile;
    @FXML
    public Button browse, encode, decode, randomKey;

    public Controller() {
        /*algorithm = new Algorithm();
        saveToFile = new SaveToFile();
        generateKey = new GenerateKey();*/
    }

    public void pressBrowse(ActionEvent event) {

        JFileChooser fc = new JFileChooser(new File("").getAbsolutePath());
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            selectedFile.setText("Selected File: " + file.getPath());
            messages.setText("File succesfuly loaded.");
            String s = file.getName();
            char[] pom = s.toCharArray();
            int pom2 = pom.length-1;
            while (pom[pom2] != '.') {
                pom2--;
            }
            end = s.substring(pom2);
        }
    }

    public void pressEncode(ActionEvent event) {
        if(file != null && key.getText().length() == 48) {
            /*encode.setDisable(true);
            encoded = algorithm.encode3DES(getBytesFromFile(), key.getText());
            saveToFile.save("encode" + end, encoded, 0);
            messages.setText("File has been encoded.");
            encode.setDisable(false);*/
        }
    }

    public void pressDecode(ActionEvent event) {
        if(file != null && key.getText().length() == 48) {
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

    public void pressRandomKey(ActionEvent event) {
        //key.setText(generateKey.getRandomKey());
    }
}
