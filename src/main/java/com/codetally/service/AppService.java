package com.codetally.service;

import java.io.*;

/**
 * Created by greg on 25/06/17.
 */
public class AppService {
    public void getStaticFile(String filename, OutputStream os) {
        InputStream ios = getClass().getResourceAsStream(filename);

        byte[] buffer = new byte[4096];

        try {
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
            ios.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getStaticFileLength(String filename) throws IOException {
        InputStream in = getClass().getResourceAsStream(filename);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
        return out.size();
    }
}
