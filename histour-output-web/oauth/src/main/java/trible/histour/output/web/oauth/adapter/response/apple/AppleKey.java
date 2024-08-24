package trible.histour.output.web.oauth.adapter.response.apple;

public record AppleKey(
	String kty,
	String kid,
	String use,
	String alg,
	String n,
	String e
) {
}
