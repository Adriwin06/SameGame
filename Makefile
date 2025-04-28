### MAKEFILE ###

### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none
JVM = java
JVMFLAGS = 
SRC_DIR = SameGame/
LISTENERS_DIR = ${SRC_DIR}ActionListeners/

### ESSENTIAL RULES ###

Main.class : Main.java Cell.class SameGameWindow.class GamePanel.class Grid.class LoadGameMenu.class MainMenu.class SettingsMenu.class PlagueTaleLookAndFeel.class 
			${JC} ${JCFLAGS} Main.java

PlagueTaleLookAndFeel.class :
			${JC} ${JCFLAGS} ${SRC_DIR}PlagueTaleLookAndFeel.java

SameGameWindow.class : GenericWindows.class GameWindowListeners.class GoBackToMainMenu.class PlaceholderListener.class ExitListener.class LoadSelectedGameListener.class BrowseButtonListener.class SaveGameButtonListener.class EndScreen.class
			${JC} ${JCFLAGS} ${SRC_DIR}SameGameWindow.java

GenericWindows.class :
			${JC} ${JCFLAGS} ${SRC_DIR}GenericWindows.java

Cell.class : CellMouseAdapter.class
			${JC} ${JCFLAGS} ${SRC_DIR}Cell.java

GamePanel.class : GoBackToMainMenu.class SaveGameButtonListener.class
			${JC} ${JCFLAGS} ${SRC_DIR}GamePanel.java

Grid.class : 
			${JC} ${JCFLAGS} ${SRC_DIR}Grid.java

LoadGameMenu.class : FileListListener.class
			${JC} ${JCFLAGS} ${SRC_DIR}LoadGameMenu.java

MainMenu.class : MainMenuLogo.class
			${JC} ${JCFLAGS} ${SRC_DIR}MainMenu.java

MainMenuLogo.class : 
			${JC} ${JCFLAGS} ${SRC_DIR}MainMenuLogo.java

SettingsMenu.class :
			${JC} ${JCFLAGS} ${SRC_DIR}SettingsMenu.java

EndScreen.class : 
			${JC} ${JCFLAGS} ${SRC_DIR}EndScreen.java

### LISTENER CLASSES ###

SaveGameButtonListener.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}SaveGameButtonListener.java

PlaceholderListener.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}PlaceholderListener.java

LoadSelectedGameListener.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}LoadSelectedGameListener.java

GoBackToMainMenu.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}GoBackToMainMenu.java

GameWindowListeners.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}GameWindowListeners.java

FileListListener.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}FileListListener.java

ExitListener.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}ExitListener.java

CellMouseAdapter.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}CellMouseAdapter.java

BrowseButtonListener.class :
			${JC} ${JCFLAGS} ${LISTENERS_DIR}BrowseButtonListener.java

### OPTIONAL RULES ###

run: Main.class
	${JVM} ${JVMFLAGS} Main

clean: 
	-rm -f *.class
	-rm -f ${SRC_DIR}*.class
	-rm -f ${LISTENERS_DIR}*.class

mrproper: clean Main.class

### FAKE OBJECTIVES ###

.PHONY: run clean mrproper