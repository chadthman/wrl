package services;

import status.StatusCode;

import conf.AppConf;
import conf.TestDataConf;
import models.Bookmark;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

import javax.inject.Inject;

@ContextConfiguration(classes = {AppConf.class, TestDataConf.class})
public class BookmarkServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject private BookmarkService bookmarkService;

    /**
     * Listing of empty set of <@link Bookmark>s
     */
    @Test
    public void listEmptySetTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);
        Assert.assertEquals("Wrong size of the list, should be 0", 0, bookmarks.size());
        Assert.assertTrue("Bookmarks list should be empty", bookmarks.isEmpty());
    }

    /**
     * List a single item
     */
    @Test
    public void listSingleBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("The initial size of the list of bookmarks should be 0", 0, bookmarks.size());

        final Bookmark bookmark = new Bookmark("http://google.com", "big search engine", false);
        Assert.assertEquals("Bookmark adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark));
        bookmarks = bookmarkService.getAllBookmarks();

        Assert.assertEquals("Total bookmark count should be 1", 1, bookmarks.size());
        Assert.assertNotNull("Bookmark retrieved is null", bookmarks.get(0));
    }

    /**
     * List two bookmarks
     */
    @Test
    public void listTwobookmarksTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        Bookmark item = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark 1 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(item));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1", 1, bookmarks.size());

        item = new Bookmark("http://yahoo.com", "bookmark2 description",false);
        Assert.assertEquals("Bookmark 2 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(item));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 2", 2, bookmarks.size());

        Assert.assertNotEquals("bookmarks in the list are not unique, but should have been.", bookmarks.get(0), bookmarks.get(1));
    }

    /**
     * Single item addition
     */
    @Test
    public void addSingleBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        final Bookmark item = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(item));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1", 1, bookmarks.size());

        Assert.assertEquals("Retrieved item is not the same as original", item, bookmarks.get(0));
    }

    /**
     * Add same/exact duplicate item
     */
    @Test
    public void addExactDupBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        final Bookmark bookmark = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1", 1, bookmarks.size());

        // Add same exact/original/duplicate item
        Assert.assertEquals("Duplicate item protection fails", StatusCode.ADD_DUPLICATE_BOOKMARK, bookmarkService.addBookmark(bookmark));
        Assert.assertEquals("Total item count should still be 1 after adding duplicate item", 1, bookmarkService.getAllBookmarks().size());
        Assert.assertEquals("Retrieved item is not the same as original", bookmark, bookmarks.get(0));
    }

    /**
     * Add different item, but with duplicate Name
     */
    @Test
    public void addDiffBookmarkDupNameTest() {
        final String bookmarkUrl = "http://google.com";
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        final Bookmark bookmark = new Bookmark(bookmarkUrl, "bookmark1 description", false);
        Assert.assertEquals("Bookmark adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1", 1, bookmarks.size());

        // Add different item, but with duplicate Name
        final Bookmark diffbookmarksameName = new Bookmark(bookmarkUrl, "different description", false);
        bookmarkService.addBookmark(diffbookmarksameName);

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1 after addition of different item which has duplicate Name", 1, bookmarks.size());

        final Bookmark retrievedBookmark = bookmarks.get(0);

        Assert.assertNotEquals("Retrieved item should NOT be the same as duplicate item that was attempted to be inserted",
        		diffbookmarksameName, retrievedBookmark);
    }

    /**
     * Add null-item
     */
    @Test
    public void addNullBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);
        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        Assert.assertEquals("ADD_NULL_ITEM error should have been returned", StatusCode.ADD_NULL_BOOKMARK, bookmarkService.addBookmark(null));

        Assert.assertEquals("Total item count should be still 0", 0, bookmarkService.getAllBookmarks().size());
    }
    
    /**
     * Add two bookmarks
     */
    @Test
    public void addTwoDiffBookmarksTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        int initialCount = bookmarks.size();
        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, initialCount);

        final Bookmark bookmark1 = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark 1 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark1));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1", 1, bookmarks.size());

        // Add second item
        final Bookmark bookmark2 = new Bookmark("http://yahoo.com", "bookmark2 description",false);
        Assert.assertEquals("Bookmark 2 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark2));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 2", 2, bookmarks.size());

        Assert.assertEquals("Retrieved bookmark1 is not the same as original", bookmark1, bookmarks.get(0));
        Assert.assertEquals("Retrieved bookmark2 is not the same as original", bookmark2, bookmarks.get(1));
    }

    /**
     * Remove single item
     */
    @Test
    public void removeSingleBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        final Bookmark bookmark1 = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark 1 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark1));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("One item added, total item count should be 1 now", 1, bookmarks.size());

        Bookmark retrievedBookmark1 = bookmarks.get(0);
        Assert.assertEquals("Retrieved bookmark1 is not the same as original", bookmark1, retrievedBookmark1);

        Assert.assertEquals("Error removing an item", StatusCode.REMOVE_SUCCESS, bookmarkService.removeBookmarkById(retrievedBookmark1.getId()));
        Assert.assertEquals("Bookmark was removed, count should be back to 0", 0, bookmarkService.getAllBookmarks().size());
    }

    /**
     * Remove non-existing item
     */
    @Test
    public void removeNonExistingBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());
        Assert.assertEquals("Non-existing item deletion was attempted, count should be still 0", 0, bookmarkService.getAllBookmarks().size());
    }

    /**
     * Remove item with negative Id
     */
    @Test
    public void removeNegativeIdBookmarkTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());
        Assert.assertEquals("Negative and non-existing item deletion was attempted, count should be still 0", 0, bookmarkService.getAllBookmarks().size());
    }

    /**
     * Remove same item twice in the row
     */
    @Test
    public void removeSameBookmarkTwiceTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, bookmarks.size());

        final Bookmark bookmark1 = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark 1 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark1));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("One item added, total item count should be 1 now", 1, bookmarks.size());

        final Bookmark retrievedBookmark1 = bookmarks.get(0);
        Assert.assertEquals("Retrieved bookmark1 is not the same as original", bookmark1, retrievedBookmark1);

        Assert.assertEquals("Error removing an item", StatusCode.REMOVE_SUCCESS, bookmarkService.removeBookmarkById(retrievedBookmark1.getId()));
        Assert.assertEquals("Bookmark was removed, count should be back to 0", 0, bookmarkService.getAllBookmarks().size());

        // Removing the same exact item
        Assert.assertEquals("Bookmarks count should be still 0 after removing the only item twice in the row", 0, bookmarkService.getAllBookmarks().size());
    }

    /**
     * Remove two bookmarks
     */
    @Test
    public void removeTwoBookmarksTest() {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertNotNull("List of bookmarks is null", bookmarks);

        int initialCount = bookmarks.size();
        Assert.assertEquals("Wrong initial size of the list, should be 0", 0, initialCount);

        final Bookmark bookmark1 = new Bookmark("http://google.com", "bookmark1 description",false);
        Assert.assertEquals("Bookmark 1 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark1));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 1", 1, bookmarks.size());

        // Add second item
        final Bookmark bookmark2 = new Bookmark("http://yahoo.com", "bookmark2 description",false);
        Assert.assertEquals("Bookmark 2 adding failure", StatusCode.ADD_SUCCESS, bookmarkService.addBookmark(bookmark2));

        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("Total item count should be 2", 2, bookmarks.size());

        final Bookmark retrievedBookmark1 = bookmarks.get(0);
        final Bookmark retrievedBookmark2 = bookmarks.get(1);

        Assert.assertEquals("Retrieved bookmark1 is not the same as original", bookmark1, retrievedBookmark1);
        Assert.assertEquals("Retrieved bookmark2 is not the same as original", bookmark2, retrievedBookmark2);

        // Remove first item
        Assert.assertEquals("Error removing 1st item", StatusCode.REMOVE_SUCCESS, bookmarkService.removeBookmarkById(retrievedBookmark1.getId()));
        bookmarks = bookmarkService.getAllBookmarks();
        Assert.assertEquals("One of two bookmarks was removed, count should be 1", 1, bookmarks.size());

        // lastRemainingBookmark should be the second inserted item
        Assert.assertEquals("Retrieved bookmark2 is not the same as original", bookmark2, bookmarks.get(0));

        // Remove second item
        Assert.assertEquals("Error removing 2nd item", StatusCode.REMOVE_SUCCESS, bookmarkService.removeBookmarkById(retrievedBookmark2.getId()));
        Assert.assertEquals("Last item was removed from db, count should be 0", 0, bookmarkService.getAllBookmarks().size());
    }
}