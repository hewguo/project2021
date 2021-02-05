package com.suola.project.ui.fx;

import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Manages switching Scenes on the Primary Stage
 */
public class StageManager {

	private static final Logger LOG = getLogger(StageManager.class);
	
	private final Stage primaryStage;
	private final SpringFXMLLoader springFXMLLoader;
	private final GNDecorator decorator;

	public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage, GNDecorator decorator) {
		//修改左上角的按钮，添加菜单
		Class cDecorator=decorator.getClass();
		try{

			Field buttonField=cDecorator.getDeclaredField("btn_ico");
			buttonField.setAccessible(true);
			Button btn_ico=(Button) buttonField.get(decorator);

			btn_ico.setOnMouseClicked(event -> {
				GlobalMenu.getInstance(stage).show(btn_ico, Side.BOTTOM,0,0);
			});
		}catch (Exception ex){
			ex.printStackTrace();
		}
		//----------------------

		this.springFXMLLoader = springFXMLLoader;
		this.primaryStage = stage;
		this.decorator = decorator;
		
		// set decorator
		decorator.setTitle("Project2021");
//		      decorator.setIcon(null);
		decorator.addButton(ButtonType.FULL_EFFECT);
		decorator.initTheme(GNDecorator.Theme.DEFAULT);
		decorator.getStage().setOnCloseRequest(event -> {
			this.closeAllPopups();
			Platform.exit();
		});
		decorator.getScene().getStylesheets().addAll(getClass().getResource("/styles/theme/fonts.css").toExternalForm(),
				getClass().getResource("/styles/theme/material-color.css").toExternalForm(),
				getClass().getResource("/styles/theme/skeleton.css").toExternalForm(),
				getClass().getResource("/styles/theme/light.css").toExternalForm(),
				getClass().getResource("/styles/theme/bootstrap.css").toExternalForm(),
				getClass().getResource("/styles/theme/shape.css").toExternalForm(),
				getClass().getResource("/styles/theme/typographic.css").toExternalForm(),
				getClass().getResource("/styles/theme/helpers.css").toExternalForm(),
				getClass().getResource("/styles/theme/master.css").toExternalForm());
		decorator.setMaximized(true);
		decorator.getStage().getIcons().add(new Image("/icons/logo2.png"));
	}
	
	/**
	 * replace content for primary stage
	 * 
	 * @param view
	 */
	public void switchScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.fxml());
		decorator.setContent(viewRootNodeHierarchy);
	}
	
	/**
	 * replace content for pane
	 * 
	 * @param view
	 * @param body
	 */
	public void switchContent(final FxmlView view, ScrollPane body) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.fxml());
		body.setContent(viewRootNodeHierarchy);
	}
	
	/**
	 * show
	 * 
	 * @param view
	 */
	public void showPopWindow(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.fxml());
		
		Scene scene = prepareScene(viewRootNodeHierarchy);
		primaryStage.setTitle(view.title());
		primaryStage.setScene(scene);
		primaryStage.setHeight(600d);
		primaryStage.setWidth(1000d);
        primaryStage.centerOnScreen();
		try {
			primaryStage.show();
		} catch (Exception exception) {
			logAndExit("Unable to show scene for title" + view.title(), exception);
		}
	}

	private Scene prepareScene(Parent rootnode) {
		Scene scene = primaryStage.getScene();

		if (scene == null) {
			scene = new Scene(rootnode);
		}
		scene.setRoot(rootnode);
		return scene;
	}

	/**
	 * Loads the object hierarchy from a FXML document and returns to root node of
	 * that hierarchy.
	 *
	 * @return Parent root node of the FXML document hierarchy
	 */
	private Parent loadViewNodeHierarchy(String fxmlFilePath) {
		Parent rootNode = null;
		try {
			rootNode = springFXMLLoader.load(fxmlFilePath);
			Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
		} catch (Exception exception) {
			logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
		}
		return rootNode;
	}

	private void logAndExit(String errorMsg, Exception exception) {
		LOG.error(errorMsg, exception, exception.getCause());
		Platform.exit();
	}

	public GNDecorator getDecorator() {
		return decorator;
	}

	public void closeAllPopups() {
//		if (MainController.popConfig.isShowing())
//			MainController.popConfig.hide();
//		if (MainController.popup.isShowing())
//			MainController.popup.hide();
	}

	public void showDecorator() {
		decorator.show();
	}
}
