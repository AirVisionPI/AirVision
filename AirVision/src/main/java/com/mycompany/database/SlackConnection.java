/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.io.IOException;

/**
 *
 * @author JoaoS
 */
public class SlackConnection {
    
    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-3578969611223-3593580541779-ji5TRHj6QRmwdCS1rKpyxXPD";
    private static String channelPost = "automationdemochannel";
    
  public static void sendMessageToSlack(String message) throws IOException{
      
      try{
          
           StringBuilder msgbuilder = new StringBuilder();
      msgbuilder.append(message);
      
      Payload payload = Payload.builder().channel(channelPost).text(msgbuilder.toString()).build();
      
      WebhookResponse res = Slack.getInstance().send(webHookUrl, payload);
          
      }catch(Exception e){
          e.printStackTrace();
      }
          
      
     
      
  }
    
}
