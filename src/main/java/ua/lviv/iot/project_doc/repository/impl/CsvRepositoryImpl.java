package ua.lviv.iot.project_doc.repository.impl;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import ua.lviv.iot.project_doc.repository.CsvRepository;


@Repository
public class CsvRepositoryImpl implements CsvRepository {

	@Override
    public List<String[]> readAll(String filepath) {
        try (CSVReader reader = new CSVReader(new FileReader(filepath))) {
            List<String[]> result = reader.readAll();
            return result;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeAll(String filepath, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filepath))) {
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
