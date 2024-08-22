package trible.histour.application.domain.memberstory;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberStory {
	Long id;
	long memberId;
	long placeId;
	String storyType;
}
