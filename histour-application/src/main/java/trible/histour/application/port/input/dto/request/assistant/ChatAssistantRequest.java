package trible.histour.application.port.input.dto.request.assistant;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "챗봇 생성 요청 정보")
public record ChatAssistantRequest(
		@Schema(description = "퀴즈 id", example = "1")
		long quizId,
		@Schema(description = "QA")
		List<String> qa,
		@Schema(description = "사용자 캐릭터 id", example = "1")
		int characterId
) {
}
