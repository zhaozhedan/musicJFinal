package com.langmy.music.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 专辑
 * 
 * @author ZZD
 *
 */
public class Special extends Model<Special> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735162091472598491L;

	public static final Special dao = new Special();
	
	public Long getSize(){
		String sql="select count(*) count from song s,special sp where s.state=1 and s.special=sp.id and s.special=?";
		return Special.dao.findFirst(sql,get("id")).getLong("count");
	}

	/**
	 * 获得专辑下首歌曲
	 * 
	 * @return
	 */
	public List<Song> getSongsOutSize(int size) {
		String sql1 = "select count(*) count from song s where s.state=1 and s.special=?";
		Long len = Song.dao.findFirst(sql1, get("id")).getLong("count");
		System.out.println(len);
		String sql2 = "select s.* from song s where s.state=1 and s.special=? ORDER BY s.check_time desc limit ?,?";
		return Song.dao.find(sql2, get("id"), size, len - size);
	}

	/**
	 * 获得专辑下的前size首歌曲
	 * 
	 * @param size
	 * @return
	 */
	public List<Song> getSongs(Long size) {
		String sql = "select s.* from song s where s.state=1 and s.special=? ORDER BY s.check_time desc limit ?";
		return Song.dao.find(sql, get("id"), size);
	}

	/**
	 * 获得该专辑所有的歌的收藏数量
	 * 
	 * @return
	 */
	public Long getCollectCount() {
		String sql = "select count(sc.id) count from song_collect sc WHERE sc.song in( select s.id from song s where s.state=1 and s.special=?)";
		return Special.dao.findFirst(sql, get("id")).getLong("count");
	}
	
	public Musician getMusician(){
		String sql="select m.* from musician m ,special s where m.id=s.musician and s.id=?";
		return Musician.dao.findFirst(sql, get("id"));
	}
}
