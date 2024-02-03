package experia.coffee.experiacoffee.model.SingletonPattern;

public class CartSingleton {

    private static CartSingleton instance;
    private int cartID;

    private CartSingleton() {
        // Costruttore privato per impedire l'istanziazione diretta
    }

    public static CartSingleton getInstance() {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

}
