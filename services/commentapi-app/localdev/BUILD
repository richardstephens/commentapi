java_binary(
    name = "localdev",
    srcs = glob(["*.java"]),
    main_class = "dev.richst.commentapi.localdev.LocalDev",
    resource_jars = [
        "//services/commentapi-app/db:migration-jar",
    ],
    resources = [
    ],
    runtime_deps = [
        "@maven//:org_mariadb_jdbc_mariadb_java_client",
        "@maven//:org_slf4j_slf4j_simple",
    ],
    deps = [
        "//services/commentapi-app:commentapi-app-main",
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_testcontainers_mariadb",
        "@maven//:org_testcontainers_testcontainers",
    ],
)
