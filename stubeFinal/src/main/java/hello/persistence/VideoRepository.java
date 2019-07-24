package hello.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.domain.User;
import hello.domain.Video;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@Repository
public class VideoRepository {

	@Autowired
	private HelperVideoRepository repository;

	public void save(Video video) throws InvalidParamException {
		if (video == null)
			throw new InvalidParamException();

		try {
			repository.save(video);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new InvalidParamException();
		}
	}

	public List<Video> getAllVideos(User user) {
		return repository.findAllByUser(user);
	}

	public Video getVideoById(int videoId) throws NotFoundException {
		try {
			return repository.findById(videoId).get();
		} catch (Exception exception) {
			throw new NotFoundException();
		}
	}

	public void removeByUser(User user) {
		repository.removeByUser(user);
	}

	public void removeVideo(int videoId) throws NotFoundException {
		repository.deleteById(videoId);
	}
	
	public Video getVideoByIdAndUser(int videoId, User user) throws NotFoundException, InvalidParamException {
		Video video = repository.findByIdAndUser(videoId, user);
		if(video == null)
			throw new InvalidParamException();
		return video; 
	}

}
