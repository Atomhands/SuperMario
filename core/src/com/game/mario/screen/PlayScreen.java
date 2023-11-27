package com.game.mario.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.mario.SuperMario;
import com.game.mario.sences.Hud;

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
    //正交相机   游戏相机
    private OrthographicCamera gameCamera;
    //游戏窗口
    private Viewport viewport;
    private Hud hud;
    //调用地图加载器
    private TmxMapLoader mapLoader;
    //平铺地图
    private TiledMap tiledMap;
    //正交平铺地图加载器
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(SuperMario game) {
        this.game = game;
        gameCamera = new OrthographicCamera();
        viewport = new FitViewport(SuperMario.V_WEIGHT, SuperMario.V_HEIGHT, gameCamera);
        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("/levels/world1-1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        gameCamera.position.set(viewport.getScreenWidth() / 2, viewport.getScreenHeight() / 2, 0);
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isTouched())
            gameCamera.position.x += dt*100;
    }
    //更新游戏时间
    public void update(float dt){
        //检测是否有鼠标输入
        handleInput(dt);
        //flash
        gameCamera.update();
        //相机渲染
        renderer.setView(gameCamera);
    }

    //在此重写方法中调用并渲染
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.25f, 0.5f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //渲染什么 实时地图
        renderer.render();

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
}
