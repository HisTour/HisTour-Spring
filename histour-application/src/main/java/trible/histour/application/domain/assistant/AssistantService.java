package trible.histour.application.domain.assistant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.AssistantUseCase;
import trible.histour.application.port.input.dto.request.assistant.ChatAssistantRequest;
import trible.histour.application.port.input.dto.response.assistant.ChatAssistantResponse;
import trible.histour.application.port.output.persistence.MissionPort;
import trible.histour.application.port.output.persistence.PlacePort;
import trible.histour.application.port.output.persistence.QuizPort;
import trible.histour.application.port.output.web.DataPort;
import trible.histour.application.port.output.web.dto.request.ChatAssistantAiRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssistantService implements AssistantUseCase {
	private final DataPort dataPort;
	private final QuizPort quizPort;
	private final MissionPort missionPort;
	private final PlacePort placePort;

	@Override
	public ChatAssistantResponse getChatAssistant(ChatAssistantRequest chatAssistantRequest) {
		val quiz = quizPort.findById(chatAssistantRequest.quizId());
		val mission = missionPort.findById(quiz.getMissionId());
		val place = placePort.findById(mission.getPlaceId());

		val chatAssistantAiRequest = ChatAssistantAiRequest.of(chatAssistantRequest.characterId(),
				chatAssistantRequest.qa(), place.getName(), mission.getName(), quiz.getSequence());

		val response = dataPort.getChatAssistant(chatAssistantAiRequest);
		return ChatAssistantResponse.of(response.data().url());
	}
}
