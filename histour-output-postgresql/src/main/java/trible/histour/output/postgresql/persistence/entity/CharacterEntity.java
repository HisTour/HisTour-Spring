package trible.histour.output.postgresql.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import trible.histour.application.domain.character.Character;
import trible.histour.application.domain.character.CharacterType;

@Entity
@Table(name = "character", schema = "histour")
@NoArgsConstructor
public class CharacterEntity extends BaseEntity {
		@Column(nullable = false)
		@Enumerated(EnumType.STRING)
		private CharacterType characterType;

		public CharacterEntity(Character character) {
				this.characterType = character.getCharacterType();
		}

		public Character toDomain() {
				return Character.builder()
								.id(getId())
								.characterType(characterType)
								.build();
		}
}
