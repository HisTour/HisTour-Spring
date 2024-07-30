package trible.histour.output.postgresql.persistence.converter;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import trible.histour.application.domain.mission.MissionType;
import trible.histour.common.exception.ExceptionCode;
import trible.histour.common.exception.HistourException;

public class MissionTypeListConverter implements AttributeConverter<List<MissionType>, String> {
	private static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

	@Override
	public String convertToDatabaseColumn(List<MissionType> missionTypes) {
		try {
			return mapper.writeValueAsString(missionTypes);
		} catch (IOException exception) {
			throw new HistourException(ExceptionCode.INTERNAL_SERVER_ERROR, exception.getMessage());
		}
	}

	@Override
	public List<MissionType> convertToEntityAttribute(String str) {
		try {
			return mapper.readValue(str, new TypeReference<>() {
			});
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
}
