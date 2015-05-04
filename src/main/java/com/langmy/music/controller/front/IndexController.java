package com.langmy.music.controller.front;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheName;
import com.langmy.music.model.Musician;
import com.langmy.music.model.Song;

public class IndexController extends Controller {

	public static Logger LOG = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 进入首页
	 */
	public void index() {
		setAttr("goldenSongs", Song.godenSong().toArray());
		setAttr("newSongs", Song.newSong().toArray());
		setAttr("topSongs", Song.topSong().toArray());
		setAttr("hotMusician", Musician.hotMusician().toArray());
		renderFreeMarker("index.html");
	}

	public void searchMusic() {
		setAttr("searchSongList", Song.dao.findByName(getPara()));
		renderFreeMarker("searchSong.html");
	}

	/**
	 * 
	 */
	public void genres() {
		renderFreeMarker("searchSong.html");
	}

	/**
	 * 进入音乐人主页
	 */
	public void myHome() {
		Musician musician = Musician.dao.findById(Integer.parseInt(getPara()));
		setAttr("musicianCon", musician.getConcerned());
		setAttr("listenCount", musician.getPlayCount());
		setAttr("posts", musician.getPost().toArray());
		setAttr("style", musician.getStyle().toArray());
		setAttr("special", musician.getSpecial(8).toArray());
		setAttr("albums", musician.getAlbum(8).toArray());
		renderFreeMarker("myHome.html");
	}

	/* @Before(CacheInterceptor.class) */
	@CacheName("userList")
	public void login() {
		List<Musician> users = Musician.dao.find("select * from musician");
		// List<User> users = User.dao.find("select * from user where id=?",2);
		// Page<User> users = User.dao.paginate(1, 10,
		// "select *","from user where id=?",2);
		// for(User user :users.getList()){
		// System.out.println(user.toString()+user.getStr("user_acc"));
		// }
		/*
		 * User user = new User(); user.set("user_acc", "accNew");
		 * user.set("name", "nameNew"); boolean flag = user.save();
		 * System.out.println(flag);
		 */
		if (LOG.isDebugEnabled()) {
			LOG.debug(users.toString());
		}
		setAttr("user", users);
		renderFreeMarker("index.html");
	}

}
