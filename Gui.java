import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * class Gui is used for layout creating, you can add buttons, panes and whatever you want
 */

public class Gui {
    /**
     * @param colorPicker picks colors
     * @param selectedColor contains selected color
     * @param cond is condition that change figure's color only once after click on colorpicker
     */
    GuiColorPicker colorPicker = new GuiColorPicker();
    Color selectedColor;
    boolean cond = false;

    /**
     * Gui constructor contains all layout's elements
     * @param stage
     */
    public Gui(Stage stage) {
        
        /**
         * @param pane place on gui for storing all figures
         */

        GuiPane pane = new GuiPane(); 
        colorPicker.setOnAction(event -> {

            /**
             * logic for colorPicker - after choosing a color you can change color of only one figure
             */

            selectedColor = colorPicker.getValue();
            System.out.println(selectedColor.toString());
            cond = true;

            pane.setOnMousePressed(event1->{
            
                double x = event1.getX();
                double y = event1.getY();
                System.out.println(x+"  "+y);
                for (javafx.scene.Node node : pane.getChildren()){
                    Shape temp;
                    if(node instanceof Shape){
                        temp = (Shape) node;
                            if(temp.getBoundsInLocal().contains(x,y) & cond){
                                temp.setFill(selectedColor);
                                cond = false;
                            }
                    }
                }
            });
        });
        
        /** Buttons
         * each name button sugests what figure it creates
         */

        GuiButton rectangleButton = new GuiButton("Rectangle");
        GuiButton circleButton = new GuiButton("Circle");
        GuiButton  polygonButton= new GuiButton("Polygon");
        GuiButton rotateButton = new GuiButton("Rotate");

        /**Buttons
         * each button name describes what each button is responsible for
         */

        GuiButton saveButton = new GuiButton("Save");
        GuiButton loadButton = new GuiButton("Load");
        GuiButton infoButton = new GuiButton("Info");
        GuiButton buildPolygonButton = new GuiButton("Build polygon");

        /**
         * buildPolygonButton is setted as invisible because we want to make it visible only when polygon is creating
         */

        buildPolygonButton.setVisible(false);
        buildPolygonButton.setPrefSize(200, 50);

        /**
         * adding events for each button
         */

        circleButton.Circle(pane);
        rectangleButton.Recatngle(pane);
        polygonButton.Polygon(pane,buildPolygonButton);
        rotateButton.rotate(pane);
        saveButton.saveButton(pane);
        loadButton.loadButton(pane);
        infoButton.infoButton();

        /**
         * @param toolBar holds all buttons and color picker
         * @param root is border pane which contains pane and toolBar
         */

        GuiToolBar toolBar = new GuiToolBar(rectangleButton,circleButton,polygonButton,rotateButton, saveButton, loadButton,infoButton, buildPolygonButton,colorPicker);
        BorderPane root = new GuiBorderPane(pane,toolBar);
        
        /**
         * @param scene is our window
         */

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("PAINT");
        stage.setMaximized(true);
        
        stage.show(); 
        
    }

}