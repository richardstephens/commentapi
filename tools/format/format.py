
import sys
import os
import subprocess

formatter = os.path.abspath(sys.argv[1])


os.chdir(os.getenv('BUILD_WORKSPACE_DIRECTORY'))

formatter_args = [
    "java",
    "-jar",
    formatter,
    "--aosp",
    "--replace",
]
java_files = []
for root, dirs, files in os.walk("."):
    path = root.split(os.sep)
    for file in files:
        if file.endswith(".java"):
            formatter_args.append(os.path.join(root,file))


subprocess.run(formatter_args, capture_output=False, check=True)

subprocess.run("buildifier $(find . -type f \( -iname BUILD -or -iname BUILD.bazel \))", shell=True)