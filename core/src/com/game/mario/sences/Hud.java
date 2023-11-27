package com.game.mario.sences;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.mario.SuperMario;
import com.game.mario.screen.PlayScreen;

/**
 * ClassName: Hud
 * Package: com.game.mario.sences
 * Description:
 *
 * @Author NieHao
 * @Create 2023/11/8 22:24
 * @Version 1.0
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    //world property
    private Integer worldTime;
    private float countTime;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label worldLabel;
    Label levelLabel;
    Label marioLabel;

    public Hud(SpriteBatch batch){
        worldTime = 300;
        countTime = 0;
        score = 0;
        viewport = new FitViewport(SuperMario.V_WEIGHT,SuperMario.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%3d",worldTime),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%10d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("Time",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("Mario",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //表单平均分配在顶部(填充)
        table.add(marioLabel).expandX().pad(5);
        table.add(worldLabel).expandX().pad(5);
        table.add(timeLabel).expandX().pad(5);
        //创建新行
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();
        //添加到舞台
        stage.addActor(table);
    }
}
