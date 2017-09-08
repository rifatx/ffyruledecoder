package com.ffy.ruleDecoder;

import com.fortify.util.CryptoUtil;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.out.println(System.getProperty("user.dir"));

        String key = "1fea047f-dee0ac89-b5db25a6-b0c3a4cf";
        String resDir = "res";
        List<String> binFiles = Arrays.asList("core_dotnet", "extended_dotnet");

        for (String file : binFiles) {
            try (InputStream fs = new FileInputStream(new File(Paths.get(resDir, file + ".bin").toString()))) {
                try (InputStream is = CryptoUtil.decryptCompressed(fs, key)) {
                    String data = IOUtils.toString(is);

                    try (PrintWriter out = new PrintWriter(Paths.get(resDir, file + ".txt").toString())) {
                        out.println(data);
                    }
                }
            }
        }
    }
}