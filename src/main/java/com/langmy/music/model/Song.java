package com.langmy.music.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 歌曲
 * 
 * @author ZZD
 *
 */
public class Song extends Model<Song> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486498055279906886L;

	public static final Song dao = new Song();

	/**
	 * 查询收藏人数最多的前12首歌
	 * 
	 * @return
	 */
	public static List<Song> godenSong() {
		String sql = "SELECT sp.`name` spName,sp.cover,so.name from special sp,(select s.*,count(sc.id) likeCount from song s ,song_collect sc where s.id =sc.song GROUP BY sc.song ORDER BY likeCount desc) so where sp.id=so.special limit 12";
		return Song.dao.find(sql);
	}

	/**
	 * 查询最新审批成功的8首歌曲
	 * 
	 * @return
	 */
	public static List<Song> newSong() {
		String sql = "SELECT s.check_time,sp.`name` spName,sp.cover,s.name from special sp, song s where sp.id=s.special ORDER BY s.check_time DESC limit 8";
		return Song.dao.find(sql);
	}

	/**
	 * 查询试听次数最多的5首歌曲
	 * 
	 * @return
	 */
	public static List<Song> topSong() {
		String sql = "SELECT m.uname, m.image,s.name from musician m,song s where s.musician = m.id ORDER BY s.play_count DESC limit 5";
		return Song.dao.find(sql);
	}

	public List<Song> findByName(String name) {
		String sql = "SELECT * FROM song s where s.`name` LIKE ?";
		return Song.dao.find(sql, name);
	}

}
