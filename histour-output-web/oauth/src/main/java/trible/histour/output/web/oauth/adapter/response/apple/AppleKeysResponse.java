package trible.histour.output.web.oauth.adapter.response.apple;

import java.util.List;

public record AppleKeysResponse(
	List<AppleKey> keys
) {
}
