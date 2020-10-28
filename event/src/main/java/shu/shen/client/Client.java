package shu.shen.client;

import org.springframework.beans.factory.annotation.Autowired;
import shu.shen.listener.CustomSpringEventListener;
import shu.shen.publisher.CustomSpringEventPublisher;

public class Client {

    private CustomSpringEventPublisher publisher;

    private CustomSpringEventListener listener;

    public void example(){
        publisher = new CustomSpringEventPublisher();
        listener = new CustomSpringEventListener();
//        publisher.publishCustomEvent("biubiubiu...");
    }
    public static void main(String[] args) {
        Client client = new Client();
        client.example();
    }

}
