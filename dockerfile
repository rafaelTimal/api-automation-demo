# Imagen base de Jenkins con JDK 17
FROM jenkins/jenkins:lts-jdk17

USER root

# Instalar Maven y Git
RUN apt-get update && \
    apt-get install -y maven git && \
    apt-get clean

# Crear directorio de trabajo
WORKDIR /workspace

# Copiar proyecto
COPY . /workspace

# Cambiar permisos para Jenkins
RUN chown -R jenkins:jenkins /workspace

USER jenkins

# Comando por defecto: iniciar Jenkins
ENTRYPOINT ["/usr/bin/tini", "--", "/usr/local/bin/jenkins.sh"]
