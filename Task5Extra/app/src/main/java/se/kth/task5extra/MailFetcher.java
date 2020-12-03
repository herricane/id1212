package se.kth.task5extra;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPMessage;
import org.apache.commons.mail.util.MimeMessageParser;

public class MailFetcher {
    public static String fetchMail(String user, String password, int index) {
        String mail = null;
        String host = "webmail.kth.se";

        try {
            Properties properties = new Properties();

            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imaps.host", host);
            properties.put("mail.imaps.port", "993");

            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("imaps");

            store.connect(host, user, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            int last = messages.length - index;
            Message message = messages[last];
            IMAPMessage msg = (IMAPMessage) message;

            MimeMessageParser parser = new MimeMessageParser(msg);
            parser.parse();
            String subject = parser.getSubject();
            String content = parser.getPlainContent();

            mail = subject + "\n------------------------------------------------\n" + content;

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail;
    }

}
