package org.bougainvilleas.tank;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.time.LocalTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TankApp extends GameApplication
{

    private Entity tank;
    private LocalTimer localTimer;

    private final Point2D up=new Point2D(0,-1);
    private final Point2D down=new Point2D(0,1);
    private final Point2D left=new Point2D(-1,0);
    private final Point2D right=new Point2D(1,0);

    private double bulletX;
    private double bulletY;

    private Point2D point=right;

    private boolean isMoving;

    @Override
    protected void initSettings(GameSettings settings)
    {
        settings.setWidth(900);
        settings.setHeight(1440);
        settings.setTitle("Tank");
        settings.setVersion("0.0.1");
        settings.setAppIcon("icon.png");
    }

    @Override
    protected void initGame()
    {
        localTimer=FXGL.newLocalTimer();
        tank = FXGL.entityBuilder()
//                //view tank实体的大小
//                .view(canvas)
//                //bbox tank实体的大小
//                .bbox(BoundingShape.box(100,100))
                .viewWithBBox(drawTank())
//                .view(new Text("player"))
                .build();
        tank.setRotationOrigin(tank.getCenter());
        FXGL.getGameWorld().addEntity(tank);

        createEnemy();
        createEnemy();
        createEnemy();

        Point2D center = tank.getCenter();
        System.err.println(center);
        System.err.println(tank.getHeight());
        System.err.println(tank.getWidth());
    }

    @Override
    protected void initInput()
    {
        FXGL.getInput().addAction(new UserAction("move up") {
            @Override
            protected void onAction()
            {
                if(isMoving)
                {
                    return;
                }
                isMoving=true;
                point=up;

                tank.translateY(-5);
                System.err.println(tank.getCenter());
                tank.setRotation(270);
                bulletX=tank.getCenter().getX();
                bulletY=tank.getCenter().getY()-60;
                Point2D center = tank.getCenter();
                System.err.println(center);
            }
        }, KeyCode.UP);
        FXGL.getInput().addAction(new UserAction("move down") {
            @Override
            protected void onAction()
            {
                if(isMoving)
                {
                    return;
                }
                isMoving=true;
                point=down;

                tank.translateY(5);
                System.err.println(tank.getCenter());
                tank.setRotation(90);
                bulletX=tank.getCenter().getX()-20;
                bulletY=tank.getCenter().getY()+50;
                Point2D center = tank.getCenter();
                System.err.println(center);
            }
        }, KeyCode.DOWN);
        FXGL.getInput().addAction(new UserAction("move left") {
            @Override
            protected void onAction()
            {
                if(isMoving)
                {
                    return;
                }
                isMoving=true;
                point=left;

                tank.translateX(-5);
                System.err.println(tank.getCenter());
                tank.setRotation(180);
                bulletX=tank.getCenter().getX()-60;
                bulletY=tank.getCenter().getY()-20;

                Point2D center = tank.getCenter();
                System.err.println(center);
            }
        }, KeyCode.LEFT);
        FXGL.getInput().addAction(new UserAction("move right") {
            @Override
            protected void onAction()
            {
                if(isMoving)
                {
                    return;
                }

                isMoving=true;
                point=right;
                tank.translateX(5);
                System.err.println(tank.getCenter());
                tank.setRotation(0);

                bulletX=tank.getCenter().getX()+50;
                bulletY=tank.getCenter().getY();
                System.err.println(tank.getCenter());
            }
        }, KeyCode.RIGHT);

        FXGL.getInput().addAction(new UserAction("shoot") {
            @Override
            protected void onAction()
            {
                if(!localTimer.elapsed(Duration.seconds(0.25)))
                {
                    return;
                }
                localTimer.capture();
                createBullet(bulletX,bulletY);
//                createCircle();
            }
        }, KeyCode.SPACE);
    }

    private Canvas drawTank()
    {
        Canvas canvas=new Canvas(100,100);
        GraphicsContext g2d = canvas.getGraphicsContext2D();
        g2d.setFill(Color.web("#ffec03"));
        g2d.fillRect(0,0,80,30);
        g2d.setFill(Color.web("#cebc17"));
        g2d.fillRect(15,30,50,40);
        g2d.setFill(Color.web("#ffec03"));
        g2d.fillRect(0,70,80,30);
        g2d.setFill(Color.web("#f9ee8a"));
        g2d.fillRect(40,40,60,20);
        return canvas;

    }

    private Entity createBullet(Double x,Double y)
    {
        Circle c=new Circle(10,Color.RED);

        return FXGL.entityBuilder()
                .type(GameType.BULLET)
                .at(x,y)
                .viewWithBBox(c)
                .with(new ProjectileComponent(point,500))
                .with(new OffscreenCleanComponent())
                .collidable()
                .buildAndAttach();
    }

    private Entity createEnemy()
    {
        return FXGL.entityBuilder()
                .type(GameType.ENEMY)
                .at(FXGLMath.random(10,850-60),FXGLMath.random(60,1440-60))
                .viewWithBBox(drawTank())
//                .with(new CollidableComponent())// 可碰撞 component
                .collidable()//可碰撞
                .buildAndAttach();
    }

//    private Entity createCircle()
//    {
//        Circle c=new Circle(10,Color.RED);
//        return FXGL.entityBuilder()
//                .type(GameType.BULLET)
//                .at(tank.getCenter())
//                .viewWithBBox(c)
//                .with(new ProjectileComponent(point,600))
//                .with(new OffscreenCleanComponent())
//                .collidable()
//                .buildAndAttach();
//    }

    @Override
    protected void initPhysics()
    {
        FXGL.getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(GameType.BULLET, GameType.ENEMY)
                {
                    @Override
                    protected void onCollisionBegin(Entity bullet, Entity enemy)
                    {
                        bullet.removeFromWorld();
                        Point2D enemyCenter = enemy.getCenter();
                        enemy.removeFromWorld();

                        Circle circle=new Circle(10,Color.RED);

                        Entity boom=FXGL.entityBuilder()
                                .at(enemyCenter)
                                .view(circle)
                                .buildAndAttach();

                        Duration d = Duration.seconds(.35);

                        ScaleTransition st = new ScaleTransition(d, circle);
                        st.setToX(10);
                        st.setToY(10);

                        FadeTransition ft = new FadeTransition(d, circle);
                        ft.setToValue(0);
                        ParallelTransition pt = new ParallelTransition(st, ft);
                        pt.setOnFinished(event -> boom.removeFromWorld());
                        pt.play();
                        createEnemy();
                    }
                }

        );
    }

    @Override
    protected void onUpdate(double tpf)
    {
        isMoving=false;
//        System.err.println(FXGL.getGameWorld().getEntities().size());
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
