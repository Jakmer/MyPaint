import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        // Create a polygon
        Polygon polygon = new Polygon(
                100, 50,
                200, 50,
                150, 150
        );
        polygon.setFill(Color.RED);
        pane.getChildren().add(polygon);

        // Set initial scale
        double initialScale = 1.0;
        Scale scaleTransform = new Scale(initialScale, initialScale, 0, 0);
        polygon.getTransforms().add(scaleTransform);

        // Scale the polygon on scroll
        double scaleFactor = 0.1;
        scene.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            double scale = 1+scaleFactor;
            scaleTransform.setX(scale * scaleTransform.getX());
            scaleTransform.setY(scale * scaleTransform.getY());
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
