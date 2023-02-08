package com.groupeonepoint.codingchallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums/last-listened")
public class ListenedAlbumsController {

	@GetMapping
	@Transactional
	public List<ListenedAlbum> getLastListenedAlbums() throws IOException {
		// FIXME Améliorer cette partie
		BufferedReader listenedAlbums = new BufferedReader(
				new FileReader(ResourceUtils.getFile("classpath:data/listened_albums_small.csv")));
		List<ListenedAlbum> lastListening = new LinkedList<ListenedAlbum>();
		listenedAlbums.lines().filter(line -> !line.startsWith("Id;")).forEach(line -> {
			ListenedAlbum listenedAlbum = new ListenedAlbum();
			listenedAlbum.setAlbumId(line.split(";")[0]);
			listenedAlbum.setListenedCount(Integer.parseInt(line.split(";")[4]));
			try {
				listenedAlbum.setLastListened(new SimpleDateFormat("yyyy-MM-dd").parse(line.split(";")[3]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			lastListening.add(listenedAlbum);
		});
		lastListening.sort( (listenning1, listenning2) -> listenning1.getLastListened().compareTo(listenning2.getLastListened()));
		return lastListening.subList(0, 10);
	}

	private class ListenedAlbum {
		private String albumId;
		private Integer listenedCount;
		private Date lastListened;
		public String getAlbumId() {
			return albumId;
		}
		public void setAlbumId(String albumId) {
			this.albumId = albumId;
		}
		public Integer getListenedCount() {
			return listenedCount;
		}
		public void setListenedCount(Integer listenedCount) {
			this.listenedCount = listenedCount;
		}
		public Date getLastListened() {
			return lastListened;
		}
		public void setLastListened(Date lastListened) {
			this.lastListened = lastListened;
		}
	}
}
