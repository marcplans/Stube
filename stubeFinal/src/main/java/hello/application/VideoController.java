package hello.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.application.dto.UserDTO;
import hello.application.dto.VideoDTO;
import hello.domain.User;
import hello.domain.Video;
import hello.persistence.VideoRepository;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@Controller
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private UserController userController;

	public VideoDTO uploadVideo(int userId, VideoDTO newVideo) throws InvalidParamException, NotFoundException {
		User user = userController.getUser(userId);

		Video video = new Video(user, newVideo);

		videoRepository.save(video);

		return new VideoDTO(video);
	}

	public List<VideoDTO> getAllVideos(int userId) throws NotFoundException {

		User user = userController.getUser(userId);

		List<VideoDTO> videosDTO = new ArrayList<>();

		List<Video> videos = videoRepository.getAllVideos(user);

		for (Video v : videos) {
			videosDTO.add(new VideoDTO(v));
		}

		return videosDTO;
	}

	public VideoDTO getVideo(int userId, int videoId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);

		Video video = videoRepository.getVideoById(videoId);

		if (user.getId() != video.getUser().getId())
			throw new InvalidParamException();

		return new VideoDTO(video);
	}

	public void removeAllVideos(int userId) throws NotFoundException {
		User user = userController.getUser(userId);

		videoRepository.removeByUser(user);

	}

	public void removeVideo(int userId, int videoId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);
		
		videoRepository.getVideoByIdAndUser(videoId, user);
		
		videoRepository.removeVideo(videoId);
	}

	public VideoDTO updateVideo(int userId, int videoId, VideoDTO videoToUpdate)
			throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);

		Video video = videoRepository.getVideoById(videoId);

		if (user.getId() != video.getUser().getId())
			throw new InvalidParamException();

		if (!videoToUpdate.getDescription().equals(""))
			video.setDescription(videoToUpdate.getDescription());

		if (!videoToUpdate.getTitle().equals(""))
			video.setTitle(videoToUpdate.getTitle());

		videoRepository.save(video);

		return new VideoDTO(video);
	}

}
