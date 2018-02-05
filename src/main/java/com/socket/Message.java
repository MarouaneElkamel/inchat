package com.socket;

import java.io.Serializable;

public class Message implements Serializable{
    
    private static final long serialVersionUID = 10275539472837495L;
    public String type, sender, content, recipient;
    
    private Model.Message msg;
    private Model.Conversation conv;
    private Model.Friendship friendship;
    private String friendshipModificationType;
    
    private String conversationID;
    
    public Message(String type, String sender, String content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient;
    }
    
    @Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }

    /**
     * @return the msg
     */
    public Model.Message getMsg()
    {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(Model.Message msg)
    {
        this.msg = msg;
    }

    /**
     * @return the conversationID
     */
    public String getConversationID()
    {
        return conversationID;
    }

    /**
     * @param conversationID the conversationID to set
     */
    public void setConversationID(String conversationID)
    {
        this.conversationID = conversationID;
    }

    /**
     * @return the friendship
     */
    public Model.Friendship getFriendship()
    {
        return friendship;
    }

    /**
     * @param friendship the friendship to set
     */
    public void setFriendship(Model.Friendship friendship)
    {
        this.friendship = friendship;
    }

    /**
     * @return the friendshipModificationType
     */
    public String getFriendshipModificationType()
    {
        return friendshipModificationType;
    }

    /**
     * @param friendshipModificationType the friendshipModificationType to set
     */
    public void setFriendshipModificationType(String friendshipModificationType)
    {
        this.friendshipModificationType = friendshipModificationType;
    }

    /**
     * @return the conv
     */
    public Model.Conversation getConv()
    {
        return conv;
    }

    /**
     * @param conv the conv to set
     */
    public void setConv(Model.Conversation conv)
    {
        this.conv = conv;
    }
}
