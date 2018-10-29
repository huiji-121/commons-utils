package com.endofmaster.commons.util;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author YQ.Huang
 */
public abstract class StreamUtils {

    public final static int BUFFER_SIZE = 4096;

    /**
     * Copy the contents of the given InputStream into a new byte array.
     * Leaves the stream open when done.
     *
     * @param in the stream to copy from
     * @return the new byte array that has been copied to
     * @throws IOException in case of I/O errors
     */
    public static byte[] copyToByteArray(InputStream in) throws IOException {
        if (in == null) throw new IllegalArgumentException("No InputStream specified");
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        copy(in, out);
        return out.toByteArray();
    }

    /**
     * Copy the contents of the given InputStream into a String.
     * Leaves the stream open when done.
     *
     * @param in      the InputStream to copy from
     * @param charset charset to use
     * @return the String that has been copied to
     * @throws IOException in case of I/O errors
     */
    public static String copyToString(InputStream in, Charset charset) throws IOException {
        if (in == null) throw new IllegalArgumentException("No InputStream specified");
        if (charset == null) throw new IllegalArgumentException("No Charset specified");
        StringBuilder out = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(in, charset);
        char[] buffer = new char[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = reader.read(buffer)) != -1) {
            out.append(buffer, 0, bytesRead);
        }
        return out.toString();
    }

    /**
     * Copy the contents of the given byte array to the given OutputStream.
     * Leaves the stream open when done.
     *
     * @param in  the byte array to copy from
     * @param out the OutputStream to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        if (in == null) throw new IllegalArgumentException("No input byte array specified");
        if (out == null) throw new IllegalArgumentException("No OutputStream specified");
        out.write(in);
    }

    /**
     * Copy the contents of the given String to the given output OutputStream.
     * Leaves the stream open when done.
     *
     * @param in      the String to copy from
     * @param charset the Charset
     * @param out     the OutputStream to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(String in, Charset charset, OutputStream out) throws IOException {
        if (in == null) throw new IllegalArgumentException("No input byte array specified");
        if (out == null) throw new IllegalArgumentException("No OutputStream specified");
        if (charset == null) throw new IllegalArgumentException("No Charset specified");
        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(in);
        writer.flush();
    }

    /**
     * Copy the contents of the given InputStream to the given OutputStream.
     * Leaves both streams open when done.
     *
     * @param in  the InputStream to copy from
     * @param out the OutputStream to copy to
     * @return the number of bytes copied
     * @throws IOException in case of I/O errors
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        if (in == null) throw new IllegalArgumentException("No InputStream specified");
        if (out == null) throw new IllegalArgumentException("No OutputStream specified");
        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }
}
