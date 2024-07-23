package trible.histour.input.http.controller;

import static trible.histour.input.http.common.dto.SuccessResponse.success;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import trible.histour.input.http.common.dto.BaseResponse;
import trible.histour.input.http.controller.docs.TestApiDocs;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestApi implements TestApiDocs {

	@GetMapping
	public ResponseEntity<BaseResponse> test() {
		return ResponseEntity.ok(success("Test Success"));
	}
}
