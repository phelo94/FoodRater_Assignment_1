package ie.cm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.cm.R;
import ie.cm.models.Coffee;

public class CoffeeListAdapter extends ArrayAdapter<Coffee>
{
    private Context context;
    private View.OnClickListener deleteListener;
    public List<Coffee> coffeeList;

    public CoffeeListAdapter(Context context, View.OnClickListener deleteListener, List<Coffee> coffeeList)
    {
        super(context, R.layout.coffeerow, coffeeList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.coffeeList = coffeeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CoffeeItem item = new CoffeeItem(context, parent,
                                         deleteListener, coffeeList.get(position));
        return item.view;
    }

    @Override
    public int getCount() {
        return coffeeList.size();
    }

    @Override
    public Coffee getItem(int position) {
        return coffeeList.get(position);
    }
}
