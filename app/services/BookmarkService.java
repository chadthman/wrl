package services;
import models.Bookmark;
import java.util.List;
import status.StatusCode;;

public interface BookmarkService {
    List<Bookmark> getAllBookmarks();
    StatusCode addBookmark(Bookmark bookmark);
    void removeBookmarkById(Long id);
    void toggleBookmarkCompleteById(Long id);
}