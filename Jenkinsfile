pipeline {
    agent any

    tools {
        // Usa el nombre que configuraste en Jenkins → Java 21
        jdk 'jdk21'
        maven 'maven3.9.11'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rafaelTimal/api-automation-demo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('HTML Report') {
            steps {
                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site',
                    reportFiles: 'surefire-report.html',
                    reportName: 'Test HTML Report'
                ])
            }
        }


    }

    post {
        always {
            echo 'Pipeline finalizado — limpiando workspace.'
            cleanWs()
        }
    }
}
