package ua.lviv.iot.project_doc.repository;

import java.util.List;

public interface CsvRepository {
	public List<String[]> readAll(String filepath);

    public void writeAll(String filepath, List<String[]> data);
}
