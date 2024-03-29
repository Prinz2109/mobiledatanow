//==============================================================================
// OkCancelPanel.java
// Copyright (c) 2000 WAP Solutions Ltd.
//==============================================================================

package wsl.fw.gui;

// imports
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import wsl.fw.util.Util;
import wsl.fw.util.Log;
import wsl.fw.datasource.DataObject;
import wsl.fw.datasource.KeyConstraintException;

import wsl.fw.resource.ResId;

//------------------------------------------------------------------------------
/**
 * Panel allowing the user to edit and save changes to a DataObject.
 * The user may only choose Ok (Save) or Cancel after editing the properties.
 * Create the OkCancelPanel by passing a PropertiesPanel to its constructor
 */
public class OkCancelPanel extends WslButtonPanel implements ActionListener
{
    // version tag
    private final static String _ident = "$Date: 2002/06/11 23:11:42 $  $Revision: 1.1.1.1 $ "
        + "$Archive: /Framework/Source/wsl/fw/gui/OkCancelPanel.java $ ";

    // resources
    public static final ResId BUTTON_OK  = new ResId("OkCancelPanel.button.Ok");
    public static final ResId BUTTON_CANCEL  = new ResId("OkCancelPanel.button.Cancel");
    public static final ResId BUTTON_HELP  = new ResId("OkCancelPanel.button.Help");
    public static final ResId ERR_ERROR  = new ResId("OkCancelPanel.error.Error");
    public static final ResId ERR_UNEXPECTED  = new ResId("OkCancelPanel.error.Unexpected");

    // attributes
    private PropertiesPanel _pnlProps = null;
    private WslButton _btnOk = new WslButton(BUTTON_OK.getText(), this);
    private WslButton _btnCancel = new WslButton(BUTTON_CANCEL.getText(), this);
    private boolean _doSave = true;
    private boolean _isOk = false;


    //--------------------------------------------------------------------------
    /**
     * Blank constructor
     */
    public OkCancelPanel()
    {
        // delegate
        this(null);
    }

    //--------------------------------------------------------------------------
    /**
     * Constructor taking a PropertiesPanel
     * @param pnlProps The PropertiesPanel to set into the OkCancelPanel
     */
    public OkCancelPanel(PropertiesPanel pnlProps)
    {
        // super
        super(WslButtonPanel.VERTICAL);

        // init controls
        initControls(pnlProps);
        updateButtons();
    }

    //--------------------------------------------------------------------------
    /**
     * Create and init controls
     * @param pnlProps The PropertiesPanel to set into the MaintenancePanel
     */
    private void initControls(PropertiesPanel pnlProps)
    {
        // set the properties panel
        getMainPanel().setLayout(new GridBagLayout());
        if(pnlProps != null)
            setPropertiesPanel(pnlProps);

        // add buttons to buttons panel
        _btnOk.setIcon(Util.resourceIcon(GuiConst.FW_IMAGE_PATH + "save.gif"));
        _btnCancel.setIcon(Util.resourceIcon(GuiConst.FW_IMAGE_PATH + "close.gif"));
        addButton(_btnOk);
        addButton(_btnCancel);

        // add help button
        if (pnlProps != null)
            if (pnlProps.getHelpId() != null)
                addHelpButton(BUTTON_HELP.getText(), pnlProps.getHelpId());

        // add custom buttons
        if( pnlProps != null)
            addCustomButtons(_pnlProps.getCustomButtons());
    }

    //--------------------------------------------------------------------------
    /**
     * @param doSave if true, then the DataObject will be saved on Ok button pressed
     */
    public void setDoSave(boolean doSave)
    {
        _doSave = doSave;
    }

    //--------------------------------------------------------------------------
    /**
     * @return WslButton the default button
     */
    public WslButton getDefaultButton()
    {
        return _btnOk;
    }

    //--------------------------------------------------------------------------
    /**
     * Action performed
     */
    public void actionPerformed(ActionEvent ev)
    {
        try
        {
            // switch on source
            if(ev.getSource().equals(_btnOk))
                onOk();
            else if(ev.getSource().equals(_btnCancel))
                onCancel();

            // update buttons
            updateButtons();
        }
        catch(Exception e)
        {
            GuiManager.showErrorDialog(this, ERR_ERROR.getText(), e);
            Log.error(ERR_ERROR.getText(), e);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * @return PropertiesPanel
     * @exception
     */
    public PropertiesPanel getPropertiesPanel()
    {
        return _pnlProps;
    }

    //--------------------------------------------------------------------------
    /**
     * Set a properties panel into the maintenance panel
     */
    public void setPropertiesPanel(PropertiesPanel pnlProps)
    {
        // set the attribute
        _pnlProps = pnlProps;

        // set the maintenance parent
        _pnlProps.setMaintenanceParent(this);

        // add to the main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        _pnlProps.setBorder(BorderFactory.createLoweredBevelBorder());
        getMainPanel().add(_pnlProps, gbc);
    }

    //--------------------------------------------------------------------------
    /**
     * Ok button has been clicked. Save the DataObject and close the panel
     * @return void
     * @exception
     * @roseuid 3990C7AE0226
     */
    private void onOk() throws Exception
    {
        // check mandatories
        boolean ret = _pnlProps.checkMandatories();
        if(ret)
        {
            // transfer data
            _pnlProps.transferData(true);

            // save the DataObject
            if(_doSave)
            {
                try
                {
                    _pnlProps.getDataObject().save();
                }
                catch (KeyConstraintException e)
                {

                    GuiManager.showErrorDialog(this, e.getMessage(), null);
                    Log.debug("OkCancelPanel.onSave: ", e);
                    return;
                }
                catch (Exception e)
                {
                    GuiManager.showErrorDialog(this, ERR_UNEXPECTED.getText(), e);
                    Log.error(ERR_UNEXPECTED.getText(), e);
                    return;
                }
            }

            // set isOk flag
            _isOk = true;

            // close
            closePanel();
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Cancel button has been clicked. Close the panel
     * @return void
     * @exception
     * @roseuid 3990C7CF0165
     */
    private void onCancel()
    {
        closePanel();
    }

    //--------------------------------------------------------------------------
    /**
     * Enable / disable controls
     */
    public void updateButtons()
    {
        // notify PropertiesPanel
        if(getPropertiesPanel() != null)
            getPropertiesPanel().updateButtons();
    }

    //--------------------------------------------------------------------------
    /**
     * Override handling for panel closing.
     */
    public void onClosePanel()
    {
        // notify the contained properties panel
        if (_pnlProps != null)
            _pnlProps.onClosePanel();

        super.onClosePanel();
    }

    //--------------------------------------------------------------------------
    // misc

    /**
     * @return true if the Ok button was clicked to close this panel
     */
    public boolean isOk()
    {
        return _isOk;
    }
}

//==============================================================================
// end of file OkCancelPanel.java
//==============================================================================
