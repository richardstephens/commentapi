java_library(
    name = "commentapi-app-main",
    srcs = glob(["src/main/java/**/*.java"]),
    resource_jars = [
        "//services/commentapi-app/db:migration-jar",
    ],
    resources = [
    ],
    visibility = ["//visibility:public"],
    deps = [
        "lombok",
        "//services/commentapi-app/db:commentapi-jooq-classes",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_sparkjava_spark_core",
        "@maven//:com_zaxxer_HikariCP",
        "@maven//:org_flywaydb_flyway_core",
        "@maven//:org_jooq_jooq",
        "@maven//:org_jooq_jooq_meta",
        "@maven//:org_projectlombok_lombok",
        "@maven//:org_slf4j_slf4j_api",
    ],
)

java_binary(
    name = "commentapi-app",
    main_class = "dev.richst.commentapi.App",
    resource_jars = [
        "//services/commentapi-app/db:migration-jar",
    ],
    resources = [
    ],
    runtime_deps = [
        ":commentapi-app-main",
        "@maven//:org_flywaydb_flyway_core",
        "@maven//:org_mariadb_jdbc_mariadb_java_client",
        "@maven//:org_slf4j_slf4j_simple",
    ],
)

java_plugin(
    name = "lombok",
    generates_api = 1,
    processor_class = "lombok.launch.AnnotationProcessorHider$AnnotationProcessor",
    deps = [
        "@maven//:org_projectlombok_lombok",
    ],
)
