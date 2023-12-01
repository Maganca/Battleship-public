package views_controller;

import javafx.application.Platform;
import model.ClientHandler;
import model.ServerHandler;

public class MultiplayerCallbackHandler implements ClientHandler.MessageCallback {
	private ViewManager vm;

	public MultiplayerCallbackHandler(ViewManager viewManager) {
		this.vm = viewManager;
		Platform.runLater(() -> {
			vm.getMPShipSelectionView().startButton.setOnAction(mouseEvent -> {
				// TODO: communicate this with the server
				vm.getMPShipSelectionView().getConstructedPlayer(vm.getBoardSize());

			});
		});
	}

	// Implementing the onObjectReceived method from ClientHandler.MessageCallback
	@Override
	public void onObjectReceived(Object object) {
		if (object == ServerHandler.ServerComms.PLAYER_1) {
			Platform.runLater(() -> {
				vm.changeView(vm.getMPShipSelectionView());
			});
		}
		if (object == ServerHandler.ServerComms.PLAYER_2) {
			Platform.runLater(() -> {
				vm.changeView(vm.getMPShipSelectionView());
			});
		}
	}
}
