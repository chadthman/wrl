package services;
import models.Bookmark;
import java.util.List;

public interface BookmarkService {
    List<Bookmark> getAllBookmarks();
    void addBookmark(Bookmark bookmark);
    void removeBookmarkById(Long id);
    void toggleBookmarkCompleteById(Long id);
}