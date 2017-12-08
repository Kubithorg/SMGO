package org.kubithon.smgo.common.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.kubithon.smgo.common.Smgo;

public class ShowLoadingException extends Exception {

    private List<String> messages = new ArrayList<>();

    public ShowLoadingException(String message) {
        super();
        this.addMessage(message);
    }

    public void logMessages() {
        for (int i = this.messages.size() - 1; i >= 0; i--)
            Smgo.logger.error(this.messages.get(i));
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

}
