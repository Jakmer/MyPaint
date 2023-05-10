import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
/**Polygon class */
public class GuiPolygon extends Polygon {
    public double rotationAngle = 0;

    public GuiPolygon(ArrayList<Double> points) {
        super();
        getPoints().addAll(points);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setOnMouseClicked(new PolygonEventHandler());
        setOnMouseDragged(new PolygonEventHandler());
        setOnScroll(new PolygonScrollHandler());
    }

    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    // Zmiana wspolrzednej x prostakata
    public void addXY(double x, double y) {
        ArrayList<Double> points = new ArrayList<>();

        for (double i : getPoints()) {
            points.add(i);
        }
        getPoints().clear();

        for (int i = 0; i < points.size(); i++) {
            if (i % 2 == 0)
                getPoints().add(points.get(i) + x);
            else
                getPoints().add(points.get(i) + y);
        }

    }

    // Zmiana szerokosci prostokata
    public void addWidthHeight(double w, double h) {
        ArrayList<Double> points = new ArrayList<>();
        double first = getPoints().get(0);

        for (double i : getPoints()) {
            points.add(i);
        }
        getPoints().clear();
        getPoints().add(first);
        for (int i = 1; i < points.size(); i++) {
            if (i % 2 == 0)
                getPoints().add(points.get(i) + w);
            else
                getPoints().add(points.get(i) + h);
        }
    }

    public void rotate(double angle) {
        rotationAngle += angle;
        double centerX = (getBoundsInLocal().getMinX() + getBoundsInLocal().getMaxX()) / 2;
        double centerY = (getBoundsInLocal().getMinY() + getBoundsInLocal().getMaxY()) / 2;
        getTransforms().setAll(new Rotate(rotationAngle, centerX, centerY));

    }

}

// Implementacja przesuwania
class PolygonEventHandler implements EventHandler<MouseEvent> {

    GuiPolygon polygon;
    private double x;
    private double y;

    private void doMove(MouseEvent event) {

        double dx = event.getX() - x;
        double dy = event.getY() - y;

        // Jesli nacisnelismy na elipse
        if (polygon.isHit(x, y)) {
            polygon.addXY(dx, dy);
        }
        x += dx;
        y += dy;
    }

    @Override
    public void handle(MouseEvent event) {
        
        polygon = (GuiPolygon) event.getSource();
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            x = event.getX();
            y = event.getY();
        }
        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            doMove(event);
        }

    }
}

// Implementacja scrollowania
class PolygonScrollHandler implements EventHandler<ScrollEvent> {

    @Override
    public void handle(ScrollEvent event) {
            GuiPolygon polygon = (GuiPolygon) event.getSource();

            if (event.getDeltaY() > 0) {
                polygon.setScaleX(polygon.getScaleX() * 1.3);
                polygon.setScaleY(polygon.getScaleY() * 1.3);
            } else {
                {
                    polygon.setScaleX(polygon.getScaleX() * 0.9);
                    polygon.setScaleY(polygon.getScaleY() * 0.9);
                }
            }

        
    }

}
