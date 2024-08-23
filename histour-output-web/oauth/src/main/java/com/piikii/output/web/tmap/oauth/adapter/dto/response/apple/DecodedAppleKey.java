package com.piikii.output.web.tmap.oauth.adapter.dto.response.apple;

import lombok.Builder;

@Builder
public record DecodedAppleKey(
	String kid,
	String alg
) {
}
