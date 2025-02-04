import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

    /**
     *
     * @author wulft
     *
     * Use the thread safe NIO IO library to read a file
     */
    public class FileInspector
    {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args)
        {
            JFileChooser chooser = new JFileChooser();
            File selectedFile;
            String rec = "";
            int charCnt = 0;
            int wordCnt = 0;
            int line = 0;
            String fileName = "";
            String[] words;

            try {

                File workingDirectory = new File(System.getProperty("user.dir"));

                chooser.setCurrentDirectory(workingDirectory);


                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();

                    InputStream in =
                            new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in));



                    while (reader.ready()) {
                        rec = reader.readLine();
                        line++;
                        charCnt += rec.length();
                        words = rec.split(" ");
                        wordCnt += words.length;

                        System.out.printf("\nLine %4d %-60s ", line, rec);
                    }
                    reader.close();
                    System.out.println("\n\nData file read!");
                    System.out.println("The filename is " + file.getFileName());
                    System.out.println("The total lines is " + line);
                    System.out.println("The total words is " + wordCnt);
                    System.out.println("The total characters is " + charCnt);


                } else
                {
                    System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
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

