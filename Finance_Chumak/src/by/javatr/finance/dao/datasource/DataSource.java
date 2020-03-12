package by.javatr.finance.dao.datasource;


import by.javatr.finance.dao.converter.Converter;
import by.javatr.finance.dao.converter.exception.ConverterException;
import by.javatr.finance.dao.datasource.exception.DataSourceException;
import by.javatr.finance.dao.validator.DAOValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource<T> implements DAOValidator {

    private String filePath;

    public DataSource(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private boolean isExist(File file){
        boolean fileExists = true;
        if (!isNull(file)){
            fileExists = file.exists();
        }
        return fileExists;
    }

    public List<T> read(Converter<T> converter) throws DataSourceException {
        if (isNull(filePath)) {
            String message = "Path has null value.";
            throw new DataSourceException(message);
        }
        File file = new File(filePath);
        if (!isExist(file)) {
            String message = "There is no file.";
            throw new DataSourceException(message);
        }

        try (Reader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader);) {
            List<T> list = new ArrayList<>();
            String currentLine;
            while (!isNull(currentLine = reader.readLine())) {
                if (currentLine.isEmpty()) {
                    continue;
                }
                T element = converter.getFromString(currentLine);
                list.add(element);
            }
            return list;
        } catch (IOException | ConverterException e) {
            String message = e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

    public void write(T element, boolean append, Converter<T> converter)
            throws DataSourceException {
        if (isNull(element)) {
            String message = "Element has null value.";
            throw new DataSourceException(message);
        }
        try (Writer fileWriter = new FileWriter(filePath, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            String str = converter.convertToString(element);
            writer.write(str);
            writer.newLine();
        } catch (ConverterException|IOException e) {
            String message = e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

    public void write(List<T> elements, Converter<T> converter)
            throws DataSourceException {
        if (isNull(elements)) {
            String message = "Elements has null value.";
            throw new DataSourceException(message);
        }
        try (Writer fileWriter = new FileWriter(filePath,false);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (T element:elements
                 ) {
                String str = converter.convertToString(element);
                writer.write(str);
                writer.newLine();
            }

        } catch (ConverterException|IOException e) {
            String message = e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

}
