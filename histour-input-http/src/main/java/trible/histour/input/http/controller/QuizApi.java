package trible.histour.input.http.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.val;
import trible.histour.application.port.input.QuizUseCase;
import trible.histour.application.port.input.dto.request.quiz.QuizGradeRequest;
import trible.histour.application.port.input.dto.response.quiz.QuizGradeResponse;
import trible.histour.application.port.input.dto.response.quiz.QuizzesResponse;
import trible.histour.input.http.controller.docs.QuizApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizApi implements QuizApiDocs {
	private final QuizUseCase quizUseCase;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/mission/{missionId}")
	@Override
	public SuccessResponse<QuizzesResponse> getQuizzes(Principal principal, @PathVariable long missionId) {
		val memberId = Long.parseLong(principal.getName());
		val response = quizUseCase.getQuizzes(memberId, missionId);
		return SuccessResponse.of(response);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/grade")
	@Override
	public SuccessResponse<QuizGradeResponse> gradeMemberQuiz(Principal principal,
																@RequestBody QuizGradeRequest request) {
		val memberId = Long.parseLong(principal.getName());
		val response = quizUseCase.gradeQuiz(memberId, request);
		return SuccessResponse.of(response);
	}
}
