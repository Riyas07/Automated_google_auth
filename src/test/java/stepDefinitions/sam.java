package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class sam {
    String emailAddress;
    int messagesTotal;
    int threadsTotal;
    int historyId;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getMessagesTotal() {
        return messagesTotal;
    }

    public void setMessagesTotal(int messagesTotal) {
        this.messagesTotal = messagesTotal;
    }

    public int getThreadsTotal() {
        return threadsTotal;
    }

    public void setThreadsTotal(int threadsTotal) {
        this.threadsTotal = threadsTotal;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) throws IOException {
        this.historyId = historyId;
    }

}
