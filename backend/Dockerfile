FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/projectBancoDados-0.0.1-SNAPSHOT.jar /usr/local/lib/projectBancoDados.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/projectBancoDados.jar"]
