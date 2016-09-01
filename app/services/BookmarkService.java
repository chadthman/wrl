package services;
import models.Bookmark;
import java.util.List;
import status.StatusCode;;

public interface BookmarkService {
    List<Bookmark> getAllBookmarks();
    StatusCode addBookmark(Bookmark bookmark);
    StatusCode removeBookmarkById(Long id);
    StatusCode toggleBookmarkCompleteById(Long id);
}