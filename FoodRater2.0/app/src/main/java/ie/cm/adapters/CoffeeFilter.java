package ie.cm.adapters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import ie.cm.models.Coffee;

public class CoffeeFilter extends Filter {
	public List<Coffee> originalCoffeeList;
	public String filterText;
	public CoffeeListAdapter adapter;

	public CoffeeFilter(List<Coffee> originalCoffeeList, String filterText,
                        CoffeeListAdapter adapter) {
		super();
		this.originalCoffeeList = originalCoffeeList;
		this.filterText = filterText;
		this.adapter = adapter;
	}

	public void setFilter(String filterText) {
		this.filterText = filterText;
	}

	@Override
	protected FilterResults performFiltering(CharSequence prefix) {
		FilterResults results = new FilterResults();

		List<Coffee> newCoffees;
		String coffeeName;

		if (prefix == null || prefix.length() == 0) {
			newCoffees = new ArrayList<>();
			if (filterText.equals("all")) {
				results.values = originalCoffeeList;
				results.count = originalCoffeeList.size();
			} else {
				if (filterText.equals("favourites")) {
					for (Coffee c : originalCoffeeList)
						if (c.favourite)
							newCoffees.add(c);
					}
					results.values = newCoffees;
					results.count = newCoffees.size();
			}
		} else {
			String prefixString = prefix.toString().toLowerCase();
			newCoffees = new ArrayList<>();

			for (Coffee c : originalCoffeeList) {
				coffeeName = c.coffeeName.toLowerCase();
				if (coffeeName.contains(prefixString)) {
					if (filterText.equals("all")) {
						newCoffees.add(c);
					} else if (c.favourite) {
						newCoffees.add(c);
					}}}
			results.values = newCoffees;
			results.count = newCoffees.size();
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void publishResults(CharSequence prefix, FilterResults results) {

		adapter.coffeeList = (ArrayList<Coffee>) results.values;

		if (results.count >= 0)
			adapter.notifyDataSetChanged();
		else {
			adapter.notifyDataSetInvalidated();
			adapter.coffeeList = originalCoffeeList;
		}
		Log.v("foodrater", "publishResults : " + adapter.coffeeList);
	}
}
