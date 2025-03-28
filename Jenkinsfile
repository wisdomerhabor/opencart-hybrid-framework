pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'maven' // Ensure Maven is configured in Jenkins
    }

    stages {
        stage('Checkout GitHub Code') {
            steps {
                git branch: 'master', url: 'https://github.com/wisdomerhabor/opencart-hybrid-framework.git'
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

        stage('Archive Test Reports') {
            steps {
				bat 'if not exist "reports" mkdir "reports"'
                archiveArtifacts artifacts: 'reports/*.html', fingerprint: true
            }
        }

        stage('Publish Extent HTML Reports') {
            steps {
                publishHTML (target: [
                    reportDir: 'reports',
                    reportFiles: '*.html',
                    reportName: 'Selenium Test Reports',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml' // Publish JUnit test results
        }
        failure {
            echo "❌ Tests Failed! Check Reports."
        }
        success {
            echo "✅ Tests Passed! All Good."
        }
    }
}
