package com.iweb.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Fu {
    public void eat() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("D:\\a.txt");
    }

    public void sleep() {

    }
}

