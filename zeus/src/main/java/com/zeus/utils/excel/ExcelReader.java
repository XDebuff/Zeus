package com.zeus.utils.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ExcelReader<T> {

    protected ExcelProcessor mExcelProcessor;
    private List<T> mList;

    public ExcelReader() {
        super();
        mExcelProcessor = new ExcelProcessor();
    }

    public List<T> read(File file) throws IOException {
        if (file == null || !file.exists()) {
            return new ArrayList<>();
        }
        mExcelProcessor.setFilePath(file.getAbsolutePath());
        List<String[]> datum = mExcelProcessor.read();
        mList = parse(datum);
        return mList;
    }

    public abstract List<T> parse(List<String[]> datum);

    public List<T> getDatum() {
        return mList;
    }

}
