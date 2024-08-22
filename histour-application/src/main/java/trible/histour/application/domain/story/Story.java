package trible.histour.application.domain.story;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Story {
	Long id;
	long placeId;
	String storyType;
	String storyName;
}
