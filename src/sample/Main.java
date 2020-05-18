package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

  private static final double SCENE_X = 800;
  private static final double SCENE_Y = 800;
  private static final double RADIUS = 200;
  private static final double POINT_CIRCLE_RADIUS = 5;
  private static final double DRAGGING_THRESHOLD = 2.5;

  @Override
  public void start(Stage primaryStage) {
    final double mainCircleX = SCENE_X / 2;
    final double mainCircleY = SCENE_Y / 2;

    DrawCircle drawCircle = new DrawCircle();
    DrawTriangle drawTriangle = new DrawTriangle();
    List<Line> triangleLines = new ArrayList<>();
    List<Text> triangleAngles = new ArrayList<>();
    primaryStage.setTitle("Dynamic triangle alongside circle");

    double randA = Math.random()* (2 * Math.PI);
    double randB = Math.random()* (2 * Math.PI);
    double randC = Math.random()* (2 * Math.PI);
    double x1 = mainCircleX + RADIUS * Math.cos(randA);
    double y1 = mainCircleY + RADIUS * Math.sin(randA);
    double x2 = mainCircleX + RADIUS * Math.cos(randB);
    double y2 = mainCircleY + RADIUS * Math.sin(randB);
    double x3 = mainCircleX + RADIUS * Math.cos(randC);
    double y3 = mainCircleY + RADIUS * Math.sin(randC);

    Circle mainCircle = drawCircle.draw(mainCircleX, mainCircleY, RADIUS, null, Color.BLACK);
    Circle circleA = drawCircle.draw(x1, y1, POINT_CIRCLE_RADIUS, Color.RED, Color.BLACK);
    Circle circleB = drawCircle.draw(x2, y2, POINT_CIRCLE_RADIUS, Color.RED, Color.BLACK);
    Circle circleC = drawCircle.draw(x3, y3, POINT_CIRCLE_RADIUS, Color.RED, Color.BLACK);
    drawTriangle.draw(circleA, circleB, circleC, triangleLines, triangleAngles);

    addDragEventHandlers(circleA, circleB, circleC, drawTriangle, triangleLines, triangleAngles);

    Pane pane = new Pane();
    pane.getChildren().addAll(triangleLines);
    pane.getChildren().addAll(triangleAngles);
    pane.getChildren().addAll(mainCircle, circleA, circleB, circleC);

    primaryStage.show();
    primaryStage.setScene(new Scene(pane, SCENE_X, SCENE_Y));
    primaryStage.show();
  }

  private void addDragEventHandlers(Circle circleA, Circle circleB, Circle circleC, DrawTriangle drawTriangle, List<Line> lines, List<Text> angles) {
    circleA.setOnMouseDragged(e -> {
      if (circleA.contains(e.getX(), e.getY()) && isPointOnCircle(e.getX(), e.getY())) {
        circleA.setCenterX(e.getX());
        circleA.setCenterY(e.getY());
        drawTriangle.draw(circleA, circleB, circleC, lines, angles);
      }
    });

    circleB.setOnMouseDragged(e -> {
      if (circleB.contains(e.getX(), e.getY()) && isPointOnCircle(e.getX(), e.getY())) {
        circleB.setCenterX(e.getX());
        circleB.setCenterY(e.getY());
        drawTriangle.draw(circleA, circleB, circleC, lines, angles);
      }
    });

    circleC.setOnMouseDragged(e -> {
      if (circleC.contains(e.getX(), e.getY()) && isPointOnCircle(e.getX(), e.getY())) {
        circleC.setCenterX(e.getX());
        circleC.setCenterY(e.getY());
        drawTriangle.draw(circleA, circleB, circleC, lines, angles);
      }
    });
  }

  private static boolean isPointOnCircle(double x, double y) {
    double distanceFromCircleCenter = Math.sqrt(Math.pow(x - SCENE_X / 2, 2) + Math.pow(y - SCENE_Y / 2, 2));
    return distanceFromCircleCenter < RADIUS + DRAGGING_THRESHOLD && distanceFromCircleCenter > RADIUS - DRAGGING_THRESHOLD;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
