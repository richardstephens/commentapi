package dev.richst.commentapi.localdev;

import dev.richst.commentapi.App;
import dev.richst.commentapi.config.CommentApiConfig;

import org.slf4j.Logger;
import org.testcontainers.containers.MariaDBContainer;

class LocalDev {
    private static Logger log = org.slf4j.LoggerFactory.getLogger(LocalDev.class);

    public static void main(String[] args) {
        log.info("Starting db in container");
        MariaDBContainer dbContainer = new MariaDBContainer<>();
        dbContainer.start();

        CommentApiConfig config =
                CommentApiConfig.builder()
                        .jdbcUrl(dbContainer.getJdbcUrl())
                        .jdbcUsername(dbContainer.getUsername())
                        .jdbcPassword(dbContainer.getPassword())
                        .port(3000)
                        .build();
        log.info(dbContainer.getJdbcUrl());

        try {
            App app = new App();
            app.start(config);
        } catch (Exception ex) {
            log.error("Exception on start", ex);
        }
    }
}
