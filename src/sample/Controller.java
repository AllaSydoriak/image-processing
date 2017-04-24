package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Controller {

    @FXML
    private Button chooseImg;

    @FXML
    private ImageView sourseImg;

    @FXML
    private ImageView newImg;


    public void chooseImgAction(ActionEvent event) throws MalformedURLException {
        BufferedImage inputImage;
        BufferedImage outputImage;
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File("D:\\University\\6\\Proccessing of multimedia\\3lab\\img"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("BMP Files", "*.bmp"));

        File selectedFile = fc.showOpenDialog(null);

        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(selectedFile);
            inputImage = ImageIO.read(imageInputStream);
            //outputImage = inputImage;
            sourseImg.setImage(SwingFXUtils.toFXImage(inputImage, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







    public void redImgAction(ActionEvent event) {
    }
}
