package org.agoncal.training.javaee.model;

import org.agoncal.training.javaee.constraints.ChronologicalDates;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@DiscriminatorValue("B")
@NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b ORDER BY b.id DESC")
@EntityListeners(DebugListener.class)
@ChronologicalDates
@XmlRootElement
public class Book extends Item {

    // ======================================
    // =             Attributes             =
    // ======================================

    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;
    @Temporal(DATE)
    private Date earlyAccessDate;
    @Temporal(DATE)
    private Date publicationDate;

    @Convert(converter = LanguageConverter.class)
    private Language contentLanguage;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tags")
    private List<String> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Chapter> chapters = new ArrayList<>();

    // ======================================
    // =            Constructors            =
    // ======================================

    public Book() {
    }

    public Book(String title, Float price, String description, Integer nbOfPage, Boolean illustrations, Language contentLanguage) {
        super(title, price, description);
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
        this.contentLanguage = contentLanguage;
    }

    public Book(Long id, String title, Float price, String description, Integer nbOfPage, Boolean illustrations, Language contentLanguage) {
        super(id, title, price, description);
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
        this.contentLanguage = contentLanguage;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    public Date getEarlyAccessDate() {
        return earlyAccessDate;
    }

    public void setEarlyAccessDate(Date earlyAccessDate) {
        this.earlyAccessDate = earlyAccessDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Language getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(Language contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTagsAsString() {
        String s = "";
        for (String tag : tags) {
            s += tag + ", ";
        }
        if (s.length() > 2)
            return s.substring(0, s.length() - 2);
        else
            return s;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Book");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", nbOfPage=").append(nbOfPage);
        sb.append(", illustrations=").append(illustrations);
        sb.append(", contentLanguage=").append(contentLanguage);
        sb.append(", earlyAccessDate=").append(earlyAccessDate);
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", tags=").append(tags);
        sb.append(", chapters=").append(chapters);
        sb.append('}');
        return sb.toString();
    }
}