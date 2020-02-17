package com.zeus.utils.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 文件操作类
 ***************************************************/
public class FileUtils {

    public boolean createFile(File file) {
        if (file != null) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean createFile(String filePath) {
        checkPathValid(filePath);
        File file = new File(filePath);
        try {
            if (file.exists() || file.isDirectory()) {
                return false;
            }
            File dir = file.getParentFile();
            if (!dir.exists()) {
                if (createDir(dir.getAbsolutePath())) {
                    return file.createNewFile();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFile(String filePath) {
        checkPathValid(filePath);
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    private boolean stringIsEmpty(String filePath) {
        return filePath == null || "".equals(filePath);
    }

    private boolean checkPathValid(String filePath) {
        if (stringIsEmpty(filePath)) {
            throw new IllegalArgumentException("filePath is null or empty");
        }
        return true;
    }

    public String readFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            fileReader.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readFile(String filePath) {
        checkPathValid(filePath);
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            fileReader.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToFile(String filePath, String content) {
        writeToFile(filePath, content);
    }

    public void writeToFile(String filePath, String content, boolean append) {
        checkPathValid(filePath);
        if (content == null) {
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(filePath);
        }
        try {
            FileWriter writer = new FileWriter(file, append);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExists(String filePath) {
        checkPathValid(filePath);
        return new File(filePath).exists();
    }

    public void copyFile(String sourcePath, String targetPath) {
        FileInputStream input = null;
        FileOutputStream output = null;
        if (!isExists(sourcePath)) {
            return;
        }
        if (!isExists(targetPath)) {
            createFile(targetPath);
        }
        try {
            input = new FileInputStream(new File(sourcePath));
            output = new FileOutputStream(new File(targetPath));
            byte[] bt = new byte[1024];
            int realbyte = 0;
            while ((realbyte = input.read(bt)) > 0) {
                output.write(bt, 0, realbyte);
            }
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveFile(String sourcePath, String targetPath) {

    }

    public boolean deleteDir(String dir) {
        return false;
    }

    public boolean deleteFilesFromDir(String dir) {
        return false;
    }

    public boolean createDir(String dir) {
        checkPathValid(dir);
        File file = new File(dir);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return false;
    }

    public List<File> getFilesFromDir(String dir) {
        File dirFile = new File(dir);
        if (isExists(dir) && dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            return Arrays.asList(files);
        }
        return null;
    }

}
