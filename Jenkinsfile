pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/tuusuario/api-automation-demo.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Report') {
            steps {
                junit 'target/surefire-reports/*.xml'
                echo 'Tests ejecutados, generando reporte...'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*.json, target/**/*.xml, target/site/**', allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline completado con éxito.'
        }
        failure {
            echo '❌ El pipeline falló. Revisa los logs de ejecución.'
        }
    }
}
