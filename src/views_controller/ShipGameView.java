package views_controller;

import java.io.File;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import model.Player;
import model.Ship;
import model.Sounds;
import model.Board.ShotResult;

public class ShipGameView extends BorderPane {

	private VBox ourVBox = new VBox();
	private Player player1;
	private Player player2;
	private ImageView[][] player1Grid;
	private ImageView[][] player2Grid;
	private GridPane player1GridPane = new GridPane();
	private GridPane player2GridPane = new GridPane();
	
	private List<Ship> shipList1;
	private List<Ship> shipList2;
	
	private Image ocean = new Image(
			new File("Tiles/Oceangif1.gif").toURI().toString());
	
	private Image shipha = new Image(
			new File("Tiles/shipha.gif").toURI().toString());
	private Image shiphb = new Image(
			new File("Tiles/shiphb.gif").toURI().toString());
	private Image shiphc = new Image(
			new File("Tiles/shiphc.gif").toURI().toString());
	private Image shipva = new Image(
			new File("Tiles/shipha.gif").toURI().toString());
	private Image shipvb = new Image(
			new File("Tiles/shiphb.gif").toURI().toString());
	private Image shipvc = new Image(
			new File("Tiles/shiphc.gif").toURI().toString());
	
	private Image hitha = new Image(
			new File("Tiles/hitha.gif").toURI().toString());
	private Image hithb = new Image(
			new File("Tiles/hithb.gif").toURI().toString());
	private Image hithc = new Image(
			new File("Tiles/hithc.gif").toURI().toString());
	private Image hitva = new Image(
			new File("Tiles/hitva").toURI().toString());
	private Image hitvb = new Image(
			new File("Tiles/hitvb.gif").toURI().toString());
	private Image hitvc = new Image(
			new File("Tiles/hitvc.gif").toURI().toString());
	
	private Image sunkha = new Image(
			new File("Tiles/sunkha.png").toURI().toString());
	private Image sunkhb = new Image(
			new File("Tiles/sunkhb.png").toURI().toString());
	private Image sunkhc = new Image(
			new File("Tiles/sunkhc.png").toURI().toString());
	private Image sunkva = new Image(
			new File("Tiles/sunkva.png").toURI().toString());
	private Image sunkvb = new Image(
			new File("Tiles/sunkvb.png").toURI().toString());
	private Image sunkvc = new Image(
			new File("Tiles/sunkvc.png").toURI().toString());
	
	private Image miss = new Image(
			new File("Tiles/pmiss.gif").toURI().toString());
	private Image hit = new Image(
			new File("Tiles/phit.gif").toURI().toString());
	private Image background = new Image(
			new File("Tiles/background.png").toURI().toString());

	private static final Media SHOOT_NOISE = new Media(
			new File("soundeffects/shoot.mp3").toURI().toString());
	private static final Media HIT_NOISE = new Media(
			new File("soundeffects/hit.mp3").toURI().toString());
	private static final Media MISS_NOISE = new Media(
			new File("soundeffects/miss.mp3").toURI().toString());
	private static final Media GOOD_NOISE = new Media(
			new File("soundeffects/good.mp3").toURI().toString());
	private static final Media BAD_NOISE = new Media(
			new File("soundeffects/bad.mp3").toURI().toString());
	private static final Media BACKGROUND = new Media(
			new File("soundeffects/hit.mp3").toURI().toString());
	private Sounds soundEffect = new Sounds();

	public ShipGameView(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		shipList1 = player1.getBoard().getShipList();
		shipList2 = player1.getBoard().getShipList();
		initializePanel();
	}

	private void initializePanel() {
		int boardSize = player1.getBoardSize();
		player1Grid = new ImageView[boardSize][boardSize];
		player2Grid = new ImageView[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				player1Grid[i][j] = new ImageView(
						imageChoice(player1.getBoard().toStringCoord(i, j)));
				player1Grid[i][j].setFitWidth(20);
				player1Grid[i][j].setFitHeight(20);
				player2Grid[i][j] = new ImageView(ocean);
				player2Grid[i][j].setFitWidth(20);
				player2Grid[i][j].setFitHeight(20);
				player2Grid[i][j].setOnMouseClicked(new ShotListener());

				player2GridPane.add(player2Grid[i][j], i, j);
				player1GridPane.add(player1Grid[i][j], i, j);
			}
		}
		player2GridPane.setHgap(2);
		player2GridPane.setVgap(2);
		player1GridPane.setHgap(2);
		player1GridPane.setVgap(2);
		ourVBox.getChildren().clear();
		ourVBox.getChildren().add(new Label("My Board: "));
		ourVBox.getChildren().add(player2GridPane);
		ourVBox.getChildren().add(new Label("\nYour Board: "));
		ourVBox.getChildren().add(player1GridPane);
		ourVBox.setBackground(new Background(new BackgroundImage(background, null, null,null,null)));
		ourVBox.setBorder(new Border(new BorderStroke(Color.BLUE,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.EMPTY,
				new Insets(10, 10, 10, 10))));

		this.setCenter(ourVBox);
	}
	/**
	 * determines ship image status for players
	 * @param stat
	 * @param ship
	 * @return
	 */
	private Image imageChoice(String stat) {
		if (stat.equals("H")) {
			return hit;
		} else if (stat.equals("M")) {
			return miss;
		} else if (stat.equals("S")) {
			return hit;
		} else if (stat.equals("_")) {
			return ocean;
		} else {
			return null;
		}
	}
	
	private Image playerImageChoice;
	
	
	public class ShotListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			int boardSize = player1.getBoardSize();
			if (player1.isAlive() && player2.isAlive()) {
				ImageView ImageClicked = (ImageView) arg0.getSource();
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (player2Grid[i][j] == ImageClicked) {
							ShotResult player1Outcome = player1
									.takeShot(player2, i, j);
							switch (player1Outcome) {
							case GAME_WON:
								soundEffect.queueSound(SHOOT_NOISE);
								soundEffect.queueSound(GOOD_NOISE);
								System.out.print("You Won");
								break;
							case HIT:
								soundEffect.queueSound(SHOOT_NOISE);
								soundEffect.queueSound(HIT_NOISE);
								System.out.print("You hit my ship!");
								break;
							case MISS:
								soundEffect.queueSound(SHOOT_NOISE);
								soundEffect.queueSound(MISS_NOISE);
								System.out.print("You missed");
								break;
							case REPEAT_ATTEMPT:
								soundEffect.queueSound(BAD_NOISE);
								System.out.print("Try Again\n");
								break;
							case SUNK:
								soundEffect.queueSound(SHOOT_NOISE);
								soundEffect.queueSound(SHOOT_NOISE);
								System.out.print("You sunk my ship!");
								break;
							default:
								break;
							}
							if (player1Outcome.equals(ShotResult.HIT)
									|| player1Outcome.equals(ShotResult.MISS)
									|| player1Outcome.equals(ShotResult.SUNK)) {
								ShotResult AIOutcome = player2
										.takeShot(player1);

								System.out.print("\nMy turn: ");
								switch (AIOutcome) {
								case GAME_WON:
									System.out.print("I won!\n");
									break;
								case HIT:
									System.out.print("I hit your ship!\n");
									break;
								case MISS:
									System.out.print("I missed\n");
									break;
								case SUNK:
									System.out.print("I sunk your ship!\n");
									break;
								default:
									System.out.print(AIOutcome);
									break;
								}
								for (int k = 0; k < boardSize; k++) {
									for (int l = 0; l < boardSize; l++) {
										player1Grid[k][l].setImage(
												imageChoice(player1.getBoard()
														.toStringCoord(k, l)));
									}
								}
							}
							player2Grid[i][j].setImage(imageChoice(
									player2.getBoard().toStringCoord(i, j)));
						}
					}
				}
			}
			if (!(player1.isAlive() && player2.isAlive())) {
				// The game is done so do whatever code here
				// System.out.print("GAME DONE!\n");
			}
		}
	}
}
