package SameGame;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

/**
 * A custom Look and Feel that creates a medieval theme inspired by Plague Tale: Innocence and Plague Tale: Requiem.
 * Because these games are just beautful, incredible, emotional, immersive, so I wanted to create a theme that reflects its atmosphere.
 * 
 * @author Adriwin
 * @version 1.2.3
 */
public class PlagueTaleLookAndFeel extends MetalLookAndFeel {
    // Theme colors from Plague Tale palette, all package private so we can access them in other classes
    static final Color DARK_BROWN = new Color(45, 30, 15);
    static final Color MEDIUM_BROWN = new Color(90, 60, 30);
    static final Color LIGHT_BROWN = new Color(150, 120, 90);
    static final Color PARCHMENT = new Color(240, 230, 200);
    static final Color AMBER = new Color(255, 191, 71);
    static final Color COAL_GREY = new Color(60, 60, 60);
    static final Color LIGHT_BROWN_HOVER = new Color(150, 120, 90, 100);
    static final Color WARM_AMBER = new Color(255, 191, 71, 100);
    static final Color CRIMSON_RED = new Color(139, 0, 0, 100);
    static final FontUIResource MEDIEVAL_FONT;
    
    static {
        // Try to load a medieval-style font if available, otherwise fallback to Serif
        Font medievalFont = null;
        try {
            File fontFile = new File("SameGame/Assets/Fonts/MedievalSharp.ttf");
            medievalFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            medievalFont = medievalFont.deriveFont(Font.PLAIN, 14);
        } catch (Exception e) {
            System.err.println("Could not load medieval font file: " + e.getMessage());
            e.printStackTrace();
        }
        
        if (medievalFont == null) {
            // Fallback to a default font
            medievalFont = new Font("Serif", Font.PLAIN, 14);
        }
        MEDIEVAL_FONT = new FontUIResource(medievalFont);
    }
    
    /**
     * Constructor - initializes the custom theme.
     */
    public PlagueTaleLookAndFeel() {
        MetalLookAndFeel.setCurrentTheme(new PlagueTaleTheme());
    }
    
    /**
     * Returns the name of this look and feel.
     */
    @Override
    public String getName() {
        return "Plague Tale Theme";
    }
    
    /**
     * Returns the ID of this look and feel.
     */
    @Override
    public String getID() {
        return "PlagueTaleTheme";
    }
    
    /**
     * Returns a description of this look and feel.
     */
    @Override
    public String getDescription() {
        return "A medieval theme inspired by Plague Tale: Requiem";
    }
    
    /**
     * Register this Look and Feel with the UIManager
     */
    public static void installLookAndFeel() {
        UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(
            "Plague Tale Theme", PlagueTaleLookAndFeel.class.getName()));
    }
    
    /**
     * Custom Metal theme implementation for Plague Tale.
     */
    private class PlagueTaleTheme extends DefaultMetalTheme {
        // Primary colors
        @Override
        public ColorUIResource getPrimary1() {
            return new ColorUIResource(DARK_BROWN);
        }
        
        @Override
        public ColorUIResource getPrimary2() {
            return new ColorUIResource(MEDIUM_BROWN);
        }
        
        @Override
        public ColorUIResource getPrimary3() {
            return new ColorUIResource(LIGHT_BROWN);
        }
        
        // Secondary colors
        @Override
        public ColorUIResource getSecondary1() {
            return new ColorUIResource(DARK_BROWN.darker());
        }
        
        @Override
        public ColorUIResource getSecondary2() {
            return new ColorUIResource(MEDIUM_BROWN.darker());
        }
        
        @Override
        public ColorUIResource getSecondary3() {
            return new ColorUIResource(PARCHMENT);
        }
        
        // Accent colors
        @Override
        public ColorUIResource getFocusColor() {
            return new ColorUIResource(AMBER);
        }
        
        // Font settings
        @Override
        public FontUIResource getControlTextFont() {
            return MEDIEVAL_FONT;
        }
        
        @Override
        public FontUIResource getSystemTextFont() {
            return MEDIEVAL_FONT;
        }
        
        @Override
        public FontUIResource getUserTextFont() {
            return MEDIEVAL_FONT;
        }
        
        @Override
        public FontUIResource getMenuTextFont() {
            return MEDIEVAL_FONT;
        }
        
        @Override
        public FontUIResource getWindowTitleFont() { 
            return new FontUIResource(MEDIEVAL_FONT.deriveFont(Font.BOLD, 16)); 
        }
        
        @Override
        public FontUIResource getSubTextFont() { 
            return new FontUIResource(MEDIEVAL_FONT.deriveFont(Font.PLAIN, 12)); 
        }
    }
    
    /**
     * Overrides the defaults to customize additional UI elements.
     */
    @Override
    public UIDefaults getDefaults() {
        UIDefaults defaults = super.getDefaults();
        
        // Custmize the UI components
        defaults.put("Panel.background", new ColorUIResource(PARCHMENT));
        defaults.put("OptionPane.background", new ColorUIResource(PARCHMENT));
        defaults.put("Button.background", new ColorUIResource(LIGHT_BROWN));
        defaults.put("Button.foreground", new ColorUIResource(DARK_BROWN));
        defaults.put("Button.select", new ColorUIResource(AMBER));
        defaults.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0))); // Transparent focus, remove the ugly border/outline around the button
        defaults.put("Button.border", new BorderUIResource.CompoundBorderUIResource(
            new LineBorder(DARK_BROWN, 1), 
            new EmptyBorder(5, 15, 5, 15)
        ));
        
        defaults.put("ComboBox.background", new ColorUIResource(PARCHMENT));
        defaults.put("ComboBox.foreground", new ColorUIResource(DARK_BROWN));
        defaults.put("ComboBox.selectionBackground", new ColorUIResource(AMBER));
        defaults.put("ComboBox.selectionForeground", new ColorUIResource(DARK_BROWN));
        
        defaults.put("Menu.background", new ColorUIResource(PARCHMENT));
        defaults.put("Menu.foreground", new ColorUIResource(DARK_BROWN));
        defaults.put("Menu.selectionBackground", new ColorUIResource(AMBER));
        defaults.put("Menu.selectionForeground", new ColorUIResource(DARK_BROWN));
        defaults.put("Menu.border", new BorderUIResource.CompoundBorderUIResource(
            new LineBorder(DARK_BROWN, 1), 
            new EmptyBorder(2, 5, 2, 5)
        ));
        
        defaults.put("MenuItem.background", new ColorUIResource(PARCHMENT));
        defaults.put("MenuItem.foreground", new ColorUIResource(DARK_BROWN));
        defaults.put("MenuItem.selectionBackground", new ColorUIResource(AMBER));
        defaults.put("MenuItem.selectionForeground", new ColorUIResource(DARK_BROWN));
        
        defaults.put("TabbedPane.background", new ColorUIResource(MEDIUM_BROWN));
        defaults.put("TabbedPane.foreground", new ColorUIResource(PARCHMENT));
        defaults.put("TabbedPane.selected", new ColorUIResource(LIGHT_BROWN));
        
        defaults.put("ScrollBar.thumb", new ColorUIResource(MEDIUM_BROWN));
        defaults.put("ScrollBar.thumbHighlight", new ColorUIResource(LIGHT_BROWN));
        defaults.put("ScrollBar.thumbShadow", new ColorUIResource(DARK_BROWN));
        defaults.put("ScrollBar.track", new ColorUIResource(PARCHMENT));
        return defaults;
    }
}
