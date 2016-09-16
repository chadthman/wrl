package services;
import models.Bookmark;
import java.util.List;
import status.StatusCode;;

public interface BookmarkService {
	/**
	 * Get 
	 * @return
	 */
    List<Bookmark> getAllBookmarks();
    /**
     * 
     * @param bookmark
     * @return
     */
    StatusCode addBookmark(Bookmark bookmark);
    /**
     * 
     * @param id
     * @return
     */
    StatusCode removeBookmarkById(Long id);
    /**
     * 
     * @param id
     * @return
     */
    StatusCode toggleBookmarkCompleteById(Long id);
}