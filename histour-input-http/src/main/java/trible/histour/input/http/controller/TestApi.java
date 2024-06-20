package trible.histour.input.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import trible.histour.input.http.docs.TestApiDocs;

@RestController
@RequiredArgsConstructor
public class TestApi implements TestApiDocs {

	@GetMapping
	public String test() {
		return "test";
	}
}
