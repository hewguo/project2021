package com.suola.project.ui.controller;

import com.suola.project.util.ServerConfig;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WebviewController implements Initializable {

	@FXML
	private WebView webView;

	@FXML
	private ProgressIndicator progressIndicator;

	@Autowired
	private ServerConfig serverConfig;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int serverPort=0;
		try{
			serverPort=serverConfig.getServerPort();
		}catch (Exception ex){

		}

		setUrl("http://localhost:"+serverPort+"/");
		WebEngine webEngine = webView.getEngine();
		webEngine.setOnResized((WebEvent<Rectangle2D> event) -> {
			// To change body of generated methods, choose Tools | Templates.
			System.out.println("Window resized");
		});

		// 加载指示器
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				if ("SUCCEEDED".equals(newValue.toString())) {
//	                	webView.setVisible(true);
					progressIndicator.setVisible(false);
				} else {
					progressIndicator.setVisible(true);
				}
			}
		});
	}

	@FXML
	public void clickPageABtn(Event event) throws IOException {
		setUrl("https://www.bing.com");
	}

	@FXML
	public void clickPageBBtn(Event event) throws IOException {
		setUrl("https://www.baidu.com");
	}

	public void setUrl(String url) {
		// show process indicator
//		webView.setVisible(false);
		progressIndicator.setVisible(true);

		// load html page
		WebEngine webEngine = webView.getEngine();
		webEngine.load(url);

	}

}
