import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
/**class for rotating rectangle */
class RectangleKeyHandler implements EventHandler<KeyEvent> {

    GuiRectangle rectangle;

    public RectangleKeyHandler(GuiRectangle r)
    {
        rectangle = r;
    }

    @Override
    public void handle(KeyEvent event) {
        /**rotating rectangle */

        switch (event.getCode()) {
            case W:
                rectangle.rotate(-10);
                break;
            case S:
                rectangle.rotate(10);
                break;
        }
    }
}