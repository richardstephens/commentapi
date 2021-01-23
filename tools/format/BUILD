java_binary(
    name = "java_format",
    main_class = "com.google.googlejavaformat.java.Main",
    runtime_deps = ["@maven//:com_google_googlejavaformat_google_java_format"],
)

py_binary(
    name = "format",
    srcs = ["format.py"],
    args = ["$(location :java_format_deploy.jar) "],
    data = [":java_format_deploy.jar"],
)
