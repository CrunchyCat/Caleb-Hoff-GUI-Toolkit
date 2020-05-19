package CalebHoff;

public class JFrameCH extends javax.swing.JFrame
{
	private static final long serialVersionUID = 1L;
	private static final java.awt.Color COLOR_TITLEBAR_BACKGROUND_DEFAULT = javax.swing.UIManager.getColor("Frame.background"),
										COLOR_TITLEBAR_FOREGROUND_DEFAULT = javax.swing.UIManager.getColor("Frame.foreground"),
										COLOR_TITLEBAR_HOVER_DEFAULT = javax.swing.UIManager.getColor("Button.darkShadow"),
										COLOR_TITLEBAR_CLOSE_DEFAULT = new java.awt.Color(232, 17, 35);
	private static final java.awt.Font FONT_TITLEBAR_DEFAULT = javax.swing.UIManager.getFont("Label.font");
	private javax.swing.JPanel pnlTitleBar, pnlMain;
	private javax.swing.JLabel lblTitle;
	private javax.swing.JButton btnMin, btnMax, btnClose;
	private int xx, xy;
	private ComponentResizer cr; //TODO Implement this
	private java.awt.Color color_TitleBarBackground = COLOR_TITLEBAR_BACKGROUND_DEFAULT,
							color_TitleBarForeground = COLOR_TITLEBAR_FOREGROUND_DEFAULT,
							color_TitleBarHover = COLOR_TITLEBAR_HOVER_DEFAULT,
							color_TitleBarClose = COLOR_TITLEBAR_CLOSE_DEFAULT;
	private java.awt.Font font_TitleBar = FONT_TITLEBAR_DEFAULT;

	public JFrameCH()
	{
		super();
		init();
	}
	
	public JFrameCH(java.awt.GraphicsConfiguration gc)
	{
		super(gc);
		init();
	}
	
	public JFrameCH(String title)
	{
		super(title);
		init();
	}
	
	public JFrameCH(String title, java.awt.GraphicsConfiguration gc)
	{
		super(title, gc);
		init();
	}
	
	private void init()
	{
		cr = new ComponentResizer();
		cr.registerComponent(this);
		setUndecorated(true);
		super.setLayout(new java.awt.BorderLayout());
		
		pnlMain = new javax.swing.JPanel();
		pnlMain.setBackground(COLOR_TITLEBAR_BACKGROUND_DEFAULT);
		
		pnlTitleBar = new javax.swing.JPanel();
		pnlTitleBar.setPreferredSize(new java.awt.Dimension(0, 30));
		pnlTitleBar.setBackground(color_TitleBarBackground);
		pnlTitleBar.setLayout(new java.awt.BorderLayout());
		
		lblTitle = new javax.swing.JLabel("  " + getTitle());
		lblTitle.setFont(font_TitleBar);
		lblTitle.setForeground(color_TitleBarForeground);
		
		javax.swing.JPanel pnlActions = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));
		pnlActions.setOpaque(false);
		
		btnMin = new javax.swing.JButton();
		btnMax = new javax.swing.JButton();
		btnClose = new javax.swing.JButton();
		
		if (OSUtils.getOS() == OSUtils.OS.MacOS)
		{
			btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_close_mac.png")));
			btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_min_mac.png")));
			btnMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_max_mac.png")));
			pnlActions.add(btnClose);
			pnlActions.add(btnMin);
			pnlActions.add(btnMax);
			pnlTitleBar.add(lblTitle, java.awt.BorderLayout.CENTER);
			pnlTitleBar.add(pnlActions, java.awt.BorderLayout.WEST);
			lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		}
		else
		{
			btnMin.setIcon(filterResource("/img/icon_min.png", color_TitleBarForeground));
			btnMax.setIcon(filterResource("/img/icon_max.png", color_TitleBarForeground));
			btnClose.setIcon(filterResource("/img/icon_close.png", color_TitleBarForeground));
			pnlActions.add(btnMin);
			pnlActions.add(btnMax);
			pnlActions.add(btnClose);
			pnlTitleBar.add(lblTitle, java.awt.BorderLayout.WEST);
			pnlTitleBar.add(pnlActions, java.awt.BorderLayout.EAST);
		}
		
		for (java.awt.Component btn : pnlActions.getComponents())
		{
			btn.setBackground(color_TitleBarBackground);
			((javax.swing.JButton)btn).setContentAreaFilled(false);
			((javax.swing.JButton)btn).setOpaque(true);
			btn.setFocusable(false);
		}
		
		super.add(pnlTitleBar, java.awt.BorderLayout.NORTH);
		super.add(pnlMain, java.awt.BorderLayout.CENTER);
			
		pnlTitleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
		    public void mouseDragged(java.awt.event.MouseEvent e) {
		    	if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH)
		    		setExtendedState(javax.swing.JFrame.NORMAL);
		    	setLocation(e.getXOnScreen() - xx, e.getYOnScreen() - xy);
		    }
		});
		pnlTitleBar.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		    	if (e.getClickCount() == 2 && !e.isConsumed()) {
		            if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH)
		            	setExtendedState(javax.swing.JFrame.NORMAL);
		            else
		            	setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		        }
		    }
		    public void mousePressed(java.awt.event.MouseEvent e) {
		    	xx = e.getX();
		        xy = e.getY();
		    }
		});
		btnMin.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		    	setState(javax.swing.JFrame.ICONIFIED);
		    }
		    public void mouseEntered(java.awt.event.MouseEvent e) {
				btnMin.setBackground(color_TitleBarHover);
			}
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnMin.setBackground(color_TitleBarBackground);
			}
		});
		btnMax.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		    	if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH)
		    		setExtendedState(javax.swing.JFrame.NORMAL);
				else
					setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		    }
		    public void mouseEntered(java.awt.event.MouseEvent e) {
				btnMax.setBackground(color_TitleBarHover);
			}
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnMax.setBackground(color_TitleBarBackground);
			}
		});
		btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		    	System.exit(0);
		    }
		    public void mouseEntered(java.awt.event.MouseEvent e) {
				btnClose.setBackground(color_TitleBarClose);
			}
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnClose.setBackground(color_TitleBarBackground);
			}
		});
	}
	
	/**
	 * Returns the background color of the main panel
	 */
	public java.awt.Color getFrameBackground()
	{
		return pnlMain.getBackground();
	}
	
	/**
	 * Returns the title bar background Color 
	 * @return title bar background color
	 */
	public java.awt.Color getTitleBarBackground()
	{
		return color_TitleBarBackground;
	}
	
	/**
	 * Returns the title bar font
	 * @return title bar font
	 */
	public java.awt.Font getTitleBarFont()
	{
		return font_TitleBar;
	}
	
	/**
	 * Returns the title bar foreground Color 
	 * @return title bar foreground color
	 */
	public java.awt.Color getTitleBarForeground()
	{
		return color_TitleBarForeground;
	}
	
	/**
	 * Sets the background color for the main panel
	 */
	public void setFrameBackground(java.awt.Color colorBG)
	{
		pnlMain.setBackground(colorBG);
	}
	
	/**
	 * Sets the maximum size for the frame
	 */
	@Override
	public void setMaximumSize(java.awt.Dimension maximumSize)
	{
		super.setMinimumSize(maximumSize);
		cr.setMinimumSize(maximumSize);
	}
	
	/**
	 * Sets the minimum size for the frame
	 */
	@Override
	public void setMinimumSize(java.awt.Dimension minimumSize)
	{
		super.setMinimumSize(minimumSize);
		cr.setMinimumSize(minimumSize);
	}
	
	/**
	 * Sets the maximum size for the frame
	 */
	@Override
	public void setTitle(String title)
	{
		super.setTitle(title);
		lblTitle.setText("  " + title);
	}
	
	/**
	 * Sets the title bar background Color 
	 */
	public void setTitleBarBackground(java.awt.Color colorBG)
	{
		color_TitleBarBackground = colorBG;
		color_TitleBarHover = colorBG.getRed() + colorBG.getBlue() + colorBG.getGreen() < java.awt.Color.WHITE.getRed()
				? colorBG.brighter().brighter() : colorBG.darker();
		pnlTitleBar.setBackground(color_TitleBarBackground);
		btnMin.setBackground(color_TitleBarBackground);
		btnMax.setBackground(color_TitleBarBackground);
		btnClose.setBackground(color_TitleBarBackground);
	}
	
	/**
	 * Sets the title bar font
	 */
	public void setTitleBarFont(java.awt.Font font)
	{
		font_TitleBar = font;
		lblTitle.setFont(font_TitleBar);
	}
	
	/**
	 * Sets the title bar foreground Color 
	 */
	public void setTitleBarForeground(java.awt.Color colorFG)
	{
		color_TitleBarForeground = colorFG;
		lblTitle.setForeground(color_TitleBarForeground);
		if (OSUtils.getOS() == OSUtils.OS.Windows)
		{
			btnMin.setIcon(filterResource("/img/icon_min.png", colorFG));
			btnMax.setIcon(filterResource("/img/icon_max.png", colorFG));
			btnClose.setIcon(filterResource("/img/icon_close.png", colorFG));
		}
	}
	
	private javax.swing.ImageIcon filterResource(String resource, java.awt.Color color)
	{
		try
		{
			java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(this.getClass().getResource(resource));
			java.awt.Graphics2D graphics = img.createGraphics();
		    graphics.setXORMode(color);
		    graphics.drawImage(img, null, 0, 0);
		    graphics.dispose();
		    return new javax.swing.ImageIcon(img);
		}
		catch (Exception e) {}
			return new javax.swing.ImageIcon(getClass().getResource(resource));
	}
	
	public static class OSUtils
	{
		public static enum OS {Windows, MacOS, Linux, Other}
		
		public static OS getOS()
		{
				String os = System.getProperty("os.name", "generic").toLowerCase(java.util.Locale.ENGLISH);
				if (os.contains("mac") || os.indexOf("darwinf") >= 0)
					return OS.MacOS;
				if (os.contains("win"))
					return OS.Windows;
				if (os.contains("nux"))
					return OS.Linux;
				return OS.Other;
		}
	}
}