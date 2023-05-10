import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * GuiCircle is a circle class. Figures circle are instances of GuiCircles 
 */

public class GuiCircle extends Circle {
    
    public GuiCircle(double x, double y, double r){
        super(x,y,r);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);

        setOnMouseClicked(new CircleEventHandler());
        setOnMouseDragged(new CircleEventHandler());
        setOnScroll(new CircleScrollHandler());
    }

    /// Metoda sprawdza czy najechalismy na figure
    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    // Zmiana wspolrzednej x kola
    public void addX(double x) {
        setCenterX(getCenterX() + x);
    }

    // Zmiana wspolrzednej y kola
    public void addY(double y) {
        setCenterY(getCenterY() + y);
    }
    //Zmiana promienia kola
    public void addRadius(double r){
        setRadius(getRadius()+r);
    }
}


// Implementacja przesuwania
class CircleEventHandler implements EventHandler<MouseEvent> {

    GuiCircle circle;
    private double x;
    private double y;

    private void doMove(MouseEvent event) {

        double dx = event.getX() - x;
        double dy = event.getY() - y;

        // Jesli nacisnelismy na kolo
        if (circle.isHit(x, y)) {
            circle.addX(dx);
            circle.addY(dy);
        }
        x += dx;
        y += dy;
    }

    @Override
    public void handle(MouseEvent event) {

        circle = (GuiCircle) event.getSource();
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
class CircleScrollHandler implements EventHandler<ScrollEvent> {

    GuiCircle circle;

    private void doScale(ScrollEvent e) {

        double x = e.getX();
        double y = e.getY();

        // Jesli nacisnelismy na kolo
        if (circle.isHit(x, y)) {
            circle.addRadius(e.getDeltaY() * 0.2);
        }
    }

    @Override
    public void handle(ScrollEvent event) {

        circle = (GuiCircle) event.getSource();
        if (event.getEventType() == ScrollEvent.SCROLL) {
            doScale(event);
        }
    }
}


