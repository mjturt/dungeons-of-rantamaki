package gui;

/* GameCamera */

public class GameCamera {
    private double x;
    private double y;

    public GameCamera(double x, double y){ 
        this.x = x;
        this.y = y;
    }

    /* Passing player and calculating center point */

    public void tick(GameObject o) {
        x = o.getX() - 800/2;
        y = o.getY() - 640/2;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
