/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elkamel
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByPhoto", query = "SELECT u FROM User u WHERE u.photo = :photo")
    , @NamedQuery(name = "User.findByLastLoginDate", query = "SELECT u FROM User u WHERE u.lastLoginDate = :lastLoginDate")
    , @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender")
    , @NamedQuery(name = "User.findByTelephone", query = "SELECT u FROM User u WHERE u.telephone = :telephone")
})
public class User implements Serializable
{

    @JoinColumn(name = "avatarid", referencedColumnName = "AVATARID")
    @OneToOne
    private Avatar avatarid;

    

    @Size(max = 10)
    @Column(name = "connectionStatu")
    private String connectionStatu;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "photo")
    private String photo;
    @Basic(optional = false)
    @Column(name = "lastLoginDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Column(name = "telephone")
    private String telephone;
    @Lob
    @Column(name = "bio")
    private String bio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friend1")
    private Collection<Friendship> friendshipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friend2")
    private Collection<Friendship> friendshipCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person1")
    private Collection<Conversation> conversationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person2")
    private Collection<Conversation> conversationCollection1;

    public User()
    {
    }

    public User(Integer id)
    {
        this.id = id;
    }

    public User(Integer id, String username, String firstName, String lastName, LocalDate birthDate, String email, String password, Date lastLoginDate, String gender)
    {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        
        this.birthDate = asDate(birthDate);
        this.email = email;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.gender = gender;
    }

 
       public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate()
    {
        return asLocalDate(birthDate) ;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = asDate(birthDate);
    }
public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date asDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate asLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime asLocalDateTime(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    @XmlTransient
    public Collection<Friendship> getFriendshipCollection()
    {
        return friendshipCollection;
    }

    public void setFriendshipCollection(Collection<Friendship> friendshipCollection)
    {
        this.friendshipCollection = friendshipCollection;
    }

    @XmlTransient
    public Collection<Friendship> getFriendshipCollection1()
    {
        return friendshipCollection1;
    }

    public void setFriendshipCollection1(Collection<Friendship> friendshipCollection1)
    {
        this.friendshipCollection1 = friendshipCollection1;
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

    @XmlTransient
    public Collection<Message> getMessageCollection1()
    {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1)
    {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<Conversation> getConversationCollection()
    {
        return conversationCollection;
    }

    public void setConversationCollection(Collection<Conversation> conversationCollection)
    {
        this.conversationCollection = conversationCollection;
    }

    @XmlTransient
    public Collection<Conversation> getConversationCollection1()
    {
        return conversationCollection1;
    }

    public void setConversationCollection1(Collection<Conversation> conversationCollection1)
    {
        this.conversationCollection1 = conversationCollection1;
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
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.User[ id=" + id + " ]";
    }

    public String getConnectionStatu()
    {
        return connectionStatu;
    }

    public void setConnectionStatu(String connectionStatu)
    {
        this.connectionStatu = connectionStatu;
    }

   

    public Avatar getAvatarid()
    {
        return avatarid;
    }

    public void setAvatarid(Avatar avatarid)
    {
        this.avatarid = avatarid;
    }
    
}
