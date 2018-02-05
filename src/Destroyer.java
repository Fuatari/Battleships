
public class Destroyer extends Ship {

	String name = "Destroyer";
	int length = 2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String name() {
		return getName();
	}

	public int length() {
		return getLength();
	}

}
