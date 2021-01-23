package dev.richst.commentapi;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import dev.richst.commentapi.config.CommentApiConfig;
import dev.richst.commentapi.restmodel.Comment;
import dev.richst.commentapi.services.CommentService;
import dev.richst.commentapi.services.PageService;

import lombok.extern.slf4j.Slf4j;

import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import spark.Spark;

import java.util.List;
import java.util.Optional;

@Slf4j
public class App {

    private void registerUrls(DSLContext dsl, CommentApiConfig config) {
        log.info("registering urls");

        CommentService commentService = new CommentService(dsl);
        PageService pageService = new PageService(dsl);
        Spark.port(config.getPort());

        Spark.get(
                "/commentapi/pages",
                (req, res) -> {
                    return new Gson().toJson(pageService.getPages());
                });
        Spark.get(
                "/commentapi/comments",
                (req, res) -> {
                    List<Comment> comments = commentService.commentsForPage("a");
                    return new Gson().toJson(comments);
                });

        Spark.post(
                "/new",
                (req, res) -> {
                    commentService.newComment("a", "cccc");
                    return "done";
                });
    }

    public void start(CommentApiConfig config) throws Exception {

        Flyway flyway =
                Flyway.configure()
                        .dataSource(
                                config.getJdbcUrl(),
                                config.getJdbcUsername(),
                                config.getJdbcPassword())
                        .load();
        flyway.migrate();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getJdbcUrl());
        hikariConfig.setUsername(config.getJdbcUsername());
        hikariConfig.setPassword(config.getJdbcPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Settings settings = new Settings();
        settings.withRenderSchema(false);
        DSLContext dsl = DSL.using(ds, SQLDialect.MARIADB, settings);

        registerUrls(dsl, config);
    }

    public static void main(String[] argv) {
        try {
            log.info("Reading config from environment");
            CommentApiConfig config =
                    CommentApiConfig.builder()
                            .jdbcUrl(System.getenv("JDBC_DATABASE_URL"))
                            .jdbcUsername(System.getenv("JDBC_DATABASE_USERNAME"))
                            .jdbcPassword(System.getenv("JDBC_DATABASE_PASSWORD"))
                            .port(
                                    Integer.parseInt(
                                            Optional.ofNullable(System.getenv("PORT"))
                                                    .orElse("3000")))
                            .build();
            new App().start(config);
        } catch (Exception e) {
            log.error("Exception on start", e);
        }
    }
}
