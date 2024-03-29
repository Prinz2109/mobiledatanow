//==============================================================================
// ShowMainMenu.java
// Copyright (c) 2001 WAP Solutions Ltd.
//==============================================================================

package wsl.mdn.guiconfig;


//------------------------------------------------------------------------------
/**
 */
public class ShowMainMenu extends MenuAction
{
    // version tag
    private final static String _ident = "$Date: 2002/06/11 23:35:35 $  $Revision: 1.1.1.1 $ "
        + "$Archive: /Mobile Data Now/Source/wsl/mdn/guiconfig/ShowMainMenu.java $ ";

    //--------------------------------------------------------------------------
    /**
     * Default constructor.
     */
    public ShowMainMenu()
    {
    }
    //--------------------------------------------------------------------------
    /**
     * Full constructor.
     * @param name, name of the action.
     * @param description, description of the action.
     * @param groupId, id of the group that owns this menu
     * @param parentMenuId, id of the paren MenuAction that contains this.
     */
    public ShowMainMenu(String name, String description, Object groupId,
        Object parentMenuId)
    {
        super(name, description, groupId, parentMenuId);
    }
}

//==============================================================================
// end of file ShowMainMenu.java
//==============================================================================
