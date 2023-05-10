import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * class of Borded Pane
 * we add here toolbar and pane
 */

public class GuiBorderPane extends BorderPane {
    public GuiBorderPane(Node toolBar, Node pane) {
        super(toolBar,  pane, null, null, null);
    }
}
