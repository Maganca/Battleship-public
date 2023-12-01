package views_controller;

import javafx.scene.Node;
import model.ClientHandler;
import model.ServerHandler;
import multiplayer_views.MPShipSelectionView;

public class ViewManager {
	private StartScreenView startView;
	private ShipSelectionView shipSelectionView;
	private DefaultGameView defaultGameView;
	private MultiplayerSetupView multiplayerSetupView;
	private GameEndView gameEndView;
	private InfoView infoView;
	public DebugView debugView;
	private BattleshipGUI battleshipGUI;
	private MPShipSelectionView mpShipSelectionView;
	// Other views...

	private int boardSize = 10;

	public ViewManager(BattleshipGUI battleshipGUI) {
		this.battleshipGUI = battleshipGUI;
		initializeViews();
	}

	private void initializeViews() {
		// Initialize all the views
		startView = new StartScreenView(this);
		defaultGameView = new DefaultGameView(this);
		shipSelectionView = new ShipSelectionView(this);
		multiplayerSetupView = new MultiplayerSetupView(this);
		gameEndView = new GameEndView(this);
		infoView = new InfoView(this);
		debugView = new DebugView(this);
		mpShipSelectionView = new MPShipSelectionView(this);
		// Initialize other views...
	}

	public void changeView(Node newView) {
		battleshipGUI.setViewTo(newView);
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int x) {
		boardSize = x;
	}

	// Methods to access different views if needed
	public StartScreenView getStartView() {
		return startView;
	}

	public ShipSelectionView getShipSelectionView() {
		return shipSelectionView;
	}

	public MultiplayerSetupView getMultiplayerSetupView() {
		return multiplayerSetupView;
	}

	public InfoView getInfoView() {
		return infoView;
	}

	public DefaultGameView getDefaultView() {
		return defaultGameView;
	}

	public DebugView getDebugView() {
		return debugView;
	}

	public GameEndView getGameEndView() {
		return gameEndView;
	}

	public MPShipSelectionView getMPShipSelectionView() {
		return mpShipSelectionView;
	}

//	@Override
//	public void onObjectReceived(Object object) {
//		if (object == ServerHandler.ServerComms.PLAYER_1) {
//			System.out.println("baller1");
//			changeView(shipSelectionView);
//			//vm.changeView(vm.getMPShipSelectionView());
//			
//		}
//		if (object == ServerHandler.ServerComms.PLAYER_2) {
//			System.out.println("baller2");
//			changeView(shipSelectionView);
//			//vm.changeView(vm.getMPShipSelectionView());
//		}
//		
//	}
}
