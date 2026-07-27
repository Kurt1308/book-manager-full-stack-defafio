# ==========================================
# Etapa de Build
# ==========================================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o arquivo de dependências
COPY pom.xml .

# Copia o código-fonte
COPY src ./src

# Gera o artefato da aplicação
RUN mvn clean package -DskipTests

# ==========================================
# Etapa de Execução
# ==========================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Porta utilizada pela aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]