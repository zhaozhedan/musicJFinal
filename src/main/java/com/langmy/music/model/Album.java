package com.langmy.music.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 相册
 * 
 * @author ZZD
 *
 */
public class Album extends Model<Album> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735162091472598491L;

	public static final Album dao = new Album();

	public Long getSize() {
		String sql = "SELECT count(*) count from picture pi where pi.state=1 and pi.album=?";
		return Picture.dao.findFirst(sql, get("id")).getLong("count");
	}

	public List<Picture> getPictures() {
		String sql = "SELECT p.* from picture p where p.state=1 and p.album=? ORDER BY P.upload_time DESC";
		return Picture.dao.find(sql, get("id"));

	}

}
