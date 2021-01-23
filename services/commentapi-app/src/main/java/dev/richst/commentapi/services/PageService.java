package dev.richst.commentapi.services;

import dev.richst.commentapi.db.test.tables.Pages;
import dev.richst.commentapi.restmodel.Page;

import org.jooq.DSLContext;

import java.util.List;
import java.util.stream.Collectors;

public class PageService {

    private DSLContext dsl;

    public PageService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<Page> getPages() {
        return this.dsl.selectFrom(Pages.PAGES).fetch().stream()
                .map(p -> new Page(p.getId(), p.getUrl()))
                .collect(Collectors.toList());
    }
}
