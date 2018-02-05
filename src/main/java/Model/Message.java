/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elkamel
 */
@Entity
@Table(name = "Message")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id")
    , @NamedQuery(name = "Message.findByDateSent", query = "SELECT m FROM Message m WHERE m.dateSent = :dateSent")
    , @NamedQuery(name = "Message.findByDateRead", query = "SELECT m FROM Message m WHERE m.dateRead = :dateRead")
})
public class Message implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "Text")
    private String text;
    @Basic(optional = false)
    @Column(name = "dateSent")
    @Temporal(TemporalType.DATE)
    private Date dateSent;
    @Column(name = "dateRead")
    @Temporal(TemporalType.DATE)
    private Date dateRead;
    @JoinColumn(name = "Conversation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conversation conversation;
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User receiver;
    @JoinColumn(name = "sender", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User sender;

    public Message()
    {
    }

    public Message(Integer id)
    {
        this.id = id;
    }

    public Message(Integer id, String text, Date dateSent)
    {
        this.id = id;
        this.text = text;
        this.dateSent = dateSent;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        text.trim();
        this.text = text;
    }

    public Date getDateSent()
    {
        return dateSent;
    }

    public void setDateSent(Date dateSent)
    {
        this.dateSent = dateSent;
    }

    public Date getDateRead()
    {
        return dateRead;
    }

    public void setDateRead(Date dateRead)
    {
        this.dateRead = dateRead;
    }

    public Conversation getConversation()
    {
        return conversation;
    }

    public void setConversation(Conversation conversation)
    {
        this.conversation = conversation;
    }

    public User getReceiver()
    {
        return receiver;
    }

    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }

    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
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
        if (!(object instanceof Message))
        {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Message[ id=" + id + " ]";
    }
    
}
