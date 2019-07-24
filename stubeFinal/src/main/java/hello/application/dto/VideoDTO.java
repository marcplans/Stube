package hello.application.dto;

import com.google.gson.annotations.Expose;

import hello.domain.Video;

public class VideoDTO {
	@Expose
	private int id;
	@Expose
	private String title, description, videoUrl,imageUrl;

	public VideoDTO(Video video) {
		this.id = video.getId();
		this.description = video.getDescription();
		this.title = video.getTitle();
		this.videoUrl = video.getVideoUrl();
		this.imageUrl=video.getImageUrl();
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getVideoUrl() {
		return videoUrl;
	}

}
