JAVAC = javac
JAVA = java
JFLAGS = --module-path /home/jakub/Documents/Workspace/Java/javafx-sdk-20.0.1/lib --add-modules javafx.controls

.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES = \
        Main.java \
        Gui.java \
		GuiPane.java \
        GuiBorderPane.java \
		GuiButton.java \
		GuiMenuButton.java \
		GuiMenuItem.java \
		GuiToolBar.java \
		GuiRectangle.java \
		GuiCircle.java \
		GuiPolygon.java \
		Figure.java \
		RectangleKeyHandler.java \
		PolyKeyHandler.java \

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JAVA) $(JFLAGS) $(MAIN)

clean:
	$(RM) *.class