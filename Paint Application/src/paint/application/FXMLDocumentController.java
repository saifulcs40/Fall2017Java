/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author saiful
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Canvas drawingCanvas;
    private GraphicsContext gc;
    
    private void drawGrid(double dx, double dy, Color color){
        gc.setStroke(color);
        for(int i=0; i<=drawingCanvas.getWidth()/dx; i++){
            gc.strokeLine(i*dx, 0, i*dx, drawingCanvas.getHeight());
        }
        for(int i = 0; i<=drawingCanvas.getHeight()/dy; i++){
            gc.strokeLine(0, i*dy, drawingCanvas.getWidth(), i*dy);
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = drawingCanvas.getGraphicsContext2D();
        
        //drawGrid(20, 20, Color.LIGHTGRAY);
        //drawGrid(100, 100, Color.GRAY);
        
        //gc.strokeLine(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        //drawBDFlag();
        //drawMan();
        drawDesktopPC();
    }    

    private void drawBDFlag() {
        Color green = Color.rgb(0, 103, 71);
        gc.setFill(green);
        gc.fillRect(0, 0, 500, 300);
        
        Color red = Color.rgb(218, 41, 28);
        gc.setFill(red);
        gc.fillOval(225-100, 150-100, 200, 200);
        
    }

    private void drawMan() {
        Color black = Color.rgb(0, 0, 0);
        gc.setStroke(black);
        gc.setLineWidth(5);
        gc.strokeOval(40, 40, 120, 120);
        gc.strokeLine(100, 160, 100, 260);                
        gc.strokeLine(100, 260, 60, 320);
        gc.strokeLine(100, 260, 140, 320);
        
        gc.strokeLine(100, 100, 100, 110);
        //mouth
        gc.strokeArc(60, 60, 80, 80, 200, 140, ArcType.OPEN);
        //left eye
        gc.strokeOval(60, 80, 20, 20);
        //right eye
        gc.strokeOval(120, 80, 20, 20);
        
        //hand
        gc.strokeLine(100, 220, 60, 220);
        gc.strokeLine(60, 220, 20, 190);

    }

    private void drawDesktopPC() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.setLineCap(StrokeLineCap.ROUND);
        //CPU
        gc.strokeLine(100, 60, 180, 60);
        gc.strokeLine(100, 60, 100, 200);
        gc.strokeLine(100, 200, 180, 200);
        gc.strokeLine(180, 60, 180, 200);
        gc.strokeLine(120, 80, 160, 80);
        gc.strokeLine(120, 100, 160, 100);
        gc.fillOval(135, 170, 15, 15);
        
        //monitor
        gc.strokeLine(200, 70, 340, 70);
        gc.strokeLine(200, 160, 340, 160);
        gc.strokeLine(200, 70, 200, 160);
        gc.strokeLine(340, 70, 340, 160);
        gc.strokeLine(270, 160, 270, 200);
        gc.strokeLine(240, 200, 300, 200);


    }
    
}
