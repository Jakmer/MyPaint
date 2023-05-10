import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * class GuiButton contains all logic for buttons
 */

public class GuiButton extends Button {
    /**
     * @param isDrawing is condition for drawing figure
     */
    private boolean isDrawing = false;
    /**
     * 
     * @param buttonLabel is a name for button
     * in constructor of button we set it name, set button size and add styles file button.css
     */
    public GuiButton(String buttonLabel) {
        super(buttonLabel);
        setPrefSize(100, 50);
        getStyleClass().add("button-3");
    }

    /**
     *  saveButton is function responsible for save all figure paramteres and store them in file data.txt
     * 
     */

    public void saveButton(GuiPane pane) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    /**
                     * @param fileName name for file to store data
                     * @param data stores data that will be write to file
                     * We go for each figure and store their parameters
                     */

                    String fileName = "data.txt";
                    String data = "";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (javafx.scene.Node node : pane.getChildren()) {
                            if (node instanceof GuiRectangle) {
                                GuiRectangle rect = (GuiRectangle) node;
                                data += "R " + (Color) rect.getFill() + " " + rect.getX() + " " + rect.getY() + " "
                                        + rect.getWidth() + " "
                                        + rect.getHeight() + '\n';
                            } else if (node instanceof GuiCircle) {
                                GuiCircle circle = (GuiCircle) node;
                                data += "C " + circle.getFill() + " " + circle.getCenterX() + " " + circle.getCenterY()
                                        + " "
                                        + circle.getRadius() + '\n';
                            } else if (node instanceof GuiPolygon) {
                                GuiPolygon polygon = (GuiPolygon) node;
                                data += "P " + polygon.getFill() + " ";
                                for (double i : polygon.getPoints()) {
                                    data += i + " ";
                                }
                                data += '\n';
                            }
                        }
                        writer.write(data);
                    }

                } catch (Exception ex) {
                }
            }
        });
    }

    /**
     * 
     * loadButton is function responsible for loading ale figures to pane from file data.txt
     */

    public void loadButton(GuiPane pane) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    /**
                     * @param fileName is name of file that we want to read from
                     * @param filePath is path for file
                     * @param lines is list that contains all data from file
                     * we iterate for each line (each line is one figure) and create figure
                     */
                    String fileName = "data.txt";
                    Path filePath = Paths.get(fileName);
                    List<String> lines = Files.readAllLines(filePath);

                    for (String line : lines) {
                        char figureType = line.charAt(0);
                        switch (figureType) {
                            case 'R':
                                String[] param = line.split(" ");
                                Color color = Color.web(param[1]);
                                double x = Double.parseDouble(param[2]);
                                double y = Double.parseDouble(param[3]);
                                ;
                                double width = Double.parseDouble(param[4]);
                                ;
                                double height = Double.parseDouble(param[5]);
                                ;
                                GuiRectangle rectangle = new GuiRectangle(x, y, width, height);
                                rectangle.setFill(color);
                                pane.getChildren().addAll(rectangle);
                                break;
                            case 'C':
                                String[] param2 = line.split(" ");
                                Color color2 = Color.web(param2[1]);
                                double xCenter = Double.parseDouble(param2[2]);
                                double yCenter = Double.parseDouble(param2[3]);
                                double r = Double.parseDouble(param2[4]);

                                GuiCircle circle = new GuiCircle(xCenter, yCenter, r);
                                circle.setFill(color2);
                                pane.getChildren().addAll(circle);
                                break;
                            case 'P':
                                String[] param3 = line.split(" ");
                                Color color3 = Color.web(param3[1]);
                                ArrayList<Double> points = new ArrayList<>();
                                for (int i = 2; i < param3.length; i++) {
                                    points.add(Double.parseDouble(param3[i]));
                                }
                                GuiPolygon polygon = new GuiPolygon(points);
                                polygon.setFill(color3);
                                pane.getChildren().add(polygon);
                                break;
                            default:
                                break;
                        }
                    }

                } catch (Exception ex) {
                }
            }
        });
    }

/**
 * infoButton is function responisble for open new window with information about program author etc.
 */

    public void infoButton() {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    Stage newStage = new Stage();
                    newStage.setTitle("Information");

                    Label label = new Label(
                            "Paint\nVersion: 1.0.0\nApplication that imitates basic paint.\nYou can draw figures, modify and save them. Have fun!\nAuthor: Jakub Merta\n");
                    // label.setLabelPadding(new Insets(20, 40, 40, 0));
                    label.setFont(new Font(18));

                    StackPane root = new StackPane(label);
                    root.setPrefSize(600, 300);

                    Scene scene = new Scene(root);
                    newStage.setScene(scene);
                    newStage.show();

                } catch (Exception ex) {
                }

            }
        });
    }
/**
 * 
 * builderButton is function that creates polygons from points
 * @param points is list of polygon points
 * @param p is list of points that are visible befor creating polygon
 * if polygon doesnt have atleast 3 point function is unable to create polygon and open new window with exception information
 */
    public void builderButton(GuiPane pane, ArrayList<Double> points, ArrayList<GuiCircle> p) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    isDrawing = false;
                    pane.setOnMouseClicked(null);
                    pane.setOnMousePressed(null);
                    pane.setOnMouseDragged(null);
                    pane.setOnMouseReleased(null);
                    System.out.println(points.size());
                    if (points.size() < 6) {
                        Stage newStage = new Stage();
                        newStage.setTitle("Exception");

                        Label label = new Label(
                                "Polygon must have atleast 3 points \n");
                        label.setPadding(new javafx.geometry.Insets(100));
                        label.setFont(new Font(18));

                        StackPane root = new StackPane(label);
                        root.setPrefSize(600, 300);

                        Scene scene = new Scene(root);
                        newStage.setScene(scene);
                        newStage.show();
                    } else {
                        /**
                         * creating new polygon figure
                         */
                        GuiPolygon polygon = new GuiPolygon(points);
                        pane.getChildren().add(polygon);
                        for (GuiCircle i : p) {
                            pane.getChildren().remove(i);
                        }
                    }
                    /**
                     * making builderButton invisble after creating polygon
                     */
                    setVisible(false);

                } catch (Exception ex) {
                }

            }
        });
    }

    /**
     * Rectangle is function responsible for creating rectangle 
     * 
     */

    public void Recatngle(GuiPane pane) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                isDrawing = true;
                pane.setOnMouseClicked(null);
                pane.setOnMousePressed(null);
                pane.setOnMouseDragged(null);
                pane.setOnMouseReleased(null);

                pane.setOnMousePressed(event -> {

                    double startX = event.getX();
                    double startY = event.getY();
                    GuiRectangle currentRectangle = new GuiRectangle(startX, startY, 0, 0);
                    currentRectangle.setFill(Color.TRANSPARENT);
                    currentRectangle.setStroke(Color.BLACK);

                    pane.getChildren().add(currentRectangle);

                    pane.setOnMouseDragged(event1 -> {
                        double width = event1.getX() - startX;
                        double height = event1.getY() - startY;
                        if (isDrawing) {
                            currentRectangle.setWidth(width);
                            currentRectangle.setHeight(height);
                        }
                    });

                    pane.setOnMouseReleased(event1 -> {
                        double endX = event1.getX();
                        double endY = event1.getY();
                        pane.getChildren().remove(currentRectangle);
                        if (isDrawing) {
                            GuiRectangle rectangle = new GuiRectangle(Math.min(startX, endX), Math.min(startY, endY),
                                    Math.abs(endX - startX),
                                    Math.abs(endY - startY));

                            pane.getChildren().add(rectangle);
                            isDrawing = false;
                        }
                        isDrawing = false;

                    });

                });
            }
        });
    }

    /**
     * 
     * Circle is function responsible for creating circles
     */
    public void Circle(GuiPane pane) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                isDrawing = true;
                pane.setOnMouseClicked(null);
                pane.setOnMousePressed(null);
                pane.setOnMouseDragged(null);
                pane.setOnMouseReleased(null);

                pane.setOnMousePressed(event -> {

                    double startX = event.getX();
                    double startY = event.getY();

                    GuiCircle currentCircle = new GuiCircle(startX, startY, 0);
                    currentCircle.setFill(Color.TRANSPARENT);
                    currentCircle.setStroke(Color.BLACK);

                    pane.getChildren().add(currentCircle);

                    pane.setOnMouseDragged(event1 -> {
                        double width = event1.getX() - startX;
                        double height = event1.getY() - startY;
                        double r = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
                        if (isDrawing)
                            currentCircle.setRadius(r);
                    });

                    pane.setOnMouseReleased(event1 -> {
                        pane.getChildren().remove(currentCircle);
                        double endX = event1.getX();
                        double endY = event1.getY();
                        if (isDrawing) {
                            double r = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
                            GuiCircle circle = new GuiCircle(startX, startY, r);

                            pane.getChildren().add(circle);
                        }
                        isDrawing = false;
                    });
                });

            }
        });
    }

    /**
     * Polygon is function responsible for creating polygons
     */

    public void Polygon(GuiPane pane, GuiButton builder) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                pane.setOnMouseClicked(null);
                pane.setOnMousePressed(null);
                pane.setOnMouseDragged(null);
                pane.setOnMouseReleased(null);
                isDrawing = true;
                ArrayList<Double> points = new ArrayList<>();
                ArrayList<GuiCircle> p = new ArrayList<>();
                pane.setOnMouseClicked(event -> {
                    double x = event.getX();
                    double y = event.getY();

                    points.add(x);
                    points.add(y);
                    if (isDrawing) {
                        GuiCircle circle = new GuiCircle(x, y, 0.5);
                        p.add(circle);
                        circle.setStroke(Color.BLACK);
                        pane.getChildren().add(circle);
                    }

                });

                builder.setVisible(true);
                builder.builderButton(pane, points, p);
            }
        });
    }
    /**
     * 
     * rotate is function responsible for rotating figures
     */
    public void rotate(GuiPane pane) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // pane.setOnMouseClicked(null);
                pane.setOnMouseClicked(event1 -> {
                    double x = event1.getX();
                    double y = event1.getY();
                    for (javafx.scene.Node node : pane.getChildren()) {
                        if (node instanceof GuiRectangle) {
                            GuiRectangle rect = (GuiRectangle) node;
                            if (node.getBoundsInLocal().contains(x, y)) {
                                System.out.println("Rectangle ");
                                setOnKeyPressed(null);
                                setOnKeyReleased(null);
                                setOnKeyPressed(new RectangleKeyHandler(rect));
                                setOnKeyReleased(new RectangleKeyHandler(rect));
                            }

                        } else if (node instanceof GuiCircle) {
                            // GuiCircle circle = (GuiCircle) node;
                            // System.out.println("Circle " + circle.getCenterX());
                        } else if (node instanceof GuiPolygon) {
                            // System.out.println("Polygon");
                            GuiPolygon polygon = (GuiPolygon) node;
                            if (polygon.getBoundsInLocal().contains(x, y)) {
                                setOnKeyPressed(null);
                                setOnKeyReleased(null);
                                setOnKeyPressed(new PolyKeyHandler(polygon));
                                setOnKeyReleased(new PolyKeyHandler(polygon));
                            }
                        }
                    }

                });

            }
        });
    }

}