pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven' // Make sure Maven is configured in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/wisdomerhabor/selenium-automation-framework.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" clean install -DskipTests'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" test'
            }
        }

        stage('Archive Extent Reports') {
            steps {
                archiveArtifacts artifacts: 'test-output/ExtentReports/*.html', fingerprint: true
            }
        }

        stage('Publish Extent HTML Report') {
            steps {
                publishHTML (target: [
                    reportDir: 'test-output/ExtentReports',
                    reportFiles: '*.html',
                    reportName: 'Selenium Test Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml' // Publish TestNG XML test results
        }
        failure {
            echo "❌ Tests Failed! Check Extent Reports."
        }
        success {
            echo "✅ Tests Passed! View the Extent Report in Jenkins."
        }
    }
}
