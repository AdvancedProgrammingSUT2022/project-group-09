package game.civilization.Controller.NetworkController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",8000);
        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

        String xml= new String(Files.readAllBytes(Paths.get("data/gameInformation.xml")));


        byte[] data=xml.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
    }
}
