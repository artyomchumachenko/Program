package com.company.lesson.two.workwithfiles;

import java.io.*;
import java.util.Arrays;

public class MainFile {

    public static void main(String[] args) throws IOException {
//        File myDir = new File("C://Java//Files//Lesson2//test");
//        File myFile = new File("C://Java//Files//Lesson2//test//test.txt");
//        if (!myDir.exists()) {
//            myDir.mkdirs();
//        }
//        if (!myFile.exists()) {
//            myFile.createNewFile();
//        } else {
//            myFile.renameTo(new File("C://Java//Files//Lesson2//test//test.pdf"));
//        }
        String path = "C://Java//Files//Lesson2//test//test.txt";
        File myFile = new File(path);
//        File myDir = myFile.getParentFile();
//        myDir.mkdirs();
//        myFile.createNewFile();
//        FileWriter writeFile = new FileWriter(path, true);
//        writeFile.append("Hello my dear Friend!");
//        writeFile.append("\nWhat the Lesson?");
//        writeFile.append(System.lineSeparator());
//        writeFile.append("wHo is Andrey?");
//        writeFile.flush();
//        writeFile.close();
//        int size = 10;
//        FileReader reader = new FileReader(myFile);
//        char[] buf = new char[size];
//        int c;
//        while ((c = reader.read(buf)) > 0) {
//            if (c < size) {
//                buf = Arrays.copyOf(buf, c);
//            }
//            System.out.println(buf);
//        }
//        BufferedReader reader = new BufferedReader(new FileReader(myFile)); // myFile = path?
//        int c;
//        while ((c = reader.read()) != -1) {
//            System.out.print((char) c);
//        }
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line + "|");
//        }
//        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
//        writer.newLine();
//        writer.append("New date");
//        writer.newLine();
//        writer.append("Next line");
//        writer.flush();
//        writer.close();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true)));
//        BufferedWriter secondWriter = new BufferedWriter(new FileWriter(path, true)); // luchshe
//
//        String line;
//        while (!(line = reader.readLine()).equals("ESC")) {
//            secondWriter.write(line);
//            secondWriter.newLine();
//            secondWriter.flush();
//        }
//        secondWriter.flush();
//        secondWriter.close();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        BufferedReader reader = new BufferedReader(new FileReader(path));
//        BufferedReader secondReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
//        String line;
//        while ((line = secondReader.readLine()) != null) {
//            writer.write(line);
//            writer.newLine();
//            writer.flush();
//        }
//        writer.close();

    }
}
