package controller;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ServerEndpoint("/echo")
public class ReviewServer{
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("comment from " + session.getId() + ": " + message);
        String send = message.substring(0,message.lastIndexOf(","));
        String review = message.substring(message.lastIndexOf(",") + 1);
        sendMessageToAll(send,review);
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("User " + session.getId() + " has closed the connection");
        sessions.remove(session);
    }

    private void sendMessageToAll(String message, String review){
        for(Session s : sessions){
            try{
                s.getBasicRemote().sendText(review);
                s.getBasicRemote().sendText(message);
            }catch (IOException exc){
                exc.printStackTrace();
            }
        }
    }


}