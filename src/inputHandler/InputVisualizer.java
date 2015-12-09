package inputHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import busInterface.Public_In;
import driverinput.DriverInput;
import virtualDataBus.Container.ShiftLeverPosition;

/*
 * Components in order:
 * 		brake pedal
 * 		gas pedal
 * 		steering wheel
 * 		shift lever
 * 		engine toggle button
 */
public class InputVisualizer extends JFrame {
	private final static int FrameWidth = 400;
	private final static int FrameHeight = 200;
	private final static int ElementPadding = 20;
	private final static int WheelRadius = 50;
	private final static double InnerWheelRatio = 0.8;
	private final static double WheelMovementScaleRatio = 2.0;
	private final static int PedalBoxWidth = 20;
	private final static int PedalBoxHeight = 100;
	private final static int PedalBorderSize = 1;
	private final static int GearBoxOffset = 25;
	private final static int GearLevelPadding = 20;
	private final static int EngineButtonRadius = 10;
	private final static int EngineButtonMargin = 5;

	private final JPanel mainPanel;
	
	private Public_In in;
	
	private void drawSteeringWheel(Graphics g, int panelWidth, int panelHeight) {
		// outer circle
		int x = panelWidth / 2 - WheelRadius;
		int y = panelHeight / 2 - WheelRadius;
		Color defaultColor = this.getBackground();
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(x, y, WheelRadius * 2, WheelRadius * 2);

		// inner circle
		int innerWheelRadius = (int) (WheelRadius * InnerWheelRatio);

		x = panelWidth / 2 - innerWheelRadius;
		y = panelHeight / 2 - innerWheelRadius;
		g.setColor(defaultColor);
		g.fillOval(x, y, innerWheelRadius * 2, innerWheelRadius * 2);

		double steeringWheelRotation = in.getSteeringWheelSignedPercentage();
		// drawing the 'needle'
		{
			double needleRadius = (WheelRadius - innerWheelRadius) / 2;
			double needleRotation = (steeringWheelRotation * WheelMovementScaleRatio - 90);
			double needleLength = innerWheelRadius + needleRadius;

			x = (int) (panelWidth / 2 - needleRadius + Math.cos(Math.toRadians(needleRotation)) * needleLength);
			y = (int) (panelHeight / 2 - needleRadius + Math.sin(Math.toRadians(needleRotation)) * needleLength);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(x, y, (int) needleRadius * 2, (int) needleRadius * 2);
		}

		// drawing the value in text
		x = panelWidth / 2 - g.getFontMetrics().stringWidth(steeringWheelRotation + "%") / 2;
		y = panelHeight / 2;
		g.setColor(Color.BLACK);
		g.drawString(steeringWheelRotation + "%", x, y);
	}

	private void drawPedals(Graphics g, int panelWidth, int panelHeight) {
		// drawing the box for gas
		int x = panelWidth / 2 - WheelRadius - ElementPadding - PedalBoxWidth - 2 * PedalBorderSize;
		int y = panelHeight / 2 - PedalBoxHeight / 2 - 2 * PedalBorderSize;
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x, y, PedalBoxWidth + PedalBorderSize, PedalBoxHeight + PedalBorderSize);

		// drawing the fill in the gas box
		int valueScaleRatio = (int) (PedalBoxHeight / DriverInput.MaxPedalPushValue);
		double valueDiffFromMax = DriverInput.MaxPedalPushValue - in.getGasPedalPercentage();
		int tempGasPedalBoxX = x;
		int tempGasPedalBoxY = y;
		x += PedalBorderSize;
		y += valueDiffFromMax * valueScaleRatio + PedalBorderSize;
		g.setColor(Color.GREEN);
		g.fillRect(x, y, PedalBoxWidth, (int) (PedalBoxHeight - (valueDiffFromMax * valueScaleRatio)));

		// drawing the box for brake
		x = tempGasPedalBoxX - ElementPadding - PedalBoxWidth - 2 * PedalBorderSize;
		y = tempGasPedalBoxY;
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x, y, PedalBoxWidth + PedalBorderSize, PedalBoxHeight + PedalBorderSize);

		// drawing the fill in the brake box
		valueDiffFromMax = DriverInput.MaxPedalPushValue - in.getBrakePedalPercentage();
		x += PedalBorderSize;
		y += valueDiffFromMax * valueScaleRatio + PedalBorderSize;
		g.setColor(Color.RED);
		g.fillRect(x, y, PedalBoxWidth, (int) (PedalBoxHeight - (valueDiffFromMax * valueScaleRatio)));
	}

	private void drawGear(Graphics g, int panelWidth, int panelHeight) {
		int x = panelWidth / 2 + WheelRadius + ElementPadding * 2;
		int y = panelHeight / 2 - WheelRadius + GearBoxOffset;

		ShiftLeverPosition shiftLeverPosition = in.getShiftLeverPosition();
		// parking
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == ShiftLeverPosition.Parking)
			g.setColor(Color.BLUE);
		g.drawString("P", x, y);
		y += GearLevelPadding;

		// reverse
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == ShiftLeverPosition.Reverse)
			g.setColor(Color.BLUE);
		g.drawString("R", x, y);
		y += GearLevelPadding;

		// neutral
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == ShiftLeverPosition.Neutral)
			g.setColor(Color.BLUE);
		g.drawString("N", x, y);
		y += GearLevelPadding;

		// drive
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == ShiftLeverPosition.Drive)
			g.setColor(Color.BLUE);
		g.drawString("D", x, y);
	}

	private void drawEngineButton(Graphics g, int panelWidth, int panelHeight) {
		int x = panelWidth - EngineButtonRadius - EngineButtonMargin;
		int y = panelHeight - EngineButtonRadius - EngineButtonMargin;

		if (in.getEngineToggleButtonState())
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.fillOval(x, y, EngineButtonRadius, EngineButtonRadius);
		g.setColor(Color.DARK_GRAY);
		g.drawOval(x, y, EngineButtonRadius, EngineButtonRadius);
	}
	
	private void drawComponents(Graphics g) {
		int panelWidth = g.getClipBounds().width;
		int panelHeight = g.getClipBounds().height;

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawSteeringWheel(g, panelWidth, panelHeight);
		drawPedals(g, panelWidth, panelHeight);
		drawGear(g, panelWidth, panelHeight);
		drawEngineButton(g, panelWidth, panelHeight);
	}
	
	public void repaintInputs() {
		this.repaint();
	}

	public InputVisualizer(Public_In in) {
		super("Driver Input");
		this.in = in;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FrameWidth, FrameHeight);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - FrameWidth / 2, dim.height / 2 - FrameHeight / 2);
		this.setLayout(new BorderLayout());
		
		this.mainPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				drawComponents(g);
			}
		};
		
		this.add(mainPanel, BorderLayout.CENTER);		
		this.setVisible(true);
	}
}
