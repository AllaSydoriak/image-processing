package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;

public class Controller {

    @FXML
    private Button chooseImg;

    @FXML
    private TextField k;

    @FXML
    private ImageView sourseImg;

    @FXML
    private ImageView newImg;

    public void chooseImgAction(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.setInitialDirectory(new File());

        String thumbURL = selectedFile.toURI().toURL().toString();
        Image imgLoad = new Image(thumbURL);

        if(selectedFile != null) {
            sourseImg.setImage(imgLoad);
        } else {
            System.out.println("Неправильний тип файлу");
        }
    }

}
