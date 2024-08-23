package com.piikii.output.web.tmap.oauth.adapter.dto.response.apple;

public record AppleKey(
	String kty,
	String kid,
	String use,
	String alg,
	String n,
	String e
) {
}
