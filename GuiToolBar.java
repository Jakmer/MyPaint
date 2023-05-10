import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToolBar;
/**Toolbar class
 * we add here toolbar styles
 */
public class GuiToolBar extends ToolBar{

    public GuiToolBar(GuiButton rect, GuiButton circ,GuiButton poly,GuiButton rotate, GuiButton saveButton,GuiButton loadButton, Button infoButton, Button builder, ColorPicker cp)
    {
        super();
        setPrefSize(2000,50);
        getItems().addAll(rect,circ,poly,rotate,saveButton,loadButton,infoButton,builder,cp);
        getStyleClass().add("toolbar-style");
    }
}
