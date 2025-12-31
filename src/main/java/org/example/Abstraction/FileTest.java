package org.example.Abstraction;

abstract class File {
    File() {
        System.out.println("Default constructor");
    }
    String id;
    File(String id) {
        this.id = id;
        System.out.println("File ID"+id);
    }
    abstract void open();
}

class Image extends File {

    String id;
    Image() {
        super();//Implicit
    }
    Image(String id) {
        super(id);
    }
    @Override
    void open() {
        System.out.println("Opening Image"+super.id);
    }
}

class Video extends File {

    @Override
    void open() {
        System.out.println("Opening Video");
    }
}

public class FileTest {
    public static void main(String[] args) {
        File imageFile1 = new Image();
        imageFile1.open();
        File imageFile2 = new Image("HSHH");
        imageFile2.open();
    }
}