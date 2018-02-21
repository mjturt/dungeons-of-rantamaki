package combat;

public class NotEnoughMana extends Exception {
	static final long serialVersionUID = 609;

	public NotEnoughMana() {
		super();
	}
	
	public NotEnoughMana(String e) {
		super(e);
	}
}
