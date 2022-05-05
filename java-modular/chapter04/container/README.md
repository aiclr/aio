## 容器体系结构

```shell
rm -rf out/
# Compile container and platform API first
javac -encoding UTF-8 -verbose -d out/container --module-source-path src -m cont_api,cont_cli
javac -encoding UTF-8 -verbose -d out --module-source-path src -m cont_svca
javac -encoding UTF-8 -verbose -d out --module-source-path src -m cont_svcb

mkdir -p out/mods out/jara out/jarb
jar -cvfe out/mods/App.jar org.bougainvilleas.container.cli.Launcher -C out/container/cont_cli .
jar -cvf out/mods/api.jar -C out/container/cont_api .
jar -cvf out/jara/a.jar -C out/cont_svca .
jar -cvf out/jarb/b.jar -C out/cont_svcb .

# Run the container app, without putting the compiled modules of the app on the module path.
# These are loaded by the container as applications!
cd out
# run class moduleClassDir/moduleName/service全类名
java -p container --add-modules ALL-SYSTEM -m cont_cli/org.bougainvilleas.container.cli.Launcher cont_svca/cont_svca/org.bougainvilleas.container.svc.a.AppA cont_svca/cont_svcb/org.bougainvilleas.container.svc.b.AppB
# run class and jar  moduleJarDir/moduleName/service全类名
java -p container --add-modules ALL-SYSTEM -m cont_cli/org.bougainvilleas.container.cli.Launcher jara/cont_svca/org.bougainvilleas.container.svc.a.AppA jarb/cont_svcb/org.bougainvilleas.container.svc.b.AppB

# run jar  moduleJarDir/moduleName/service全类名
java -p mods --add-modules ALL-SYSTEM -m cont_cli jara/cont_svca/org.bougainvilleas.container.svc.a.AppA jarb/cont_svcb/org.bougainvilleas.container.svc.b.AppB
# run jar and class  moduleClassDir/moduleName/service全类名
java -p mods --add-modules ALL-SYSTEM -m cont_cli cont_svca/cont_svca/org.bougainvilleas.container.svc.a.AppA cont_svca/cont_svcb/org.bougainvilleas.container.svc.b.AppB

deploy 0
deploy 1
undeploy 0
undeploy 1
exit
cd ..

# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods:$JAVA_HOME/jmods --add-modules cont_cli --launcher app=cont_cli --output out/image

# 运行
cd out
# linux
./image/bin/app jara/cont_svca/org.bougainvilleas.container.svc.a.AppA jarb/cont_svcb/org.bougainvilleas.container.svc.b.AppB
./image/bin/app cont_svca/cont_svca/org.bougainvilleas.container.svc.a.AppA cont_svca/cont_svcb/org.bougainvilleas.container.svc.b.AppB
./image/bin/java --list-modules
# windows
.\out\image\bin\app.bat jara/cont_svca/org.bougainvilleas.container.svc.a.AppA jarb/cont_svcb/org.bougainvilleas.container.svc.b.AppB
.\out\image\bin\app.bat cont_svca/cont_svca/org.bougainvilleas.container.svc.a.AppA cont_svca/cont_svcb/org.bougainvilleas.container.svc.b.AppB
.\out\image\bin\java --list-modules

```