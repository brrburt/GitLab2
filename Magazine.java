package edu.odu.cs.cs350;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * A magazine containing a collection of articles, organized by (starting) page number.
 * 
 * 
 * @author zeil
 *
 */
public class Magazine implements Cloneable {

  private String title;
  private LocalDate pubDate;
  private ArrayList<Article> articles;

  /**
   * Create a "blank" magazine with empty strings for title, the current
   * date for the publication date,
   * and an empty (zero-length) list of articles.
   */
  public Magazine() {
	  title = "";
	  pubDate = LocalDate.now();
	  articles = new ArrayList<Article>();
	  
  }

  /**
   * Create a new magazine.
   * @param title title of the magazine
   * @param publDate publication date of the magazine.
   */
  public Magazine(String title, LocalDate pubDate) {
	  this.title = title;
	  this.pubDate = pubDate;
	  articles = new ArrayList<Article>();
  }

  
  
  /**
   * Get the title of this magazine.
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the title of this magazine.
   * @param title the title to set
   */
  public void setTitle(String title) {
	  this.title = title;
  }


  /**
   * Get the publication date of this magazine.
   * @return the date
   */
  public LocalDate getPublicationDate() {
    return pubDate;
  }

  /**
   * Set the publication date of this magazine.
   * @param pubDate the publication date to set
   */
  public void setPublicationDate(LocalDate pubDate) {
	  this.pubDate = pubDate;
  }

  
  /**
   * How many articles does this magazine have?
   * @return number of articles
   */
  public int numArticles() {
    return articles.size();
  }
  
  
  /**
   * Add an article to the magazine at an indicated starting page.
   * 
   * If an article is already at that page, replaces the existing one.
   * 
   * @param startingPage first page of the article
   * @param article author to be added
   */
  public void addArticle(int startingPage, Article article) {
	  articles.add(startingPage, article);
  }
  
  /**
   * Get the article previously placed at a given starting page.
   * 
   * @param startingAtPage a page number in the magazine
   * @return the article starting at that page, or null if no article
   *     has been put there.
   */
  public Article getArticle (int startingAtPage) {
      return articles.get(startingAtPage);
  }
  
  /**
   * Render the magazine as a string in a format guaranteed to
   * contain all fields.
   */
  public String toString() {
	//return "Magazine [title=" + title + ", pubDate=" + pubDate + ", articles=" + articles + "]";
	String result = title + ", " + pubDate + "\n" + "Contents:\n";
	for(int i = 0; i < this.numArticles(); i++) {
		if(i == this.numArticles() - 1)
			result = result + "   " + i + "  " + this.getArticle(i).toString();
		else
			result = result + "   " + i + "  " + this.getArticle(i).toString() + "\n";
	}
	
	return result;
  }
  
  public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Magazine other = (Magazine) obj;
	return Objects.equals(articles, other.articles) && Objects.equals(pubDate, other.pubDate)
			&& Objects.equals(title, other.title);
  }

  public int hashCode() {
	return Objects.hash(articles, pubDate, title);
  }

  /**
   * Return a (deep) copy of this object.
   */
  @Override
  public Object clone()  {
	Magazine copy = new Magazine(this.title, this.pubDate);
	for(int i = 0; i < this.numArticles(); i++) {
		copy.addArticle(i, this.getArticle(i));
	}
    return copy;
  }

  /**
   * Provide access to the table of contents of
   * this magazine. e.g.,
   *     Magazine magazine = new Magazine(...);
   *        ...
   *     for (Integer page: magazine,startingPages()) {
   *        Article art = magazine.getArticle(page);
   *        doSomethingWith (page, art);
   *     }
   * 
   * @return iterator over the starting page numbers. Numbers
   *        are returned in ascending order.
   */
  public Set<Integer> startingPages() {
	Set<Integer> intSet = new HashSet<Integer>();
	for(Article i: articles) {
		intSet.add(getArticle(i));
	}
    return intSet;
  }


}
// This is from master