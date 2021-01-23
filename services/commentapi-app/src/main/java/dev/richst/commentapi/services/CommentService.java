package dev.richst.commentapi.services;

import dev.richst.commentapi.db.test.tables.Comments;
import dev.richst.commentapi.db.test.tables.Pages;
import dev.richst.commentapi.db.test.tables.Users;
import dev.richst.commentapi.db.test.tables.records.CommentsRecord;
import dev.richst.commentapi.db.test.tables.records.PagesRecord;
import dev.richst.commentapi.db.test.tables.records.UsersRecord;
import dev.richst.commentapi.restmodel.Comment;

import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    private DSLContext dsl;

    public CommentService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<Comment> commentsForPage(String url) {
        return dsl
                .selectFrom(Comments.COMMENTS)
                .where(
                        Comments.COMMENTS.PAGE_ID.eq(
                                dsl.select(Pages.PAGES.ID)
                                        .from(Pages.PAGES)
                                        .where(Pages.PAGES.URL.eq(url))))
                .fetch()
                .stream()
                .map(c -> new Comment(c.getComment()))
                .collect(Collectors.toList());
    }

    public void newComment(String url, String text) {
        UsersRecord user = dsl.newRecord(Users.USERS);
        user.setEmail("a@b.c");
        user.setCreated(LocalDateTime.now());
        user.store();

        PagesRecord page = dsl.newRecord(Pages.PAGES);
        page.setUrl(url);
        page.store();

        CommentsRecord comment = dsl.newRecord(Comments.COMMENTS);
        comment.setPageId(page.getId());
        comment.setUserId(user.getId());
        comment.setApproved((byte) 0);
        comment.setComment(text);
        comment.setCreated(LocalDateTime.now());
        comment.store();
    }
}
