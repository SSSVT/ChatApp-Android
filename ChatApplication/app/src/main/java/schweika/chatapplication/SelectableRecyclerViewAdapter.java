package schweika.chatapplication;

import java.util.ArrayList;

import schweika.chatapplication.ViewModels.Abstract.SelectableViewModel;
import schweika.chatapplication.ViewModels.Interfaces.ISelectable;

public class SelectableRecyclerViewAdapter<T extends ISelectable> extends GenericRecyclerViewAdapter<T>
{
    public SelectableRecyclerViewAdapter(ArrayList<T> list, int layoutId)
    {
        super(list, layoutId);
    }

    public ArrayList<T> getSelectedItems()
    {
        ArrayList<T> selectedItems = new ArrayList<>();

        for (T item : this.items)
        {
            if (item.getSelected())
                selectedItems.add(item);
        }

        return selectedItems;
    }
}
