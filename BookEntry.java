import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {

//  *** Data ***

    private final String title;       // Name of the book
    private final String[] authors;   // Array of authors
    private final float rating;       // Rating of the book
    private final String ISBN;        // ISBN of the book
    private final int pages;          // Number of pages in the book



//  *** Methods ***

    /**
     * Constructor for the BookEntry Class
     *
     * @param name - String containing the name of the book
     * @param authors - Array of Strings containing all the authors of the book
     * @param rating - float containing the rating between 1 and 5 of the book
     * @param ISBN - String containing the ISBN number of the book
     * @param pages - Integer containing the number of pages in the book
     */
    public BookEntry(String name, String[] authors, float rating, String ISBN, int pages){

        // Exception handling - NullPointerExceptions
        if (name.equals(null)){throw new NullPointerException("Name argument is null");}
        if (authors.equals(null)){throw new NullPointerException("Authors array is null");}
        if (rating == 0.0f){throw new NullPointerException("Rating is empty");}
        if (ISBN.equals(null)){throw new NullPointerException("ISBN is null");}
        if (pages == 0){throw new NullPointerException("Pages is empty");}

        // Exception handling - IllegalArgumentExceptions
        if (pages < 0){throw new IllegalArgumentException("Pages out of range");}
        if (rating < 0 || rating > 5){throw new IllegalArgumentException("Rating out of range");}

        this.title = name;
        this.authors = authors;
        this.rating = rating;
        this.ISBN = ISBN;
        this.pages = pages;

    }

    public String getTitle(){
        return this.title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public float getRating() {
        return rating;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPages() { return pages; }


    @Override
    public String toString(){

        String output = "";

        output = output + title + "\n";
        output = output + "by ";
        for (int i = 0; i < authors.length-1; i++){
            output = output + authors[i] + ", ";
        }
        output = output + authors[authors.length-1]+ "\n";
        output = output + "Rating: " + String.format("%.3g%n", rating);
        output = output + "ISBN: " + ISBN + "\n";
        output = output + pages + " pages";

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntry bookEntry = (BookEntry) o;
        return Float.compare(bookEntry.rating, rating) == 0 &&
                pages == bookEntry.pages &&
                title.equals(bookEntry.title) &&
                Arrays.equals(authors, bookEntry.authors) &&
                ISBN.equals(bookEntry.ISBN);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }


}
