package models;

import org.junit.Assert;
import org.junit.Test;

public class BookmarkTest {

    /**
     * Test equals with exact same item
     */
    @Test
    public void equalsSameExactBookmarkTest() {
        final Bookmark aBookmark = new Bookmark("http://google.com", "biggest search engine in the world", false);
        Assert.assertEquals("Bookmarks are not the same", aBookmark, aBookmark);
    }

    /**
     * Test equals with equivalent items
     */
    @Test
    public void equalsEquivalentBookmarksTest() {
        final String bookmarkUrl = "http://google.com";
        final String bookmarkDesc = "biggest search engine in the world";

        final Bookmark bookmark1 = new Bookmark(bookmarkUrl, bookmarkDesc, false);
        final Bookmark bookmark2 = new Bookmark(bookmarkUrl, bookmarkDesc, false);

        Assert.assertEquals("Bookmarks should be the same", bookmark1, bookmark2);
    }

    /**
     * Test equals with different items
     */
    @Test
    public void equalsDiffBookmarksTest() {
        final Bookmark bookmark1 = new Bookmark("http://google.com", "bookmark1 desc", false);
        final Bookmark bookmark2 = new Bookmark("http://yahoo.com", "bookmark2 desc", false);

        Assert.assertNotEquals("Bookmarks are evaluated to be the same, while they are not", bookmark1, bookmark2);
    }

    /**
     * Test equals with null description
     */
    @Test
    public void equalsNullDescTest() {
        final String bookmarkUrl = "http://google.com";

        final Bookmark bookmark1 = new Bookmark(bookmarkUrl, null, false);
        final Bookmark bookmark2 = new Bookmark(bookmarkUrl, null, false);

        Assert.assertEquals("Items are not the same", bookmark1, bookmark2);
    }

    /**
     * Same Name, but different Description
     * Items should be different
     */
    @Test
    public void equalsSameNameDiffDescTest() {
        final String bookmarkUrl = "bookmark";

        final Bookmark bookmark1 = new Bookmark(bookmarkUrl, "diff description 1", false);
        final Bookmark bookmark2 = new Bookmark(bookmarkUrl, "diff description 2", false);

        Assert.assertNotEquals("Items should not be the same", bookmark1, bookmark2);
    }
}