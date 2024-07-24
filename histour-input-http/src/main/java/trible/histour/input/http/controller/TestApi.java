package trible.histour.input.http.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import trible.histour.input.http.controller.docs.TestApiDocs;
import trible.histour.input.http.controller.dto.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestApi implements TestApiDocs {

	@GetMapping
	public ResponseEntity<SuccessResponse<?>> test() {
		return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.ofEmpty());
	}
}
