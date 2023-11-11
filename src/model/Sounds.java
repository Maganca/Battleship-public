package model;

import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

	private MediaPlayer soundPlayer;
	private ArrayList<Media> musicQueue;
	public boolean isPlaying;

	public Sounds() {
		musicQueue = new ArrayList<Media>();
		isPlaying = false;
	}
	
	public Sounds(Media song) {
		isPlaying = false;
		loop(song);
	}
	
	private void loop(Media song) {
		if(!isPlaying) {
			soundPlayer = new MediaPlayer(song);
			soundPlayer.play();
			soundPlayer.setOnEndOfMedia(new Waiter());
		}
	}

	public void queueUpNextSong(Media sound) {
		musicQueue.add(sound);
	}

	/*
	 * This function plays a song if the queue is not empty.
	 */
	public void play() {
		if (!musicQueue.isEmpty()&& !isPlaying) {
			soundPlayer = new MediaPlayer(musicQueue.remove(0));
			soundPlayer.play();
			isPlaying = true;
			soundPlayer.setOnEndOfMedia(new Waiter());
		}
	}

	private class Waiter implements Runnable {
		@Override
		public void run() {
			isPlaying = false;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			play();
		}
	}
}
