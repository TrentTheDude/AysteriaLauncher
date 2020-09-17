package me.trent.utils;

import me.trent.Storage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class FileUtils {

    public static String getData(File file) {
        String data = "";
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()){
                if (data.equalsIgnoreCase("")){
                    //empty
                    data = sc.nextLine();
                }else{
                    data = data+" "+sc.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeData(File file, String data) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void download(String remotePath, String localPath) {
        BufferedInputStream in = null;
        FileOutputStream out = null;

        Utils.log("local: "+localPath);
       // Storage.path = localPath;

        try {
            //url = new URL("http://api.trent.pw/lockit/getVersion");
            //                con = url.openConnection();
            //                con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            //                con.connect();
            //                is = con.getInputStream();
            URL url = new URL(remotePath);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            conn.connect();
            int totalSize = conn.getContentLength();

            if (totalSize < 0) {
                System.out.println("Could not get the file size");
            } else {
                System.out.println("File size: " + totalSize);
            }

           // in = new BufferedInputStream(url.openStream());
            in = new BufferedInputStream(conn.getInputStream());
            out = new FileOutputStream(localPath);
            byte data[] = new byte[1024];
            int count;
            double sumCount = 0.0;

            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);

                sumCount += count;
                if (totalSize > 0) {
                    double percent = (sumCount / totalSize * 100.0);
                   // System.out.println("Percent: " + percent + "%");
                  //  Storage.update_percent = Utils.round(percent, 2);
                }
            }

            in.close();
            out.close();

           // Storage.updated = true;

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
        }
    }

}
