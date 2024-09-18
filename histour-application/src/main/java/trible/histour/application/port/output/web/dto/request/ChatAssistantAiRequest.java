package trible.histour.application.port.output.web.dto.request;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ChatAssistantAiRequest(
		int character,
		List<String> QA,
		String mission_name,
		String submission_name,
		int task_sequence
) {
	public static ChatAssistantAiRequest of(int character, List<String> qa, String missionName, String submissionName,
											int taskSequence) {
		return ChatAssistantAiRequest.builder()
				.character(character)
				.QA(qa)
				.mission_name(missionName)
				.submission_name(submissionName)
				.task_sequence(taskSequence)
				.build();
	}
}
