package game.civilization.Controller.NetworkController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        System.out.println(socket + "is connected");

        int length = dataInputStream.readInt();
        byte[] data = new byte[length];
        dataInputStream.readFully(data);
        String xml = new String(data, StandardCharsets.UTF_8);

        System.out.println(xml);

        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        if(xml.length() != 0){
            GameDataBaseSaving game = (GameDataBaseSaving)xStream.fromXML(xml);
       //     game.setToGameDataBase();
        }

//        try {
//            FileWriter fileWriter;
//            if (Files.exists(Paths.get("data/gameInformation2.xml")))
//                fileWriter = new FileWriter("data/gameInformation2.xml",false);
//            else{
//                new File("data").mkdir();
//                fileWriter = new FileWriter("data/gameInformation2.xml",false);
//            }
//            fileWriter.write(xml);
//            fileWriter.close();
//        } catch (IOException ignored) {
//        }
//    }

    }
}
