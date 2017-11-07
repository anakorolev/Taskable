package com.taskable.Vendor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Created by akorolev on 11/5/17.
 */

public class CustomizedButtonUI extends BasicButtonUI implements MouseListener {
    private Color hoverColor   = new Color(240, 240, 240);
    private Color     normalColor  = new Color(240, 240, 240);
    private Color     pressedColor = new Color(240, 240, 240);
    private Color     btnFontColor;
    private Color     pressedFontColor;
    private Color     hoverFontColor;
    private ImageIcon normalIcon;
    private ImageIcon hoverIcon;
    private ImageIcon pressedIcon;
    private Font      btnFont;

    public CustomizedButtonUI() {
        super();
    }

    public CustomizedButtonUI(Color normalColor, Color hoverColor, Color pressedColor, ImageIcon icon) {
        this(normalColor, hoverColor, pressedColor, null, Color.BLACK, Color.BLACK, Color.BLACK, icon);
    }
    public CustomizedButtonUI(Color normalColor, Color hoverColor, Color pressedColor, Font btnFont, Color btnFontColor, Color pressedFontColor, Color hoverFontColor, ImageIcon icon) {
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.pressedColor = pressedColor;
        this.btnFont = btnFont;
        this.btnFontColor = btnFontColor;
        this.pressedFontColor = pressedFontColor;
        this.hoverFontColor = hoverFontColor;
        this.normalIcon = icon;
    }

    public CustomizedButtonUI(ImageIcon normalIcon, ImageIcon hoverIcon, ImageIcon pressedIcon) {
        this(normalIcon, hoverIcon, pressedIcon, null, Color.BLACK);
    }
    public CustomizedButtonUI(ImageIcon normalIcon, ImageIcon hoverIcon, ImageIcon pressedIcon, Font btnFont, Color btnFontColor) {
        this.normalIcon = normalIcon;
        this.hoverIcon = hoverIcon;
        this.pressedIcon = pressedIcon;
        this.btnFont = btnFont;
        this.btnFontColor = btnFontColor;
    }

    @Override
    public void installUI(JComponent comp) {
        super.installUI(comp);
        comp.addMouseListener(this);
    }

    @Override
    public void uninstallUI(JComponent comp) {
        super.uninstallUI(comp);
        comp.removeMouseListener(this);
    }

    @Override
    protected void installDefaults(AbstractButton btn) {
        super.installDefaults(btn);
        btn.setIcon(this.normalIcon);
        btn.setBackground(this.normalColor);
        btn.setForeground(this.btnFontColor);
        btn.setFont(this.btnFont);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    public Dimension getPreferredSize(JComponent comp) {
        Dimension dim = super.getPreferredSize(comp);
        if(comp.getBorder() != null){
            Insets insets = comp.getBorder().getBorderInsets(comp);
            dim.setSize(dim.width + insets.left + insets.right, dim.height + insets.top + insets.bottom);
        }
        return dim;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        changeButtonStyle((JButton) e.getComponent(), this.pressedColor, this.normalIcon, this.pressedFontColor);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        changeButtonStyle((JButton) e.getComponent(), this.pressedColor, this.normalIcon, this.pressedFontColor);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        changeButtonStyle((JButton)e.getComponent(), this.normalColor, this.normalIcon, this.btnFontColor);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        changeButtonStyle((JButton) e.getComponent(), this.hoverColor, this.normalIcon, this.hoverFontColor);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        changeButtonStyle((JButton)e.getComponent(), this.normalColor, this.normalIcon, this.btnFontColor);
    }

    private void changeButtonStyle(JButton btn, Color color, ImageIcon icon, Color fontColor){
        btn.setBackground(color);
        btn.setIcon(icon);
        btn.setForeground(fontColor);
    }
}
