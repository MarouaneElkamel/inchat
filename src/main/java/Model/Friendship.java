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
@Table(name = "Friendship")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Friendship.findAll", query = "SELECT f FROM Friendship f")
    , @NamedQuery(name = "Friendship.findById", query = "SELECT f FROM Friendship f WHERE f.id = :id")
    , @NamedQuery(name = "Friendship.findByDate", query = "SELECT f FROM Friendship f WHERE f.date = :date")
    , @NamedQuery(name = "Friendship.findByStatus", query = "SELECT f FROM Friendship f WHERE f.status = :status")
})
public class Friendship implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "friend1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User friend1;
    @JoinColumn(name = "friend2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User friend2;

    public Friendship()
    {
    }

    public Friendship(Integer id)
    {
        this.id = id;
    }

    public Friendship(Integer id, Date date, String status)
    {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public User getFriend1()
    {
        return friend1;
    }

    public void setFriend1(User friend1)
    {
        this.friend1 = friend1;
    }

    public User getFriend2()
    {
        return friend2;
    }

    public void setFriend2(User friend2)
    {
        this.friend2 = friend2;
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
        if (!(object instanceof Friendship))
        {
            return false;
        }
        Friendship other = (Friendship) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Friendship[ id=" + id + " ]";
    }
    
}
