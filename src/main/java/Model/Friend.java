/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Comparator;

/**
 *
 * @author Elkamel
 */
public class Friend
{
    private User friend;
    private String friendshipStatue;
    private int friendshipId;

    /**
     * @return the friend
     */
   public  Friend(User u,String s,int i)
    {
        this.setFriend(u);
        this.setFriendshipStatue(s);
        this.setFriendshipId(i);
        
    }
    public User getFriend()
    {
        return friend;
    }

    /**
     * @param friend the friend to set
     */
    public void setFriend(User friend)
    {
        this.friend = friend;
    }

    /**
     * @return the friendshipStatue
     */
    public String getFriendshipStatue()
    {
        return friendshipStatue;
    }

    /**
     * @param friendshipStatue the friendshipStatue to set
     */
    public void setFriendshipStatue(String friendshipStatue)
    {
        this.friendshipStatue = friendshipStatue;
    }

    /**
     * @return the friendshipId
     */
    public int getFriendshipId()
    {
        return friendshipId;
    }

    /**
     * @param friendshipId the friendshipId to set
     */
    public void setFriendshipId(int friendshipId)
    {
        this.friendshipId = friendshipId;
    }
    public static Comparator<Friend> getCompByName()
{   
    Comparator comp = new Comparator<Friend>(){
     @Override
     public int compare(Friend f1, Friend f2)
     {
         return f1.friend.getUsername().compareTo(f2.friend.getUsername());
     }        
 };
 return comp;
}  
    
}
