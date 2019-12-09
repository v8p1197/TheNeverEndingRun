package it.unisa.theneverendingrun.models;

public abstract class Component {

    private Sprite sprite;

    private double x;
    private double y;

    private double width;
    private double height;

    public Component(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return sprite.getWidth();
    }

    public double getHeight() {
        return sprite.getHeight();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
