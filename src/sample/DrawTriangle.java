package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class DrawTriangle {

  private static final int ANGLE_TEXT_PADDING = 15;

  private Line line1 = new Line();
  private Line line2 = new Line();
  private Line line3 = new Line();
  private Text angle1 = new Text();
  private Text angle2 = new Text();
  private Text angle3 = new Text();

  public void draw(Circle pointA, Circle pointB, Circle pointC, List<Line> triangleLines, List<Text> triangleAngles) {
    assert triangleAngles != null;
    assert triangleLines != null;

    line1.setStartX(pointA.getCenterX());
    line1.setStartY(pointA.getCenterY());
    line1.setEndX(pointB.getCenterX());
    line1.setEndY(pointB.getCenterY());
    triangleLines.add(line1);

    line2.setStartX(pointB.getCenterX());
    line2.setStartY(pointB.getCenterY());
    line2.setEndX(pointC.getCenterX());
    line2.setEndY(pointC.getCenterY());
    triangleLines.add(line2);

    line3.setStartX(pointC.getCenterX());
    line3.setStartY(pointC.getCenterY());
    line3.setEndX(pointA.getCenterX());
    line3.setEndY(pointA.getCenterY());
    triangleLines.add(line3);

    double a = Math.sqrt(Math.pow(pointB.getCenterX() - pointA.getCenterX(), 2) + Math.pow(pointB.getCenterY() - pointA.getCenterY(), 2)); // distance from 1 to 2
    double b = Math.sqrt(Math.pow(pointC.getCenterX() - pointB.getCenterX(), 2) + Math.pow(pointC.getCenterY() - pointB.getCenterY(), 2)); // distance from 2 to 3
    double c = Math.sqrt(Math.pow(pointA.getCenterX() - pointC.getCenterX(), 2) + Math.pow(pointA.getCenterY() - pointC.getCenterY(), 2)); // distance from 3 to 1

    double triangleAngle1 = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));
    double triangleAngle2 = Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b));
    double triangleAngle3 = Math.acos((Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * c));

    angle1.setText(String.format("%.2f", Math.toDegrees(triangleAngle3)));
    angle1.setX(pointA.getCenterX() + ANGLE_TEXT_PADDING);
    angle1.setY(pointA.getCenterY() + ANGLE_TEXT_PADDING);

    angle2.setText(String.format("%.2f", Math.toDegrees(triangleAngle1)));
    angle2.setX(pointB.getCenterX() + ANGLE_TEXT_PADDING);
    angle2.setY(pointB.getCenterY() + ANGLE_TEXT_PADDING);

    angle3.setText(String.format("%.2f", Math.toDegrees(triangleAngle2)));
    angle3.setX(pointC.getCenterX() + ANGLE_TEXT_PADDING);
    angle3.setY(pointC.getCenterY() + ANGLE_TEXT_PADDING);

    triangleAngles.add(angle1);
    triangleAngles.add(angle2);
    triangleAngles.add(angle3);
  }

}
