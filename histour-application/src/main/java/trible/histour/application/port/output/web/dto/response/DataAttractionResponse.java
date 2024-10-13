package trible.histour.application.port.output.web.dto.response;

import java.util.List;

public record DataAttractionResponse(
		Response response
) {

	public record Response(
			Header header,
			Body body
	) {

		public record Header(
				String resultCode,
				String resultMsg
		) {
		}

		public record Body(
				Items items,
				int numOfRows,
				int pageNo,
				int totalCount
		) {

			public record Items(
					List<Item> item
			) {

				public record Item(
						String tid,
						String tlid,
						String stid,
						String stlid,
						String title,
						String mapX,
						String mapY,
						String audioTitle,
						String script,
						String playTime,
						String audioUrl,
						String langCode,
						String imageUrl,
						String createdtime,
						String modifiedtime
				) {
				}
			}
		}
	}
}
