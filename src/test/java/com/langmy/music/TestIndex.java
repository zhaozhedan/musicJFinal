package com.langmy.music;

import java.util.List;

import org.junit.Test;

import com.langmy.music.model.Musician;
import com.langmy.music.model.Song;

import junit.framework.TestCase;

public class TestIndex extends TestCase {
	@Test
	public void test() {
		String sql = "select s.*,count(sc.id) from song s ,song_collect sc where s.id =sc.song GROUP BY sc.song";

		List<Song> songs = Song.dao.find(sql);
		for (Song song : songs) {
			System.out.println(song.toJson());
		}
	}
	
	@Test
	public void test2() {
		Musician users = Musician.dao.findById(1);
			System.out.println(users.toJson());
	}
}
