package dev.richst.commentapi.config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentApiConfig {
    String jdbcUrl;
    String jdbcUsername;
    String jdbcPassword;

    int port;
}
