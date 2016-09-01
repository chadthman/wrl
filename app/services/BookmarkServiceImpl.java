package services;

import models.Bookmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

@Named
public class BookmarkServiceImpl implements BookmarkService {

    private static final Logger log = LoggerFactory.getLogger(BookmarkService.class);
    
    @PersistenceContext private EntityManager em;

    @Override
    public List<Bookmark> getAllBookmarks() {
        log.debug(">Getting All Bookmarks");
        List<Bookmark> bookmarks = em.createQuery("SELECT c FROM Bookmark c ORDER BY c.created DESC", Bookmark.class).getResultList();
        return bookmarks;
    }

    @Override
    @Transactional
    public void addBookmark(Bookmark bookmark) {
    	log.debug(">Adding Bookmark");
   
        Long count = (Long)em.createQuery("SELECT COUNT(i) FROM Bookmark i WHERE i.url = :url")
        				.setParameter("url", bookmark.getUrl())
                        .getSingleResult();

        if (count > 0) {
        	log.error(">>Adding Bookmark : Failed Due To Duplicated Entry");
        	return;
        }

        em.persist(bookmark);
        em.flush();
    }

    @Override
    @Transactional
    public void removeBookmarkById(Long id) {
        log.debug(">Removing Bookmark (%s)", id);
        Bookmark bookmark = em.find(Bookmark.class, id);
        if (bookmark == null) {
            log.warn(">>Removing Bookmark : Failed Due To No Existing Entry (%s)", id);
            return;
        } else {
            em.remove(bookmark);
            log.debug(">>Removing Bookmark: Successfully Removed (%s)", id);
        }
    }
    
    @Override
    @Transactional
    public void toggleBookmarkCompleteById(Long id) {
    	 log.debug(">Toggling Bookmark Completed (%s)", id);
    	 Bookmark bookmark = em.find(Bookmark.class, id);
    	 if (bookmark == null) {
             log.warn(">>Toggling Bookmark Completed : Failed Due To No Existing Entry (%s)", id);
             return;
         } else {
             Boolean completed = !bookmark.getCompleted();
             bookmark.setCompleted(completed);
             
             em.persist(bookmark);
             em.flush();
             log.debug(">>Toggling Bookmark Completed: Successfully Toggled (%s)", id);
         }
    	
    }
}