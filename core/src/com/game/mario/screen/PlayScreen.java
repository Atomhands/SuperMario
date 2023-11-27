package com.game.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.mario.SuperMario;
import com.game.mario.sences.Hud;
import com.game.mario.sprite.Mario;


/**
 * ClassName: PlayScreen
 * Package: com.game.mario.screen
 * Description:
 *
 * @Author NieHao
 * @Create 2023/11/8 21:57
 * @Version 1.0
 */
public class PlayScreen implements Screen {
    private SuperMario game;
    //正交相机   游戏相机  游戏窗口
    private OrthographicCamera gameCamera;
    private Viewport viewport;
    private Hud hud;

    //调用地图加载器  平铺地图  正交平铺地图加载器
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    private Mario player;

    public PlayScreen(SuperMario game) {
        this.game = game;

        gameCamera = new OrthographicCamera();
        viewport = new FitViewport(SuperMario.V_WEIGHT / SuperMario.PPM, SuperMario.V_HEIGHT / SuperMario.PPM, gameCamera);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("levels/level1-1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap,1 / SuperMario.PPM);

        gameCamera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        player = new Mario(this);

        //body
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;


        //遍历地图对象
        for (MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / SuperMario.PPM, (rectangle.y + rectangle.getHeight() / 2) / SuperMario.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2 / SuperMario.PPM,rectangle.getHeight()/2 / SuperMario.PPM);
            fixtureDef.shape =shape;
            body.createFixture(fixtureDef);
        }
        for (MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / SuperMario.PPM, (rectangle.y + rectangle.getHeight() / 2) / SuperMario.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2 / SuperMario.PPM,rectangle.getHeight()/2 / SuperMario.PPM);
            fixtureDef.shape =shape;
            body.createFixture(fixtureDef);
        }
        for (MapObject object : tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / SuperMario.PPM, (rectangle.y + rectangle.getHeight() / 2) / SuperMario.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2 / SuperMario.PPM,rectangle.getHeight()/2 / SuperMario.PPM);
            fixtureDef.shape =shape;
            body.createFixture(fixtureDef);
        }
        for (MapObject object : tiledMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / SuperMario.PPM, (rectangle.y + rectangle.getHeight() / 2) / SuperMario.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth()/2 / SuperMario.PPM,rectangle.getHeight()/2 / SuperMario.PPM);
            fixtureDef.shape =shape;
            body.createFixture(fixtureDef);
        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.b2Body.applyLinearImpulse(new Vector2(0,4f),player.b2Body.getWorldCenter(),true);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2Body.getLinearVelocity().x <= 2)
                player.b2Body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2Body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2Body.getLinearVelocity().x >= -2)
                player.b2Body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2Body.getWorldCenter(), true);
//            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
//                player.fire();
    }

    //更新游戏时间
    public void update(float dt) {
        //检测是否有鼠标输入
        handleInput(dt);

        world.step(1/60f,6,2);

        gameCamera.position.x = player.b2Body.getPosition().x;
        //flash
        gameCamera.update();
        //相机渲染
        renderer.setView(gameCamera);
    }

    //在此重写方法中调用并渲染
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //渲染什么 实时地图
        renderer.render();

        b2dr.render(world,gameCamera.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return tiledMap;
    }
}
