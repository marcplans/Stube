package hello.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hello.application.VideoController;
import hello.application.dto.VideoDTO;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@RestController
@CrossOrigin
@RequestMapping("users/{userId}/videos")
public class VideoRestController {

	@Autowired
	private VideoController controller;

	private String toJson(Object object) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}

	@PostMapping(produces = "application/json;charset=UTF-8")
	public String upload(@PathVariable int userId, @RequestBody String jVideo)
			throws NotFoundException, InvalidParamException {

		VideoDTO video = new Gson().fromJson(jVideo, VideoDTO.class);

		VideoDTO newVideo = controller.uploadVideo(userId, video);

		return toJson(newVideo);
	}

	@GetMapping(produces = "application/json;charset=UTF-8")
	public String getAllVideos(@PathVariable int userId) throws NotFoundException {

		List<VideoDTO> videos = controller.getAllVideos(userId);

		return new Gson().toJson(videos);
	}

	@GetMapping(value = "/{videoId}", produces = "application/json;charset=UTF-8")
	public String getVideo(@PathVariable int userId, @PathVariable int videoId)
			throws NotFoundException, InvalidParamException {

		VideoDTO video = controller.getVideo(userId, videoId);

		return toJson(video);

	}

	@DeleteMapping(produces = "application/json;charset=UTF-8")
	public void removeAllVideos(@PathVariable int userId) throws Exception {
		controller.removeAllVideos(userId);
	}

	@DeleteMapping(value = "/{videoId}", produces = "application/json;charset=UTF-8")
	public void removeVideo(@PathVariable int userId, @PathVariable int videoId)
			throws NotFoundException, InvalidParamException {
		controller.removeVideo(userId, videoId);
	}

	@PutMapping(value = "/{videoId}", produces = "application/json;charset=UTF-8")
	public String updateVideo(@PathVariable int userId, @PathVariable int videoId, @RequestBody String jVideo)
			throws InvalidParamException, NotFoundException {

		VideoDTO videoToUpdate = new Gson().fromJson(jVideo, VideoDTO.class);

		VideoDTO video = controller.updateVideo(userId, videoId, videoToUpdate);

		return toJson(video);
	}

}
