load("@rules_jooq_flyway_codegen//rules_jooq_flyway_codegen:jooqflyway.bzl", "jooqflyway")

java_library(
    name = "migration-jar",
    resource_strip_prefix = "services/commentapi-app/db",
    resources = glob(["db/migration/*.sql"]),
    visibility = ["//services/commentapi-app:__subpackages__"],
)

jooqflyway(
    name = "commentapi-jooq-classes",
    codegen_xml = "codegen.xml",
    db_type = "mariadb",
    migration_jar = ":migration-jar",
    visibility = ["//services/commentapi-app:__subpackages__"],
)
