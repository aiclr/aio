package org.bougainvilleas.ilj.study.chapter02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteProcess
{
    public static void main(String[] args)
    {
        try
        {
            Process process = Runtime.getRuntime().exec("java --version");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (null!=(line=bufferedReader.readLine()))
                System.out.println(line);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
