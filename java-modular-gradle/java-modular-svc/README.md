# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p cli/build/libs/:api/build/libs/:facade_ab/build/libs/:svc_a/build/libs/:svc_b/build/libs/:$JAVA_HOME/jmods --add-modules cli,facade_ab --launcher app=cli --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules