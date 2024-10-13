package trible.histour.input.http.controller.docs;

import java.security.Principal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.application.port.input.dto.request.quiz.QuizGradeRequest;
import trible.histour.application.port.input.dto.response.quiz.QuizGradeResponse;
import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@Tag(name = "QuizApi", description = "퀴즈 관련 api")
public interface QuizApiDocs {

	@Operation(
		summary = "퀴즈 조회 api",
		description = "미션에 해당하는 퀴즈 목록을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success"),
			@ApiResponse(responseCode = "400", description = "해당 미션 수행에 적합하지 않음")
		}
	)
	SuccessResponse<QuizzesResponse> getQuizzes(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			description = "미션 id",
			required = true,
			in = ParameterIn.PATH
		) long missionId
	);

	@Operation(
			summary = "퀴즈 채점 API",
			description = "퀴즈를 채점합니다.",
			responses = {
				@ApiResponse(responseCode = "201", description = "CREATED success"),
				@ApiResponse(responseCode = "400", description = "이미 해결한 퀴즈"),
			}
	)
	SuccessResponse<QuizGradeResponse> gradeMemberQuiz(
			@Parameter(hidden = true) Principal principal,
			@RequestBody(
				description = "퀴즈 채점 요청값",
				required = true,
				content = @Content(schema = @Schema(implementation = QuizGradeRequest.class))
			) QuizGradeRequest quizGradeRequest
	);
}
