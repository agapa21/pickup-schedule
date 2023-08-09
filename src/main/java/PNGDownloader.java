import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class PNGDownloader {

    PNGDownloader(String imgValue) {

        String savePath = "output.png";

        try {
            String base64Data = imgValue.split(",")[1];

            byte[] decodedBytes = org.apache.commons.codec.binary.Base64.decodeBase64(base64Data);

            File outputFile = new File(savePath);
            FileUtils.writeByteArrayToFile(outputFile, decodedBytes);

            System.out.println("File successfully saved " + savePath);

            EmailSender emailSender = new EmailSender();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
