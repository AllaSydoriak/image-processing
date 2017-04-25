package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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

    private BufferedImage inputImage;
//  private BufferedImage outputImage;
    private FileChooser fc = new FileChooser();


    public void chooseImgAction(ActionEvent event) throws MalformedURLException {

        fc.setInitialDirectory(new File("D:\\University\\6\\Proccessing of multimedia\\3lab\\img"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("BMP Files", "*.bmp"));

        File selectedFile = fc.showOpenDialog(null);

        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(selectedFile);
            inputImage = ImageIO.read(imageInputStream);
//            outputImage = inputImage;
            sourseImg.setImage(SwingFXUtils.toFXImage(inputImage, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void redImgAction() {
        BufferedImage outputImage = copy(inputImage);
        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                Color color = new Color(inputImage.getRGB(i, j));
                Color rColor = new Color(color.getRed(), 0, 0);
                outputImage.setRGB(i, j, rColor.getRGB());
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));

    }

    public void greenImgAction() {
        BufferedImage outputImage = copy(inputImage);

        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                Color color = new Color(inputImage.getRGB(i, j));
                Color rColor = new Color(0, color.getGreen(), 0);
                outputImage.setRGB(i, j, rColor.getRGB());
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));
    }

    public void blueImgAction(ActionEvent event) {
        BufferedImage outputImage = copy(inputImage);

        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                Color color = new Color(inputImage.getRGB(i, j));
                Color rColor = new Color(0, 0, color.getBlue());
                outputImage.setRGB(i, j, rColor.getRGB());
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));
    }

    private BufferedImage copy(BufferedImage source) {
        ColorModel cm = source.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = source.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
