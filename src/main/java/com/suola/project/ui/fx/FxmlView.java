package com.suola.project.ui.fx;

import java.util.ResourceBundle;

public enum FxmlView {
    MODULE_WEBVIEW {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.webview.title");
        }

        @Override
		public String fxml() {
            return "/template/module/webview.fxml";
        }

    };
	
    
    public abstract String title();
    public abstract String fxml();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
