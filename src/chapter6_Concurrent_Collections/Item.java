package chapter6_Concurrent_Collections;

public class Item {

	private String description;
	private int itemId;

	public String getDescription() {
		return description;
	}

	public int getItemId() {
		return itemId;
	}

	public Item() {
		this.description = "Default Item";
		this.itemId = 0;
	}

	public Item(String description, int itemId) {
		this.description = description;
		this.itemId = itemId;
	}

}
