package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawCircle {

  public Circle draw(double x, double y, double radius, Color fill, Color stroke) {
    Circle circle = new Circle(x, y, radius);
    circle.setFill(fill);
    circle.setStroke(stroke);
    return circle;
  }

}
