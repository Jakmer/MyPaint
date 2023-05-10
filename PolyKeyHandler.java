import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
/**class for rotating polygon */
public class PolyKeyHandler implements EventHandler<KeyEvent> {
    GuiPolygon polygon;
  

    public PolyKeyHandler(GuiPolygon p)
    {
        polygon = p;
    }

    @Override
    public void handle(KeyEvent event) {
        /**rotating polygon */

        switch (event.getCode()) {
            case W:
                polygon.rotate(-10);
                break;
            case S:
                polygon.rotate(10);
                break;
        }
    }
}
