package com.aut;

import java.io.*;

/**
 * this class is mainly used for reading files and dealing with them.
 */
class InputFileReader {
    private File file;
    private String path;
    private FileInputStream fin;
    private byte[] bytes;

    /**
     * creates file and fileInputStream with the given path
     *
     * @param path input path
     */
    InputFileReader(String path) {
        this.path = path;
        System.out.println(path);
        try {
            file = new File(path);
            fin = new FileInputStream(file);
            bytes = new byte[(int) file.length()];

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

    }

    /**
     * reads bytes from file and saves it in bytes array.
     *
     * @return bytes read from file
     */
    byte[] readFile() {
        try {
            fin.read(bytes);
            fin.close();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return bytes;
    }

    /**
     * @return file directory
     */
    String getDirectory() {

        return file.getParent();
    }

    /**
     * gets file extension
     *
     * @return extension
     */
    String getExtension() {
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i);
        }
        return extension;
    }

    /**
     * gets filename without the extension.
     *
     * @return filename
     */
    String getFileName() {
        return file.getName().substring(0, file.getName().lastIndexOf('.'));
    }

}