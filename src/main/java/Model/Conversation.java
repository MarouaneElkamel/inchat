/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elkamel
 */
@Entity
@Table(name = "Conversation")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Conversation.findAll", query = "SELECT c FROM Conversation c")
    , @NamedQuery(name = "Conversation.findById", query = "SELECT c FROM Conversation c WHERE c.id = :id")
    , @NamedQuery(name = "Conversation.findByName", query = "SELECT c FROM Conversation c WHERE c.name = :name")
    , @NamedQuery(name = "Conversation.findByEmoji", query = "SELECT c FROM Conversation c WHERE c.emoji = :emoji")
    , @NamedQuery(name = "Conversation.findByColor", query = "SELECT c FROM Conversation c WHERE c.color = :color")
    , @NamedQuery(name = "Conversation.findByNickNames", query = "SELECT c FROM Conversation c WHERE c.nickNames = :nickNames")
})
public class Conversation implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "emoji")
    private String emoji;
    @Column(name = "color")
    private String color;
    @Column(name = "NickNames")
    private String nickNames;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversation")
    private Collection<Message> messageCollection;
    @JoinColumn(name = "Person1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User person1;
    @JoinColumn(name = "Person2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User person2;

    public Conversation()
    {
    }

    public Conversation(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmoji()
    {
        return emoji;
    }

    public void setEmoji(String emoji)
    {
        this.emoji = emoji;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getNickNames()
    {
        return nickNames;
    }

    public void setNickNames(String nickNames)
    {
        this.nickNames = nickNames;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection()
    {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection)
    {
        this.messageCollection = messageCollection;
    }

    public User getPerson1()
    {
        return person1;
    }

    public void setPerson1(User person1)
    {
        this.person1 = person1;
    }

    public User getPerson2()
    {
        return person2;
    }

    public void setPerson2(User person2)
    {
        this.person2 = person2;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversation))
        {
            return false;
        }
        Conversation other = (Conversation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Conversation[ id=" + id + " ]";
    }
    
}
