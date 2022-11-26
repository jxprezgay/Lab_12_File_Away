import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        String fileName;

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                fileName = selectedFile.getName();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int lineCount = 0;
                int wordCount = 0;
                int charactersCount = 0;

                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    lineCount++;
                }

                for(String line:lines)
                {
                    String words[] = line.split(" ");
                    wordCount += words.length;
                    charactersCount += line.length();
                }

                reader.close();
                System.out.println("Data file read!");
                System.out.println("summary report of " + fileName);
                System.out.println("number of lines is " + lineCount);
                System.out.println("number of words is " + wordCount);
                System.out.println("number or characters is " + charactersCount);
               // System.out.printf("File: %d\nLines: %d\nWords: %d\nCharacters: %d\n",fileName, lineCount, wordCount, charactersCount);
            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}