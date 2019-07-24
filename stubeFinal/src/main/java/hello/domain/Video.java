package hello.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hello.application.dto.VideoDTO;
import hello.utilities.InvalidParamException;

@Entity(name = "Video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="videoId")
	private Integer id;
	private String title, description;
	@Column(name="video_url")
	private String videoUrl;
	@Column(name="image_url")
	private String imageUrl;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	public Video() {

	}

	public Video(User user, VideoDTO newVideo) throws InvalidParamException {

		if (user == null)
			throw new InvalidParamException();

		checkValidTitle(newVideo.getTitle());
		checkValidDescription(newVideo.getDescription());
		checkValidURL(newVideo.getImageUrl());
		checkValidURL(newVideo.getVideoUrl());

		this.user = user;
		this.title = newVideo.getTitle();
		this.description = newVideo.getDescription();
		this.videoUrl = newVideo.getVideoUrl();
		this.imageUrl = newVideo.getImageUrl();
	}

	private void checkValidTitle(String title) throws InvalidParamException {
		if (title != null && title.equals(""))
			throw new InvalidParamException();
	}

	private void checkValidDescription(String description) throws InvalidParamException {
		if (description != null && description.equals(""))
			throw new InvalidParamException();
	}

	private void checkValidURL(String url) throws InvalidParamException {
		if (url != null && url.equals("") && !url.contains(".com"))
			throw new InvalidParamException();
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getVideoUrl() {
		return videoUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public User getUser() {
		return user;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
