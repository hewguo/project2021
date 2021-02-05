package com.suola.project.ui.fx;

import com.gn.decorator.GNDecorator;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.sun.javafx.application.LauncherImpl.launchApplication;

@SuppressWarnings("restriction")
public abstract class AbstractFxApplication extends Application {

	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractFxApplication.class);

	// spring context
	protected static ConfigurableApplicationContext applicationContext;

	// stage manager
	protected StageManager stageManager;

	// pre-load views
	protected static List<FxmlView> preloadViews;
	protected static FxmlView initView;

	// pre-load status
	private float progress = 0;

	/**
	 * 1. load spring application context;
	 * 2. launch FX application;
	 * 
	 * @param appClass
	 * @param args
	 */
	public static void run(final Class<? extends Application> appClass, final List<FxmlView> _preloadViews,
			final FxmlView _initView, final String[] args) {
		preloadViews = _preloadViews;
		initView = _initView;

		CompletableFuture.supplyAsync(() -> applicationContext = SpringApplication.run(appClass, args))
				.whenComplete((ctx, throwable) -> {
					if (throwable != null) {
						LOGGER.error("Failed to load spring application context: ", throwable);
					} else {
						launchApplication(appClass, FxAppPreloader.class, args);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#init()
	 */
	@Override
	public synchronized void init() {
		try {
			for (FxmlView view : preloadViews) {
				// load view
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							FXMLLoader.load(getClass().getResource(view.fxml()));
						}catch (Exception ex){
							ex.printStackTrace();
						}
					}
				});


				// update loader status
				notifyLoader();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void notifyLoader() {
		progress += 100f / preloadViews.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primary) {

		GNDecorator decorator = new GNDecorator();
		stageManager = applicationContext.getBean(StageManager.class, primary, decorator);
		stageManager.switchScene(initView);
		//((VBox)(stageManager.getDecorator().getScene().getRoot())).getChildren().addAll(menuBar);
		stageManager.showDecorator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() throws Exception {
		super.stop();
		if (applicationContext != null) {
			applicationContext.close();
		}
	}

}
