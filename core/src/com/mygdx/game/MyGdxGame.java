package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BackGround bg;
	Bird bird;
	Obstacles obstacles;
	boolean gameOver;
	Texture restartTexture;
	Score score;
	int count = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		bg = new BackGround();
		bird = new Bird();
		obstacles = new Obstacles();
		gameOver = false;
		restartTexture = new Texture("RestartBtn.png");
		score = new Score();
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		obstacles.render(batch);
		if (!gameOver) {
			bird.render(batch);
		} else {
			batch.draw(restartTexture, 200, 200);
		}
		score.render(batch);
		batch.end();
	}
	public int getCount(){
		return count;
	}

	public void update() {
		bg.update();
		bird.update();
		obstacles.update();
		for (int i = 0; i < Obstacles.obs.length; i++) {
			if (bird.position.x > Obstacles.obs[i].position.x && bird.position.x < Obstacles.obs[i].position.x + 50) {
				if (!Obstacles.obs[i].emptySpace.contains(bird.position)) {
					gameOver = true;
					count++;
				}
			}
		}
		score.update();
		if(bird.position.y <0 || bird.position.y >600){
			gameOver = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			recreate();
		}
	}
		@Override
		public void dispose() {
			batch.dispose();
		}
		public void recreate(){
		bird.recreate();
		obstacles.recreate();
		gameOver = false;
		}
}