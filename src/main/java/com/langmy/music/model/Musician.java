package com.langmy.music.model;

import java.math.BigDecimal;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 音乐人
 * 
 * @author ZZD
 *
 */
public class Musician extends Model<Musician> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735162091472598491L;

	public static final Musician dao = new Musician();

	public Long getSpecialSize(){
		String sql="select count(*) count from musician m,special sp where sp.state=1 and sp.musician=m.id and m.id=?";
		return Musician.dao.findFirst(sql,get("id")).getLong("count");
	}
	/**
	 * 查询关注人数最多的前15名歌手
	 * 
	 * @return
	 */
	public static List<Musician> hotMusician() {
		String hotMusicianSql = "SELECT m.id,m.uname,m.image,m.provice,m.city from musician m,(select mc.musician,COUNT(mc.id) likeCount from muscian_concerned mc GROUP BY mc.musician ORDER BY likeCount DESC) mc1 where mc1.musician=m.id";
		return Musician.dao.find(hotMusicianSql);
	}

	/**
	 * 查询关注人数
	 * 
	 * @return
	 */
	public Long getConcerned() {
		String sql = "SELECT count(mc.id) count from muscian_concerned mc WHERE mc.concerned =?";
		return MusicianConcerned.dao.findFirst(sql, get("id")).getLong("count");
	}

	/**
	 * 获得音乐人所有歌曲的播放量(试听量)
	 * 
	 * @return
	 */
	public BigDecimal getPlayCount() {
		String sql = "SELECT sum(s.play_count) playCount from song s WHERE s.musician=?";
		return Song.dao.findFirst(sql, get("id")).getBigDecimal("playCount");
	}

	/**
	 * 获得音乐人风格
	 * 
	 * @return
	 */
	public List<MusicianStyle> getStyle() {
		String sql = "select s.`name` from musician_style ms, style s  where ms.style=s.id and ms.musician=?";
		return MusicianStyle.dao.find(sql, get("id"));
	}

	/**
	 * 获得音乐人身份
	 * 
	 * @return
	 */
	public List<MusicianPost> getPost() {
		String sql = "select p.`name` from musician_post mp, post p  where mp.post=p.id and mp.musician=?";
		return MusicianPost.dao.find(sql, get("id"));
	}

	/**
	 * 获得最新专辑 除了specialId这张
	 * 
	 * @param special
	 * @param l
	 * @return
	 */
	public List<Special> getSpecialLast(int specialId, long l) {
		String sql = "select s.id, s.name,s.cover from special s where s.state=1 and s.musician=? and s.id not in(?) order by s.check_time desc limit ?";
		return Special.dao.find(sql, get("id"), specialId, l);
	}

	/**
	 * 获得音乐人最新发行的专辑
	 * 
	 * @param size
	 * @return
	 */
	public List<Special> getSpecial(int size) {
		String sql = "select s.id, s.name,s.cover from special s where s.state=1 and s.musician=? order by s.check_time desc limit ?";
		return Special.dao.find(sql, get("id"), size);
	}

	/**
	 * 获得最新的相册
	 * 
	 * @param size
	 * @return
	 */
	public List<Album> getAlbum(int size) {
		String sql = "SELECT a.id ,a.name,a.cover from album a where a.musician=? and a.state=1 ORDER BY a.creatTime DESC limit ?";
		return Album.dao.find(sql, get("id"), size);
	}

}
