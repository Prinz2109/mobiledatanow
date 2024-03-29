/**	$Id: MdnHelpAboutPanel.java,v 1.5 2004/10/21 01:17:58 tecris Exp $
 *
 * Help dialog from MDN administrator.
 *
 */
package wsl.mdn.admin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.JLabel;

import wsl.fw.gui.GuiConst;
import wsl.fw.gui.WslButton;
import wsl.fw.gui.WslPanel;
import wsl.fw.resource.ResId;
import wsl.fw.util.Util;
import wsl.mdn.common.MdnAdminConst;
import wsl.mdn.server.MdnServer;

public class MdnHelpAboutPanel extends WslPanel
{
	// resources
	public static final ResId TITLE  = new ResId ("MdnAdminFrame.title");

	public
	MdnHelpAboutPanel ()
	{
		this.setPanelTitle ("About " + TITLE.getText ());

		// Initialise controls
		initControls ();
	}

	/**
	 * Initialise controls.
	 */
	private void
	initControls ()
	{
		setLayout (new GridBagLayout ());
		GridBagConstraints gbc = new GridBagConstraints ();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets.top = GuiConst.DEFAULT_INSET;
		gbc.insets.left = GuiConst.DEFAULT_INSET;
		gbc.insets.bottom = GuiConst.DEFAULT_INSET;
		gbc.insets.right = GuiConst.DEFAULT_INSET;

		// Name control
		JLabel lbl = new JLabel (TITLE.getText ());
		lbl.setFont (new Font (null, Font.BOLD, 30));

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets.left = GuiConst.DEFAULT_INSET;
		gbc.insets.top = GuiConst.DEFAULT_INSET;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;//0.2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		//add (lbl, gbc);
		//gbc.gridx = 1;
		//gbc.weightx = 0.8;
		////gbc.insets.right = GuiConst.DEFAULT_INSET;
		//add (_txtName, gbc);

		//gbc.gridy = 0;
		//Dimension dMDN = new Dimension (150, 30);
		//WslButton btnMDN = new WslButton (Util.resourceIcon (MdnAdminConst.SS_IMAGE_PATH + "mdn.gif"), dMDN, null);
		//Image img = java.awt.Toolkit.getDefaultToolkit ().createImage ("D://classes//wsl//mdn//resource//images//mdn.gif");
		//JButton btn = new JButton ();
		//btn.setIcon (Util.resourceIcon (GuiConst.FW_IMAGE_PATH + "mdn.gif"));
		//this.setIconImage (img);
		//add (btnMDN, gbc);
		//gbc.gridx = 1;
		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		//add (new JLabel (""), gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		Dimension dWSL = new Dimension (220, 150);
		gbc.gridheight = 4;
		WslButton btnWSL = new WslButton (Util.resourceIcon (MdnAdminConst.SS_IMAGE_PATH + "logo_small.gif"), dWSL, null);
		gbc.weightx = 0.2;
		add (btnWSL, gbc);

		gbc.gridheight = 1;

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JLabel title = new JLabel (TITLE.getText ());
		title.setFont (new Font (null, Font.BOLD, 14));
		add (title, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add (new JLabel (MdnServer.TEXT_VERSION.getText () + " " 
			+ MdnServer.VERSION_NUMBER), gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		//gbc.gridheight = GridBagConstraints.REMAINDER;
		add (new JLabel ("Copyright (c) 2004 Firetrust Mobile Data Now Ltd"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		add (new JLabel ("All Rights Reserved"), gbc);

		// controls
		//_btnClose.setIcon (Util.resourceIcon (GuiConst.FW_IMAGE_PATH + "close.gif"));
	}
}
