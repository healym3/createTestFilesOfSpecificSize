package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
//https://stackoverflow.com/questions/245251/create-file-with-given-size-in-java

public class Main {

    public static void main(String[] args) {
        final long HUGE_FILE_SIZE = 1024*1024*100;
        final ByteBuffer buf = ByteBuffer.allocate(4).putInt(2);
        buf.rewind();

        final OpenOption[] options = { StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW , StandardOpenOption.SPARSE };
        final Path hugeFile = Paths.get("size100.txt");

        try (final SeekableByteChannel channel = Files.newByteChannel(hugeFile, options);) {
            channel.position(HUGE_FILE_SIZE);
            channel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
