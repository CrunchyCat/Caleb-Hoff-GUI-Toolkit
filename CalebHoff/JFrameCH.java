package CalebHoff;

public class JFrameCH extends javax.swing.JFrame
{
	private static final long serialVersionUID = 1L;
	private static final java.awt.Color COLOR_TITLEBAR_BACKGROUND_DEFAULT = javax.swing.UIManager.getColor("Frame.background"),
										COLOR_TITLEBAR_FOREGROUND_DEFAULT = javax.swing.UIManager.getColor("Frame.foreground"),
										COLOR_TITLEBAR_HOVER_DEFAULT = javax.swing.UIManager.getColor("Button.shadow"),
										COLOR_TITLEBAR_CLOSE_DEFAULT = new java.awt.Color(232, 17, 35);
	private static final java.awt.Font FONT_TITLEBAR_DEFAULT = javax.swing.UIManager.getFont("Label.font");
	private javax.swing.JPanel pnlTitleBar;
	private javax.swing.JLabel lblTitle;
	private javax.swing.JButton btnMin, btnMax, btnClose;
	private javax.swing.JMenuItem mniRestore, mniMove, mniSize, mniMin, mniMax;
	private int xx, xy;
	private ComponentResizer cr;
	private java.awt.Color color_TitleBarBackground = COLOR_TITLEBAR_BACKGROUND_DEFAULT,
							color_TitleBarForeground = COLOR_TITLEBAR_FOREGROUND_DEFAULT,
							color_TitleBarHover = COLOR_TITLEBAR_HOVER_DEFAULT,
							color_TitleBarClose = COLOR_TITLEBAR_CLOSE_DEFAULT;
	private java.awt.Font font_TitleBar = FONT_TITLEBAR_DEFAULT;

	/**
	 * Constructs a new JFrameCH that is initially invisible.
	 * This constructor sets the component's locale property to the value returned by
	 * JComponent.getDefaultLocale.
	 */
	public JFrameCH()
	{
		super();
		init();
	}
	
	/**
	 * Creates a JFrameCH in the specified GraphicsConfiguration of a screen device and a blank title.
	 * This constructor sets the component's locale property to the value returned by
	 * JComponent.getDefaultLocale.
	 * @param gc - the graphics configuration used to construct the new frame
	 */
	public JFrameCH(java.awt.GraphicsConfiguration gc)
	{
		super(gc);
		init();
	}
	
	/**
	 * Creates a new, initially invisible JFrameCH with the specified title. 
	 * This constructor sets the component's locale property to the value returned by
	 * JComponent.getDefaultLocale.
	 * @param title - the title of the new frame
	 */
	public JFrameCH(String title)
	{
		super(title);
		init();
	}
	
	/**
	 * Creates a JFrameCH in the specified GraphicsConfiguration of a screen device and specified title.
	 * This constructor sets the component's locale property to the value returned by
	 * JComponent.getDefaultLocale.
	 * @param title - the title of the new frame
	 * @param gc - the graphics configuration used to construct the new frame
	 */
	public JFrameCH(String title, java.awt.GraphicsConfiguration gc)
	{
		super(title, gc);
		init();
	}
	
	/**
	 * Converts ordinary JFrame to JFrameCH by creating a title bar,
	 * title bar popup menu, window movement/size control
	 */
	private void init()
	{
		cr = new ComponentResizer();
		cr.registerComponent(this);
		setUndecorated(true);
		super.setLayout(new java.awt.BorderLayout());
		
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
		mniRestore = new javax.swing.JMenuItem("Restore");
		mniMove = new javax.swing.JMenuItem("Move");
		mniSize = new javax.swing.JMenuItem("Size");
		mniMin = new javax.swing.JMenuItem("Minimize");
		mniMax = new javax.swing.JMenuItem("Maximize");
		
		if (OSUtils.getOS() == OSUtils.OS.MacOS) //Adds macOS features
		{ //TODO: Fix Button Highlighting on MacOS
			btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_close_mac.png")));
			btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_min_mac.png")));
			btnMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_max_mac.png")));
			btnClose.setPreferredSize(new java.awt.Dimension(25, 20));
			btnMin.setPreferredSize(new java.awt.Dimension(25, 20));
			btnMax.setPreferredSize(new java.awt.Dimension(25, 20));
			pnlActions.add(btnClose);
			pnlActions.add(btnMin);
			pnlActions.add(btnMax);
			pnlTitleBar.add(lblTitle, java.awt.BorderLayout.CENTER);
			pnlTitleBar.add(pnlActions, java.awt.BorderLayout.WEST);
			lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		}
		else //Adds Windows features
		{
			javax.swing.JPopupMenu mnuTitleBar = new javax.swing.JPopupMenu();
			mnuTitleBar.setBorderPainted(false);
			pnlTitleBar.setComponentPopupMenu(mnuTitleBar);
			javax.swing.JMenuItem mniClose = new javax.swing.JMenuItem("Close"); 		
			mniRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pop_restore.png")));
			mniRestore.setEnabled(false);
			mniMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pop_min.png")));
			mniMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pop_max.png")));
			mniMax.setFont(new java.awt.Font(mniMax.getFont().getName(), java.awt.Font.BOLD, mniMax.getFont().getSize()));
			mniClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pop_close.png")));
			mniClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.ActionEvent.ALT_MASK));
			
			mnuTitleBar.add(mniRestore);
			mnuTitleBar.add(mniMove); //TODO: Add Move Functionality
			mnuTitleBar.add(mniSize); //TODO: Add Size Functionality
			mnuTitleBar.add(mniMin);
			mnuTitleBar.add(mniMax);
			mnuTitleBar.addSeparator();
			mnuTitleBar.add(mniClose);
			
			mniRestore.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if (e.getButton() == java.awt.event.MouseEvent.BUTTON1)
						setWindowState(javax.swing.JFrame.NORMAL);
				}
			});
			mniMin.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if (e.getButton() == java.awt.event.MouseEvent.BUTTON1)
						setWindowState(javax.swing.JFrame.ICONIFIED);
				}
			});
			mniMax.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if (e.getButton() == java.awt.event.MouseEvent.BUTTON1)
						setWindowState(javax.swing.JFrame.MAXIMIZED_BOTH);
				}
			});
			mniClose.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if (e.getButton() == java.awt.event.MouseEvent.BUTTON1)
						System.exit(0);
				}
			});
			
			btnMin.setIcon(filterResource("/img/icon_min.png", color_TitleBarForeground));
			btnMax.setIcon(filterResource("/img/icon_max.png", color_TitleBarForeground));
			btnClose.setIcon(filterResource("/img/icon_close.png", color_TitleBarForeground));
			pnlActions.add(btnMin);
			pnlActions.add(btnMax);
			pnlActions.add(btnClose);
			pnlTitleBar.add(lblTitle, java.awt.BorderLayout.WEST);
			pnlTitleBar.add(pnlActions, java.awt.BorderLayout.EAST);
		}
		
		for (java.awt.Component btn : pnlActions.getComponents()) //Sets up action buttons
		{
			btn.setBackground(color_TitleBarBackground);
			((javax.swing.JButton)btn).setContentAreaFilled(false);
			((javax.swing.JButton)btn).setOpaque(true);
			btn.setFocusable(false);
		}
		
		super.add(pnlTitleBar, java.awt.BorderLayout.NORTH);
			
		pnlTitleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
		    public void mouseDragged(java.awt.event.MouseEvent e) {
		    	if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH)
		    		setWindowState(javax.swing.JFrame.NORMAL);
		    	setLocation(e.getXOnScreen() - xx, e.getYOnScreen() - xy);
		    }
		});
		pnlTitleBar.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		    	if (e.getButton() != java.awt.event.MouseEvent.BUTTON1)
					return;
		    	if (e.getClickCount() == 2 && !e.isConsumed()) {
		    		if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH)
			    		setWindowState(javax.swing.JFrame.NORMAL);
					else
						setWindowState(javax.swing.JFrame.MAXIMIZED_BOTH);
		        }
		    }
		    public void mousePressed(java.awt.event.MouseEvent e) {
		    	xx = e.getX();
		        xy = e.getY();
		    }
		});
		btnMin.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		    	if (e.getButton() == java.awt.event.MouseEvent.BUTTON1)
		    		setWindowState(javax.swing.JFrame.ICONIFIED);
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
		    	if (e.getButton() != java.awt.event.MouseEvent.BUTTON1)
					return;
		    	if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH)
		    		setWindowState(javax.swing.JFrame.NORMAL);
				else
					setWindowState(javax.swing.JFrame.MAXIMIZED_BOTH);
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
		    	if (e.getButton() == java.awt.event.MouseEvent.BUTTON1)
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
	
	/**
	 * Sets if window is maximized, normal, or minimized
	 * @param state - Maximized (6), Normal (0), or Minimized (1)
	 */
	private void setWindowState(int state)
	{
		if (state == javax.swing.JFrame.MAXIMIZED_BOTH) //Maximize Window
		{
			setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
			mniRestore.setEnabled(true);
			mniMove.setEnabled(false);
			mniSize.setEnabled(false);
			mniMin.setEnabled(true);
			mniMax.setEnabled(false);
			mniMax.setFont(new java.awt.Font(mniMax.getFont().getName(), java.awt.Font.PLAIN, mniMax.getFont().getSize()));
		}
		else if (state == javax.swing.JFrame.ICONIFIED) //Minimize Window
		{
			setExtendedState(javax.swing.JFrame.ICONIFIED);
			mniRestore.setEnabled(true);
			mniMove.setEnabled(false);
			mniSize.setEnabled(false);
			mniMin.setEnabled(false);
			mniMax.setEnabled(true);
			mniMax.setFont(new java.awt.Font(mniMax.getFont().getName(), java.awt.Font.PLAIN, mniMax.getFont().getSize()));
		}
		else //Windowify window
		{
			setExtendedState(javax.swing.JFrame.NORMAL);
			mniRestore.setEnabled(false);
			mniMove.setEnabled(true);
			mniSize.setEnabled(true);
			mniMin.setEnabled(true);
			mniMax.setEnabled(true);
			mniMax.setFont(new java.awt.Font(mniMax.getFont().getName(), java.awt.Font.BOLD, mniMax.getFont().getSize()));
		}
	}
	
	/**
	 * Applies color tint to an image resource at a URL
	 * @param resource - image specified by URL string
	 * @param color - the color to tint the image
	 * @return the tinted image as an ImageIcon
	 */
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
	
	/**
	 * Determines the host operating system
	 * @author Caleb
	 */
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