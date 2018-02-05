/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elkamel
 */
@Entity
@Table(name = "avatar")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Avatar.findAll", query = "SELECT a FROM Avatar a")
    , @NamedQuery(name = "Avatar.findByAvatarid", query = "SELECT a FROM Avatar a WHERE a.avatarid = :avatarid")
    , @NamedQuery(name = "Avatar.findByImageName", query = "SELECT a FROM Avatar a WHERE a.imageName = :imageName")
})
public class Avatar implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AVATARID")
    private Integer avatarid;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;
    @Size(max = 150)
    @Column(name = "ImageName")
    private String imageName;

    public Avatar()
    {
    }

    public Avatar(Integer avatarid)
    {
        this.avatarid = avatarid;
    }

    public Avatar(Integer avatarid, byte[] image)
    {
        this.avatarid = avatarid;
        this.image = image;
    }

    public Integer getAvatarid()
    {
        return avatarid;
    }

    public void setAvatarid(Integer avatarid)
    {
        this.avatarid = avatarid;
    }

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (avatarid != null ? avatarid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avatar))
        {
            return false;
        }
        Avatar other = (Avatar) object;
        if ((this.avatarid == null && other.avatarid != null) || (this.avatarid != null && !this.avatarid.equals(other.avatarid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Avatar[ avatarid=" + avatarid + " ]";
    }
    
}
