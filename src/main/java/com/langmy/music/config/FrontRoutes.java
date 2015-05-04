package com.langmy.music.config;

import com.jfinal.config.Routes;
import com.langmy.music.controller.front.IndexController;
import com.langmy.music.controller.front.MyHomeController;

public class FrontRoutes extends Routes {

	@Override
	public void config() {
		add("/", IndexController.class, "/view/front");
		add("/myHome", MyHomeController.class, "/view/front");
	}

}
