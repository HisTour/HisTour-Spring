package trible.histour.output.web.oauth.adapter.response.apple;

import lombok.Builder;

@Builder
public record DecodedAppleKey(
	String kid,
	String alg
) {
}
