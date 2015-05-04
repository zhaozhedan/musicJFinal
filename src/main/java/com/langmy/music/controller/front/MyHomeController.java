package com.langmy.music.controller.front;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.core.Controller;
import com.langmy.music.model.Album;
import com.langmy.music.model.Musician;
import com.langmy.music.model.Special;

/**
 * 
 * @author ZZD
 *
 */
public class MyHomeController extends Controller {
	public static Logger LOG = LoggerFactory.getLogger(MyHomeController.class);

	/**
	 * 进入专辑详细页面
	 */
	public void mySongs() {
		int specialId = Integer.parseInt(getPara());
		Special special = Special.dao.findById(specialId);
		// 显示专辑基本信息
		setAttr("specialCover", special.get("cover"));
		setAttr("specialName", special.get("name"));
		setAttr("collectCount", special.getCollectCount());
		// 显示歌曲礼列表
		Long songSize = special.getSize();
		if (songSize <= 5) {
			setAttr("leftSongs", special.getSongs(songSize));
		} else {
			setAttr("leftSongs", special.getSongs((long) 5));
			setAttr("rightSongs", special.getSongsOutSize(5));
		}
		// 显示该音乐人其余专辑 此处规定显示其余4个
		Musician musician = special.getMusician();
		Long musicianSize = musician.getSpecialSize();
		if (musicianSize <= 5) {
			setAttr("specialLast",
					musician.getSpecialLast(specialId, musicianSize - 1)
							.toArray());
		} else {
			setAttr("specialLast", musician.getSpecialLast(specialId, 4)
					.toArray());
		}

		renderFreeMarker("mySong.html");
	}

	public void myPicture() {
		int albumId = Integer.parseInt(getPara());
		Album album = Album.dao.findById(albumId);
		//获取相册大小  初始化页面格式
		long albumSize = album.getSize();
		List<Integer> titleIds = new ArrayList<Integer>();
		for (int i = 1; i <= albumSize; i++) {
			titleIds.add(i);
		}
		setAttr("titleIds", titleIds.toString());
		setAttr("pictures", album.getPictures().toArray());
		renderFreeMarker("myPicture.html");
	}
}
