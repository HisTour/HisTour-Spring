package trible.histour.application.port.output.persistence;

public interface TestPort {
	void save();

	void find(long id);

	void update(long id);

	void delete(long id);
}
