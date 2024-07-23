package trible.histour.input.http.controller.docs;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import trible.histour.input.http.common.dto.BaseResponse;
import trible.histour.input.http.common.dto.ErrorResponse;
import trible.histour.input.http.common.dto.SuccessResponse;



@Tag(name = "test", description = "SWAGGER 테스트용 API")
public interface TestApiDocs {
	@Operation(
			summary = "서버 연결 테스트",
			description = "서버 연결을 테스트합니다.",
			responses = { @ApiResponse(
							responseCode = "200",
							description = "테스트 성공",
							content = @Content(schema = @Schema(implementation = SuccessResponse.class))
					), @ApiResponse(
							responseCode = "500",
							description = "서버 내부 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					)
			}
	)
	ResponseEntity<BaseResponse> test();
}

