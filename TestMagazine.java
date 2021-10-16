package edu.odu.cs.cs350;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

// Comment added during version control lab
public class TestMagazine{


	@Test
	public void testConstructor() {
		Magazine mag = new Magazine();
		assertTrue(mag.getTitle().isEmpty());
		assertThat(mag.getPublicationDate(), is(LocalDate.now()));
		assertThat(mag.numArticles(), is(0));
	}

	@Test
	public void testCopyConstructor() {
		// Init Magazine with generic data to copy later
		LocalDate date = LocalDate.of(2001, 1, 1);
		Magazine mag = new Magazine("National Geographic", date);
		Article article = new Article("Dolphins", "John Doe");
		mag.addArticle(0, article);

		// Perform deep copy
		Magazine copy = (Magazine) mag.clone();

		// Cause a fail
		// Article editorial = new Article("Jackalopes", "Smith John");
		// copy.addArticle(1, editorial);

		// Assure that both objects are equal
		assertTrue(copy.equals(mag));
		assertTrue(copy.getTitle() == mag.getTitle());
		assertTrue(copy.getPublicationDate().isEqual(mag.getPublicationDate()));
	}

	@Test
	public void testEquals() {
		// Init first magazine
		LocalDate date1 = LocalDate.of(2001, 1, 1);
		Magazine mag1 = new Magazine("National Geographic", date1);
		Article article = new Article("Dolphins", "John Doe");
		mag1.addArticle(0, article);

		// Init second magazine
		LocalDate date2 = LocalDate.of(2001, 1, 1);
		Magazine mag2 = new Magazine("National Geographic", date2);
		Article editorial = new Article("Dolphins", "John Doe");
		mag2.addArticle(0, editorial);

		// Assure that both objects are equal
		assertTrue(mag2.equals(mag1));
		assertTrue(mag2.getTitle() == mag1.getTitle());
		assertTrue(mag2.getPublicationDate().isEqual(mag1.getPublicationDate()));
		assertTrue(mag2.numArticles() == mag1.numArticles());
		assertTrue(mag2.getArticle(0).equals(mag1.getArticle(0)));
	}

	/*
	@Test
	public void testToString() {
		// Init magazine
		LocalDate date1 = LocalDate.of(2001, 1, 1);
		Magazine mag1 = new Magazine("National Geographic", date1);
		Article article = new Article("Dolphins", "John Doe");
		Article editorial = new Article("Jackalopes", "Smith John");
		mag1.addArticle(0, article);
		mag1.addArticle(1, editorial);
		
		
		String expected = "Magazine [title=" + mag1.getTitle() 
						+ ", pubDate=" + mag1.getPublicationDate() 
						+ ", articles=[";
		
		// Iterate through articles, calling their toString functions
		for(int i = 0; i < mag1.numArticles(); i++) {
			if(i == mag1.numArticles() - 1)
				expected = expected + mag1.getArticle(i).toString() + "]";
			else
				expected = expected + mag1.getArticle(i).toString() + ", ";
		}
		expected = expected + "]";
		
		assertThat(mag1.toString(), is(expected));
	}
	*/
	
	@Test
	public void testToString() {
		// Init magazine
		LocalDate date1 = LocalDate.of(2001, 1, 1);
		Magazine mag1 = new Magazine("National Geographic", date1);
		Article article = new Article("Dolphins", "John Doe");
		Article editorial = new Article("Jackalopes", "Smith John");
		mag1.addArticle(0, article);
		mag1.addArticle(1, editorial);
		
		String expected = mag1.getTitle() + ", " + mag1.getPublicationDate() + "\n" + "Contents:\n";
		for(int i = 0; i < mag1.numArticles(); i++) {
			if(i == mag1.numArticles() - 1)
				expected = expected + "   " + i + "  " + mag1.getArticle(i).toString();
			else
				expected = expected + "   " + i + "  " + mag1.getArticle(i).toString() + "\n";
		}
		
		assertThat(mag1.toString(), is(expected));
	}

	@Test
	public void testHash() {
		// Init first magazine
		LocalDate date1 = LocalDate.of(2001, 1, 1);
		Magazine mag1 = new Magazine("National Geographic", date1);
		Article article = new Article("Dolphins", "John Doe");
		mag1.addArticle(0, article);

		// Init second magazine
		LocalDate date2 = LocalDate.of(2001, 1, 1);
		Magazine mag2 = new Magazine("National Geographic", date2);
		Article editorial = new Article("Dolphins", "John Doe");
		mag2.addArticle(0, editorial);
		
		// Hash both magazine objects
		int mag1Hash = mag1.hashCode();
		int mag2Hash = mag2.hashCode();
		
		assertTrue(mag1Hash == mag2Hash);
	}

	@Test
	public void testArticles() {
		// Init magazine
		LocalDate date = LocalDate.of(2001, 1, 1);
		Magazine mag = new Magazine("National Geographic", date);
		
		// Make some articles
		Article article1 = new Article("Dolphins", "John Doe");
		Article article2 = new Article("Jackalopes", "Smith John");
		Article article3 = new Article("Tapirs", "Jane Mill");
		Article article4 = new Article("Saigas", "Joshua Howell");
		Article article5 = new Article("Civets", "Howard Robinson");
		
		// Add articles
		mag.addArticle(0, article1);
		mag.addArticle(1, article2);
		mag.addArticle(2, article3);
		mag.addArticle(3, article4);
		mag.addArticle(4, article5);
		
		assertThat(mag.numArticles(), is(5));
		assertThat(mag.getArticle(1).getTitle(), is("Jackalopes"));
		assertThat(mag.getArticle(2).getGivenName(), is("Jane Mill"));
	}
	
}