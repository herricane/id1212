package se.kth.server;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import com.sun.mail.imap.IMAPMessage;
import org.apache.commons.mail.util.MimeMessageParser;

public class FetchServiceImpl extends UnicastRemoteObject implements FetchService {
    public FetchServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String fetchMail(String host, String storeType, String user, String password) {
        String firstMail = null;
        try {
            Properties properties = new Properties();

            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imaps.host", host);
            properties.put("mail.imaps.port", "993");

            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore(storeType);

            store.connect(host, user, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            int last = messages.length - 10;
            Message message = messages[last];
            IMAPMessage msg = (IMAPMessage) message;

            MimeMessageParser parser = new MimeMessageParser(msg);
            parser.parse();
            String subject = parser.getSubject();
            String content = parser.getPlainContent();

            firstMail = subject + "\n------------------------\n" + content;

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return firstMail;
    }
}
