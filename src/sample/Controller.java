package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private Button chooseImg;

    @FXML
    private ImageView sourseImg;

    @FXML
    private ImageView newImg;

    @FXML
    private javafx.scene.control.TextField rMin;
    @FXML
    private javafx.scene.control.TextField rMax;
    @FXML
    private javafx.scene.control.TextField gMin;
    @FXML
    private javafx.scene.control.TextField gMax;
    @FXML
    private javafx.scene.control.TextField bMin;
    @FXML
    private javafx.scene.control.TextField bMax;

    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private FileChooser fc = new FileChooser();


    public void chooseImgAction() {

        fc.setInitialDirectory(new File("D:\\Alla\\University\\6\\Multimedia processing\\3lab\\img"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("BMP Files", "*.bmp"));

        File selectedFile = fc.showOpenDialog(null);

        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(selectedFile);
            inputImage = ImageIO.read(imageInputStream);
            sourseImg.setImage(SwingFXUtils.toFXImage(inputImage, null));

            rMin.setText(String.valueOf(min(inputImage)[0]));
            gMin.setText(String.valueOf(min(inputImage)[1]));
            bMin.setText(String.valueOf(min(inputImage)[2]));

            rMax.setText(String.valueOf(max(inputImage)[0]));
            gMax.setText(String.valueOf(max(inputImage)[1]));
            bMax.setText(String.valueOf(max(inputImage)[2]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void redImgAction() {

        outputImage = copy(inputImage);
        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                Color color = new Color(inputImage.getRGB(i, j));
                Color rColor = new Color(color.getRed(), 0, 0);
                outputImage.setRGB(i, j, rColor.getRGB());
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));

        rMin.setText(String.valueOf(min(inputImage)[0]));
        gMin.setText("0");
        bMin.setText("0");

        rMax.setText(String.valueOf(min(inputImage)[0]));
        gMax.setText("0");
        bMax.setText("0");
    }

    public void greenImgAction() {

        outputImage = copy(inputImage);
        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                Color color = new Color(inputImage.getRGB(i, j));
                Color rColor = new Color(0, color.getGreen(), 0);
                outputImage.setRGB(i, j, rColor.getRGB());
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));

        rMin.setText("0");
        gMin.setText(String.valueOf(min(inputImage)[0]));
        bMin.setText("0");

        rMax.setText("0");
        gMax.setText(String.valueOf(min(inputImage)[0]));
        bMax.setText("0");
    }

    public void blueImgAction() {

        outputImage = copy(inputImage);
        for (int i = 0; i < outputImage.getWidth(); i++) {
            for (int j = 0; j < outputImage.getHeight(); j++) {
                Color color = new Color(inputImage.getRGB(i, j));
                Color rColor = new Color(0, 0, color.getBlue());
                outputImage.setRGB(i, j, rColor.getRGB());
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));
        rMin.setText("0");
        gMin.setText("0");
        bMin.setText(String.valueOf(min(inputImage)[0]));


        rMax.setText("0");
        gMax.setText("0");
        bMax.setText(String.valueOf(min(inputImage)[0]));

    }

    public void reset() {
        outputImage = copy(inputImage);


        rMin.setText(String.valueOf(min(inputImage)[0]));
        gMin.setText(String.valueOf(min(inputImage)[1]));
        bMin.setText(String.valueOf(min(inputImage)[2]));

        rMax.setText(String.valueOf(max(inputImage)[0]));
        gMax.setText(String.valueOf(max(inputImage)[1]));
        bMax.setText(String.valueOf(max(inputImage)[2]));

        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));


    }


    public int[] max(BufferedImage bufferedImage) {
        int R = 0;
        int G = 0;
        int B = 0;
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                if (color.getRed() > R) {
                    R = color.getRed();
                }
                if (color.getBlue() > B) {
                    B = color.getBlue();
                }
                if (color.getGreen() > G) {
                    G = color.getGreen();
                }
            }
        }
        return new int[]{R, G, B};
    }

    public int[] min(BufferedImage bufferedImage) {
        int R = 255;
        int G = 255;
        int B = 255;
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                if (color.getRed() < R) {
                    R = color.getRed();
                }
                if (color.getBlue() < B) {
                    B = color.getBlue();
                }
                if (color.getGreen() < G) {
                    G = color.getGreen();
                }
            }
        }
        return new int[]{R, G, B};
    }


    public void newColors() {
        outputImage = copy(inputImage);

        int[] min = min(inputImage);
        int[] max = max(inputImage);

        int[] new_min = new int[3];
        int[] new_max = new int[3];

        new_min[0] = Integer.parseInt(rMin.getText());
        new_min[1] = Integer.parseInt(gMin.getText());
        new_min[2] = Integer.parseInt(bMin.getText());

        new_max[0] = Integer.parseInt(rMax.getText());
        new_max[1] = Integer.parseInt(gMax.getText());
        new_max[2] = Integer.parseInt(bMax.getText());

        if(!(new_min[0]==min[0] && new_min[1]==min[1] && new_min[2]==min[2] &&
                new_max[0]==max[0] && new_max[1]==max[1] && new_max[2]==max[2])) {

            for (int i = 0; i < outputImage.getWidth(); i++) {
                for (int j = 0; j < outputImage.getHeight(); j++) {
                    Color color = new Color(outputImage.getRGB(i, j));
                    int red = (int) (color.getRed() - min[0]) * (new_max[0] - new_min[0]) / (max[0] - min[0]) + new_min[0];
                    int green = (int) (color.getGreen() - min[1]) * (new_max[1] - new_min[1]) / (max[1] - min[1]) + new_min[1];
                    int blue = (int) (color.getBlue() - min[2]) * (new_max[2] - new_min[2]) / (max[2] - min[2]) + new_min[2];
                    Color newColor = new Color(red, blue, green);
                    outputImage.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        newImg.setImage(SwingFXUtils.toFXImage(outputImage, null));
    }

    public void save(){
        File result = new File("D:\\Alla\\University\\6\\Multimedia processing\\3lab\\new.bmp");
        try {
            ImageIO.write(outputImage, "BMP", result);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }




    private BufferedImage copy(BufferedImage source) {
        ColorModel cm = source.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = source.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


}
