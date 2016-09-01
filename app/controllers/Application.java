package controllers;
import services.BookmarkService;

import org.springframework.stereotype.Component;

import play.*;
import play.mvc.*;
import play.data.Form;
import play.libs.Json;

import views.html.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Bookmark;
import models.BookmarkForm;

import javax.inject.Inject;

@Component
public class Application extends Controller {
	
	@Inject private BookmarkService bookmarkService;
	
    private static final Logger log = LoggerFactory.getLogger(Application.class);
	
 
    public Result index() {
    	log.debug(">index()");
    	return ok(index.render(Form.form(models.BookmarkForm.class), bookmarkService.getAllBookmarks()));
    }

}
