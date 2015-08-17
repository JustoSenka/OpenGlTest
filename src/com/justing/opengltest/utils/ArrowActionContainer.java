package com.justing.opengltest.utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class ArrowActionContainer {
	
	protected short dx = 0, dy = 0, ex = 0, ey = 0, ez = 0;
	private final static ArrowActionContainer Instance = new ArrowActionContainer();
	private ArrowActionContainer(){}
	
	public static final ArrowActionContainer getInstance() {
		return Instance;
	}

	public void configureKeyBindings(JFrame frame) {
		JRootPane rootPane = frame.getRootPane();
        InputMap im = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = rootPane.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RightArrowDown");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LeftArrowDown");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UpArrowDown");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DownArrowDown");
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RightArrowUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LeftArrowUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "UpArrowUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "DownArrowUp");
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "S");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Space");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, false), "Shift");
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "dUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "aUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "wUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "sUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "SpaceUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, true), "ShiftUp");

        am.put("RightArrowDown", this.new ArrowAction("RightArrowDown"));
        am.put("LeftArrowDown", this.new ArrowAction("LeftArrowDown"));
        am.put("UpArrowDown", this.new ArrowAction("UpArrowDown"));
        am.put("DownArrowDown", this.new ArrowAction("DownArrowDown"));
        
        am.put("RightArrowUp", this.new ArrowAction("RightArrowUp"));
        am.put("LeftArrowUp", this.new ArrowAction("LeftArrowUp"));
        am.put("UpArrowUp", this.new ArrowAction("UpArrowUp"));
        am.put("DownArrowUp", this.new ArrowAction("DownArrowUp"));
        
        am.put("D", this.new ArrowAction("D"));
        am.put("A", this.new ArrowAction("A"));
        am.put("W", this.new ArrowAction("W"));
        am.put("S", this.new ArrowAction("S"));
        am.put("Space", this.new ArrowAction("Space"));
        am.put("Shift", this.new ArrowAction("Shift"));
        
        am.put("dUp", this.new ArrowAction("dUp"));
        am.put("aUp", this.new ArrowAction("aUp"));
        am.put("wUp", this.new ArrowAction("wUp"));
        am.put("sUp", this.new ArrowAction("sUp"));
        am.put("SpaceUp", this.new ArrowAction("SpaceUp"));
        am.put("ShiftUp", this.new ArrowAction("ShiftUp"));
	}
	
	public short getDx() {return dx;}
	public short getDy() {return dy;}
	public short getEx() {return ex;}
	public short getEy() {return ey;}
	public short getEz() {return ez;}
	
	@SuppressWarnings("serial")
	protected class ArrowAction extends AbstractAction {

		private String cmd;
		protected ArrowAction(String cmd) {
			this.cmd = cmd;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (cmd.equalsIgnoreCase("LeftArrowDown")) {
				dx = -1;
			} else if (cmd.equalsIgnoreCase("RightArrowDown")) {
				dx = 1;
			} else if (cmd.equalsIgnoreCase("UpArrowDown")) {
				dy = 1;
			} else if (cmd.equalsIgnoreCase("DownArrowDown")) {
				dy = -1;
			} else if (cmd.equalsIgnoreCase("LeftArrowUp")) {
				if (dx != 1)dx = 0;
			} else if (cmd.equalsIgnoreCase("RightArrowUp")) {
				if (dx != -1)dx = 0;
			} else if (cmd.equalsIgnoreCase("UpArrowUp")) {
				if (dy != -1)dy = 0;
			} else if (cmd.equalsIgnoreCase("DownArrowUp")) {
				if (dy != 1)dy = 0;
			} else if (cmd.equalsIgnoreCase("A")) {
				ex = -1;
			} else if (cmd.equalsIgnoreCase("D")) {
				ex = 1;
			} else if (cmd.equalsIgnoreCase("W")) {
				ez = -1;
			} else if (cmd.equalsIgnoreCase("S")) {
				ez = 1;
			} else if (cmd.equalsIgnoreCase("Space")) {
				ey = 1;
			} else if (cmd.equalsIgnoreCase("Shift")) {
				ey = -1;
			} else if (cmd.equalsIgnoreCase("aUp")) {
				if (ex != 1)ex = 0;
			} else if (cmd.equalsIgnoreCase("dUp")) {
				if (ex != -1)ex = 0;
			} else if (cmd.equalsIgnoreCase("wUp")) {
				if (ez != 1)ez = 0;
			} else if (cmd.equalsIgnoreCase("sUp")) {
				if (ez != -1)ez = 0;
			} else if (cmd.equalsIgnoreCase("SpaceUp")) {
				if (ey != -1)ey = 0;
			} else if (cmd.equalsIgnoreCase("ShiftUp")) {
				if (ey != 1)ey = 0;
			}
		}
	}
}