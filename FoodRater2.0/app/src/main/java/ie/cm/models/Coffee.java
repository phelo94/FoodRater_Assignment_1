package ie.cm.models;

import java.io.Serializable;
import java.util.UUID;

public class Coffee implements Serializable
{
	public String coffeeId;
	public String coffeeName;
	public String shop;
	public double rating;
	public double price;
	public boolean favourite;


	public Coffee() {}

	public Coffee(String name, String shop, double rating, double price, boolean fav)
	{
		this.coffeeId = UUID.randomUUID().toString();
		this.coffeeName = name;
		this.shop = shop;
		this.rating = rating;
		this.price = price;
		this.favourite = fav;
	}

	@Override
	public String toString() {
		return coffeeId + " " + coffeeName + ", " + shop + ", " + rating
				+ ", " + price + ", fav =" + favourite;
	}
}
