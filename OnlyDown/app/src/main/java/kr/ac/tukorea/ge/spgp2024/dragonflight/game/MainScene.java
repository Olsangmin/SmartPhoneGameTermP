package kr.ac.tukorea.ge.spgp2024.dragonflight.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp2024.dragonflight.R;
import kr.ac.tukorea.ge.spgp2024.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2024.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp2024.framework.objects.Score;
import kr.ac.tukorea.ge.spgp2024.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2024.framework.view.Metrics;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Fighter fighter;
    Score score; // package private

    public int getScore() {
        return score.getScore();
    }

    public enum Layer {
        bg, enemy, bullet, player, ui, controller, COUNT, touch
    }
    public MainScene() {
        //Metrics.setGameSize(16, 16);
        initLayers(Layer.COUNT);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker(this));

        add(Layer.bg, new VertScrollBackground(R.mipmap.bg_earth, 0.0f));
        //add(Layer.bg, new VertScrollBackground(R.mipmap.clouds, 0.4f));

        add(Layer.bg, new Button(R.mipmap.btn_jump_n, 1.5f, 15.0f, 2.0f, 1.5f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                //Log.d(TAG, "Button: Slide " + action);
                fighter.jump();
               return true;
            }
        }));
        add(Layer.bg, new Button(R.mipmap.btn_slide_n, 7.5f, 15.0f, 2.0f, 1.5f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
               fighter.slide();
                return false;
            }
        }));

        this.fighter = new Fighter();
        add(Layer.player, fighter);

        this.score = new Score(R.mipmap.number_24x32, Metrics.width - 0.5f, 0.5f, 0.6f);
        score.setScore(0);
        add(Layer.ui, score);
    }

    public void addScore(int amount) {
        score.add(amount);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
