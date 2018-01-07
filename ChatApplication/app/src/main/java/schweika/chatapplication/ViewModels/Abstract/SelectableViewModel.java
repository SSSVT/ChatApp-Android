package schweika.chatapplication.ViewModels.Abstract;

import schweika.chatapplication.ViewModels.Interfaces.ISelectable;

public abstract class SelectableViewModel implements ISelectable
{
    protected boolean selected = false;

    public boolean getSelected()
    {
        return this.selected;
    }

    public void toggleSelected()
    {
        this.selected = !selected;
    }

    public void setSelected(boolean value)
    {
        this.selected = value;
    }
}
