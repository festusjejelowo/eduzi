web: 	java -cp target/eduzi-core-1.0.jar:target/dependency/* com.ocularminds.eduzi.WebService
web: 	java $JAVA_OPTS -Dconfig.file=application.conf -Djava.util.logging.config.file=logging.properties -cp "target/lib/*" org.openscoring.server.Main --port $PORT --model-dir "pmml"
queue:  java -cp target/eduzi-core-1.0.jar:target/dependency/* com.ocularminds.eduzi.SearchBroker
