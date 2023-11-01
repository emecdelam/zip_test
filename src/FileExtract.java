import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileExtract {
    public static String extractor(String filePath) {
        Path path = Paths.get(filePath);

        try {
            return String.join("\n", Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void compressor(String filePath){
        zwriter(Encoder.encoder(extractor(filePath)),filePath);
    }
    public static void decompressor(String filePath){
        writer(Decoder.decoder(Objects.requireNonNull(extractor(filePath))),filePath);
    }
    public static void zwriter(String bitsData, String filePath) {
        Path originalPath = Paths.get(filePath);
        String fileName = originalPath.getFileName().toString();
        String czipFileName = fileName.replaceFirst("[.][^.]+$", "") + ".czip";
        Path directory = originalPath.getParent();
        Path czipFilePath = (directory != null) ? directory.resolve(czipFileName) : Paths.get(czipFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(czipFilePath.toString()))) {
            writer.write(bitsData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writer(String bitsData, String filePath) {
        filePath = filePath + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(bitsData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}