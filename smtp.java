package systemProgramming;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class smtp {
    public static void main(String[] args) {
        try {
            // SSL soketi kullanarak SMTP'ye baglanmak
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("smtp.gmail.com", 465);

            // soket giri-cikis olusturma
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(in.readLine());

            out.write("HELO localhost\r\n");
            out.flush();
            System.out.println(in.readLine());

            // SMTP tanimlama
            out.write("AUTH LOGIN\r\n");
            out.flush();
            System.out.println(in.readLine());
            
            String encodedUsername = "YXlzZW51ci5lc3NpekBiaWwub211LmVkdS50cg==";
            String encodedPassword = "ZGh1YyB3b3BqIGx2eXQgaGJ0bQ==";

            out.write(encodedUsername + "\r\n");
            out.flush();
            System.out.println(in.readLine());

            out.write(encodedPassword + "\r\n");
            out.flush();
            System.out.println(in.readLine());
  
            out.write("MAIL FROM: <aysenur.essiz@bil.omu.edu.tr>\r\n");
            out.flush();
            System.out.println(in.readLine());

            out.write("RCPT TO: <ayseessiz4@gmail.com>\r\n");
            out.flush();
            System.out.println(in.readLine());

            out.write("DATA\r\n");
            out.flush();
            System.out.println(in.readLine());

            out.write("Subject: hello world \r\n");
            //out.write("MAIL From: your_email@example.com\r\n");
            //out.write("rcpt To: recipient@example.com\r\n");
            out.write("Bu mail kod ile gönderilmistir. \r\n");
            out.write(".\r\n");
            out.write(".\r\n");
            out.flush();
            System.out.println(in.readLine());

            out.write("QUIT\r\n");
            out.flush();
            System.out.println(in.readLine());

            // Soket'i kapiyoruz
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
