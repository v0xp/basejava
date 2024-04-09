package com.urise.webapp;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MainFile {
    public static int fact(int n) {
        if (n == 1) {
            return 1;
        }
        int r = n * fact(n - 1);
        return r;

    }

    private static void addFiles(File folder, Collection<File> allFiles) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                allFiles.add(f);
                addFiles(f, allFiles);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        List<String> strings = Files.readAllLines(Paths.get("C:\\Users\\Маша\\Desktop\\Java" +
//                "\\BaseJava\\basejava\\.gitignore"));
//        for (String lines : strings) {
//            System.out.println(lines);
//        }
//        URL url = new URL("https://maina.club/uploads/posts/2023-02/1675222845_maina-club-p-prozrachnaya-voda-korabli-krasivo-1.jpg");
//
//        try (InputStream inputStream = url.openStream()) {
//            Files.copy(inputStream, Paths.get("C:\\Users\\Маша\\Desktop\\newFile.jpg"));
//        }
//        System.out.println("OK");
//        System.out.println(fact(4));
//
//
//        File folder = new File("C:\\Users\\Маша\\IdeaProjects\\basejava\\basejava\\src\\com\\urise\\webapp");
//        for(File file : Objects.requireNonNull(folder.listFiles())){
//                if(folder.isDirectory()){
//                System.out.println("Directory:" + file.getName());
//            }
//        }
//
//        Collection<File> allFiles = new ArrayList<>();
//        addFiles(folder, allFiles);
//
//        for( File file : allFiles){
//            if(file.isDirectory()){
//            System.out.println("Directory: " + file.getName());
//            }else {
//                System.out.println("--File: " + file.getName());
//            }
//        }

//        class MyFileVisitor extends SimpleFileVisitor<Path> {
//
//            @Override
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//
//                List<String> lines = Files.readAllLines(file);
//                for (String s: lines) {
//                    if(attrs.isDirectory()){
//                        System.out.println("D: " + s);
//                    }else {
//                        System.out.println("--F: " + s);
//                    }
//                        break;
//                    }
//                return FileVisitResult.CONTINUE;
//            }
//        }
//        Files.walkFileTree(Paths.get("C:\\Users\\Маша\\IdeaProjects\\basejava\\basejava\\src\\com\\urise\\webapp"), new MyFileVisitor());
/////////////////////////////////////////////////////////////////////////////////////////////////

//        File file = new File("C:\\Users\\Маша\\Desktop\\textFile.txt");
//        file.createNewFile();

//////////////////////////////////////////////////////////
//       String hello = "Hello World";
//       try(FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Маша\\Desktop\\textFile.txt");){
//           outputStream.write(hello.getBytes());
//       }


///////////////////////////////////////////////////////////////////////////
//        String hello = "Hello World";
//        String fileText = "C:\\Users\\Маша\\Desktop\\textFile.txt";
//        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileText))){
//             bufferedOutputStream.write(hello.getBytes());
//        }
////////////////////////////////////////////////////////////////////////////
//        File file = new File("C:\\Users\\Маша\\Desktop\\Catalog");
//        file.mkdir();
//        File file2 = new File("C:\\Users\\Маша\\Desktop\\Catalog\\textFile.txt");
//        file2.createNewFile();

//////////////////////////////////////////////////////////////////////////////
//        String hello = "Hello World";
//        String path = "C:\\Users\\Маша\\Desktop\\Catalog\\textFile.txt";
//        File file = new File(path);
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        Files.write(Paths.get(path), hello.getBytes());

///////////////////////////////////////////////////////////////////////////
//        String hello = "Hello World";
//        Files.write(Paths.get("C:\\Users\\Маша\\Desktop\\Catalog\\textFile.txt"), hello.getBytes());
//////////////////////////////////////////////////////////////////////////////
        String path = "C:\\Users\\Маша\\Desktop\\Catalog\\textFile.txt";
        Person person = new Person(12, 12);
        try (ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(path))){
            object.writeObject(person);
        }

    }

    public static class Person implements Serializable {
        private static final long serialVersionUID = 1L;

        public int age;
        public double height;

        Person (int age, double height)
        {
            this.age = age;
            this.height = height;
        }
    }
}
