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

import status.StatusCode;

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
    public StatusCode addBookmark(Bookmark bookmark) {
    	log.debug(">Adding Bookmark");
   
        Long count = (Long)em.createQuery("SELECT COUNT(i) FROM Bookmark i WHERE i.url = :url")
        				.setParameter("url", bookmark.getUrl())
                        .getSingleResult();

        if (count > 0) {
        	return StatusCode.ADD_DUPLICATE_BOOKMARK;
        }

        em.persist(bookmark);
        em.flush();
        return StatusCode.ADD_SUCCESS;
    }

    @Override
    @Transactional
    public StatusCode removeBookmarkById(Long id) {
        log.debug(">Removing Bookmark (%s)", id);
        Bookmark bookmark = em.find(Bookmark.class, id);
        
        if (bookmark == null) {
            return StatusCode.REMOVE_NULL_BOOKMARK;
        } 
        
        em.remove(bookmark);
        return StatusCode.REMOVE_SUCCESS;
    }
    
    @Override
    @Transactional
    public StatusCode toggleBookmarkCompleteById(Long id) {
    	 log.debug(">Toggling Bookmark Completed (%s)", id);
    	 Bookmark bookmark = em.find(Bookmark.class, id);
    	 
    	 if (bookmark == null) {
    		 return StatusCode.TOGGLE_NULL_BOOKMARK;
         }
    	 
    	 Boolean completed = !bookmark.getCompleted();
         bookmark.setCompleted(completed);
         em.persist(bookmark);
         em.flush();
         
         return StatusCode.TOGGLE_SUCCESS;
    }
}