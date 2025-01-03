pipeline {
    agent any
    
    environment {
        SONAR_TOKEN = credentials('sonar-token')  // Ensure SonarQube token is correctly configured in Jenkins
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"  // Ensure the JAVA_HOME path is correctly configured
    }
    
    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        
        stage('Build with Maven') {
            steps {
                bat 'mvn clean package'  // Build the project using Maven
            }
        }
        
        stage('Run Automation Tests') {
            steps {
                bat 'mvn test'  // Run the test suite with Maven
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=mern ^
                        -Dsonar.tests=src/test/java ^
                        -Dsonar.java.binaries=target/classes ^
                        -Dsonar.java.test.binaries=target/test-classes ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=%SONAR_TOKEN%
                    """
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'  // Success message
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'  // Failure message
        }
    }
}
