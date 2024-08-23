package com.piikii.output.web.tmap.oauth.adapter.dto.response.kakao;

import java.time.LocalDateTime;

public record KakaoResponse(
	String id,
	LocalDateTime connected_at,
	Properties properties,
	KakaoAccount kakao_account
) {

	public record Properties(
		String nickname,
		String profile_image,
		String thumbnail_image
	) {
	}

	public record KakaoAccount(
		boolean profile_needs_agreement,
		Profile profile,
		boolean has_email,
		boolean email_needs_agreement,
		boolean is_email_valid,
		boolean is_email_verified,
		String email,
		boolean has_birthday,
		boolean birthday_needs_agreement,
		String birthday,
		String birthday_type,
		boolean has_gender,
		boolean gender_needs_agreement,
		String gender
	) {
	}

	public record Profile(
		String nickname,
		String thumbnail_image_url,
		String profile_image_url,
		boolean is_default_image
	) {
	}
}
